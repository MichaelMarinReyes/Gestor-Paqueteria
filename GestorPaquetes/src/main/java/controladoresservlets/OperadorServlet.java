package controladoresservlets;

import clases.roles.Operador;
import com.google.gson.Gson;
import database.accionesadmin.OperadorDao;
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

@WebServlet(name = "gestionar-operadores", urlPatterns = "/gestionar-operadores/*")
public class OperadorServlet extends HttpServlet {
    private OperadorDao operadorDao = new OperadorDao();
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("idOperador");
        if (idParametro != null && !idParametro.isEmpty()) {
            Operador operador = operadorDao.obtenerOperador(Integer.parseInt(idParametro));
            if (operador != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(operador));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.sendResponse(resp, servicioAdministrador.obtenerOperadores());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            Operador operador = gson.fromJson(req.getReader(), Operador.class);
            this.sendResponse(resp, servicioAdministrador.crearOperador(operador));
            sendJsonResponse(resp, HttpServletResponse.SC_CREATED, operador);
        } catch (ExcepcionApi e) {
            sendError(resp, e.getCode(), e.getMensaje());
        } catch (Exception e) {
            sendError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idOperador = req.getParameter("idOperador");
            Gson gson = new Gson();
            BufferedReader reader = req.getReader();
            Operador operador = gson.fromJson(reader, Operador.class);
            try {
                servicioAdministrador.actualizarOperador(operador, Integer.parseInt(idOperador));
                resp.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                this.sendError(resp, ExcepcionApi.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .mensaje("Error al procesar el JSON: " + e.getMessage())
                        .build());
            }
        } catch (Exception e) {
            System.out.println("Error");
            this.sendError(resp, ExcepcionApi.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .mensaje("Error al procesar el JSON: " + e.getMessage())
                    .build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOperador = req.getParameter("idOperador");
        if (idOperador != null && !idOperador.isEmpty()) {
            Operador operador = operadorDao.obtenerOperador(Integer.parseInt(idOperador));
            if (operador != null) {
                try {
                    servicioAdministrador.eliminarOperador(operador.getIdOperador());
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
