package controladoresservlets;

import clases.puntosdecontrorutaydestino.Ruta;
import com.google.gson.Gson;
import database.accionesadmin.RutaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioAdministrador;
import util.ExcepcionApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ruta", urlPatterns = "/ruta/*")
public class RutaServlet extends HttpServlet {
    private RutaDao rutaDao = new RutaDao();
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParametro = req.getParameter("idRuta");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idRuta = Integer.parseInt(idParametro);
            Ruta ruta = rutaDao.obtenerRuta(idRuta);
            if (ruta != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(ruta));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.sendResponse(resp, servicioAdministrador.obtenerRutas());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            Ruta ruta = gson.fromJson(req.getReader(), Ruta.class);
            this.sendResponse(resp, rutaDao.crearRuta(ruta));
        } catch (Exception e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = req.getReader();
            Ruta ruta = gson.fromJson(reader, Ruta.class);
            servicioAdministrador.editarRuta(ruta);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .mensaje("Error al procesar el JSON: " + e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idRuta");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idRuta = Integer.parseInt(idParametro);
            Ruta idRutaEntidad = rutaDao.obtenerRuta(idRuta);
            if (idRutaEntidad != null) {
                servicioAdministrador.eliminarRuta(idRutaEntidad.getIdPuntoControl());
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private void sendResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
    }

    private void sendError(HttpServletResponse resp, ExcepcionApi e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCode(), e.getMessage());
    }
}
