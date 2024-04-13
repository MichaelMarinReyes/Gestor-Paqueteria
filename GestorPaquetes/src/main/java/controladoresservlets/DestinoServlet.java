package controladoresservlets;

import clases.puntosdecontrorutaydestino.Paquete;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.accionesrecepcionista.DestinoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioRecepcionista;
import util.AdaptadorLocalTime;
import util.ExcepcionApi;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(name = "gestionar-paquetes-destino", urlPatterns = "/gestionar-paquetes-destino/*")
public class DestinoServlet extends HttpServlet {
    private ServicioRecepcionista servicioRecepcionista = new ServicioRecepcionista();
    private DestinoDao destinoDao = new DestinoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idPaquete");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPaquete = Integer.parseInt(idParametro);
            Paquete paquete = destinoDao.paqueteEntregado(idPaquete);
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
            this.sendResponse(resp, servicioRecepcionista.obtenerPaquetesEntregados());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
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
