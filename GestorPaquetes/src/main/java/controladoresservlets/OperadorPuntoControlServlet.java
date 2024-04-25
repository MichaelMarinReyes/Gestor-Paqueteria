package controladoresservlets;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import com.google.gson.Gson;
import database.accionesadmin.PuntoDeControlDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServiciosOperador;
import util.ExcepcionApi;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "obtener-puntos-de-control", urlPatterns = "/puntos-de-control-asignados/*")
public class OperadorPuntoControlServlet extends HttpServlet {
    private PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
    private ServiciosOperador serviciosOperador = new ServiciosOperador();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idOperador");
        if (idParametro != null && !idParametro.isEmpty()) {
            try {
                this.sendResponse(resp, serviciosOperador.obtenerPuntosDeControl(Integer.parseInt(idParametro)));
            } catch (ExcepcionApi e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    private void sendResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void sendError(HttpServletResponse resp, ExcepcionApi e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCode(), e.getMessage());
    }

    private void sendJsonResponse(HttpServletResponse resp, int statusCode, Object data) throws IOException {
        resp.setStatus(statusCode);
        resp.setContentType("application/json");
        resp.getWriter().println(new Gson().toJson(data));
    }

    private void sendError(HttpServletResponse resp, int statusCode, String message) throws IOException {
        resp.setStatus(statusCode);
        resp.setContentType("application/json");
        resp.getWriter().println("{\"error\": \"" + message + "\"}");
    }
}
