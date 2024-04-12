package controladoresservlets;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import com.google.gson.Gson;
import database.accionesadmin.PuntoDeControlDao;
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

@WebServlet(name = "gestionar-puntos-de-control", urlPatterns = "/gestionar-puntos-de-control/*")
public class PuntoControlServlet extends HttpServlet {
    private PuntoDeControlDao puntoControlDao = new PuntoDeControlDao();
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParametro = req.getParameter("idPuntoControl");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPuntoControl = Integer.parseInt(idParametro);
            PuntoDeControl puntoControl = puntoControlDao.obtenerPuntoControl(idPuntoControl);
            if (puntoControl != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(puntoControl));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.sendResponse(resp, servicioAdministrador.obtenerPuntosDeControl());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            PuntoDeControl puntoDeControl = gson.fromJson(req.getReader(), PuntoDeControl.class);
            this.sendResponse(resp, servicioAdministrador.crearPuntoControl(puntoDeControl));
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
            PuntoDeControl puntoDeControl = gson.fromJson(reader, PuntoDeControl.class);

            try {
                servicioAdministrador.editarPuntoControl(puntoDeControl);
                resp.setStatus(HttpServletResponse.SC_OK);
            } catch (ExcepcionApi e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .mensaje("Error al procesar el JSON: " + e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idPuntoControl");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPuntoControl = Integer.parseInt(idParametro);
            PuntoDeControl puntoControl = puntoControlDao.obtenerPuntoControl(idPuntoControl);
            if (puntoControl != null) {
                try {
                    servicioAdministrador.eliminarPuntoControl(puntoControl.getIdPuntoControl());
                } catch (ExcepcionApi e) {
                    throw new RuntimeException(e);
                }
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
