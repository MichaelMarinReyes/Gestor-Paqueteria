package controladoresservlets;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import com.google.gson.Gson;
import database.accionesadmin.PuntoDeControlDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "gestionar-puntos-de-control", urlPatterns = "/gestionar-puntos-de-control/*")
public class PuntoControlServlet extends HttpServlet {
    private final PuntoDeControlDao puntoControlDao = new PuntoDeControlDao();
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParametro = req.getParameter("id");
        if (idParametro != null && !idParametro.isEmpty()) {
            int idPuntoControl = Integer.parseInt(idParametro);
            PuntoDeControl puntoControl = puntoControlDao.obtenerPuntoControlPorId(idPuntoControl);
            if (puntoControl != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(puntoControl));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            List<PuntoDeControl> puntosControl = puntoControlDao.obtenerTodosLosPuntosControl();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(puntosControl));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            PuntoDeControl puntoDeControl = gson.fromJson(req.getReader(), PuntoDeControl.class);
            this.enviarResponse(resp, puntoControlDao.crearPuntoControl(puntoDeControl));
        } catch (Exception e) {
            this.enviarError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void enviarResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
    }

    private void enviarError(HttpServletResponse resp, ExcepcionApi e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCode(), e.getMessage());
    }
}
