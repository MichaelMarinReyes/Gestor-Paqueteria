package controladoresservlets;

import clases.puntosdecontrorutaydestino.Ruta;
import clases.puntosdecontrorutaydestino.RutaPuntoControl;
import com.google.gson.Gson;
import database.accionesadmin.RutaPuntoControlDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioAdministrador;
import util.ExcepcionApi;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ruta-puntos-de-control", urlPatterns = "/rutas-y-puntos-de-control")
public class RutaPuntoControlServlet extends HttpServlet {
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idRuta");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idRuta = Integer.parseInt(idParametro);
            this.sendResponse(resp, servicioAdministrador.obtenerPuntosDeControlDeRuta(idRuta));
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
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
    }

    private void sendError(HttpServletResponse resp, ExcepcionApi e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCode(), e.getMessage());
    }
}
