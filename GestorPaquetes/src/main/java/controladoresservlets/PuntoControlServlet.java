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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "gestionar-puntos-de-control", urlPatterns = "/gestionar-puntos-de-control/*")
public class PuntoControlServlet extends HttpServlet {
    private final PuntoDeControlDao puntoControlDao = new PuntoDeControlDao();
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private final Gson gson = new Gson();

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
            this.sendResponse(resp, puntoControlDao.crearPuntoControl(puntoDeControl));
        } catch (Exception e) {
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //MODIFICAR
        String idParametro = req.getParameter("idPuntoControl");
        System.out.println(idParametro);
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPuntoControl = Integer.parseInt(idParametro);
            try {
                servicioAdministrador.editarPuntoControl(idPuntoControl);
            } catch (ExcepcionApi e) {
                throw new RuntimeException(e);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Error 404");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
