package controladoresservlets;

import clases.puntosdecontrorutaydestino.Paquete;
import clases.puntosdecontrorutaydestino.Ruta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.accionesrecepcionista.LocalizarPaqueteDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioRecepcionista;
import util.AdaptadorLocalTime;
import util.ExcepcionApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(name = "gestionar-paquetes", urlPatterns = "/gestionar-paquetes/*")
public class PaqueteServlet extends HttpServlet {

    private LocalizarPaqueteDao localizarPaqueteDao = new LocalizarPaqueteDao();
    private ServicioRecepcionista servicioRecepcionista = new ServicioRecepcionista();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idPaquete");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPaquete = Integer.parseInt(idParametro);
            Paquete paquete = localizarPaqueteDao.obtenerPaquete(idPaquete);
            if (paquete != null) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalTime.class, new AdaptadorLocalTime())
                        .create();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.write(gson.toJson(paquete));
                writer.flush();
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.sendResponse(resp, servicioRecepcionista.obtenerPaquetes());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalTime.class, new AdaptadorLocalTime());
            Gson gson = gsonBuilder.create();
            Paquete paquete = gson.fromJson(req.getReader(), Paquete.class);
            Paquete paqueteInsertado = servicioRecepcionista.insertarPaquete(paquete);
            sendResponse(resp, paqueteInsertado);
        } catch (Exception e) {
            sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalTime.class, new AdaptadorLocalTime())
                    .create();
            int idPaquete = Integer.parseInt(req.getParameter("idPaquete"));
            BufferedReader reader = req.getReader();
            Paquete paquete = gson.fromJson(reader, Paquete.class);
            paquete.setIdPaquete(idPaquete);
            servicioRecepcionista.actualizarPaquete(paquete, idPaquete);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .mensaje("ID de paquete no válido: " + e.getMessage())
                    .build());
        } catch (Exception e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .mensaje("Error al procesar el JSON: " + e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idPaquete");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPaquete = Integer.parseInt(idParametro);
            Paquete paquete = localizarPaqueteDao.obtenerPaquete(idPaquete);
            if (paquete != null) {
                servicioRecepcionista.eliminarPaquete(paquete.getIdPaquete());
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private void sendResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new AdaptadorLocalTime())
                .create();

        writer.write(gson.toJson(object));
        writer.flush();
    }

    private void sendError(HttpServletResponse resp, ExcepcionApi e) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(e.getCode());
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();
        writer.write(gson.toJson(e));
        writer.flush();
    }
}
