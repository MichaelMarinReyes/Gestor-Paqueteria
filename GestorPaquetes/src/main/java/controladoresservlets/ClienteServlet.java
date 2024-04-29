package controladoresservlets;

import clases.puntosdecontrorutaydestino.PuntoDeControl;
import clases.roles.Cliente;
import com.google.gson.Gson;
import database.accionesadmin.ClienteDao;
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

@WebServlet(name = "gestionar-clientes", urlPatterns = "/gestionar-clientes/*")
public class ClienteServlet extends HttpServlet {
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    private ClienteDao clienteDao = new ClienteDao();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParametro = req.getParameter("nit");
        if (idParametro != null && !idParametro.isEmpty()) {
            Cliente nitCliente = clienteDao.obtenerCliente(idParametro);
            if (nitCliente != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(nitCliente));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            this.sendResponse(resp, servicioAdministrador.obtenerClientes());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(req.getReader(), Cliente.class);
            Cliente clienteCreado = servicioAdministrador.crearCliente(cliente);
            sendJsonResponse(resp, HttpServletResponse.SC_CREATED, clienteCreado);
        } catch (ExcepcionApi e) {
            sendError(resp, e.getCode(), e.getMensaje());
        } catch (Exception e) {
            sendError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nit = req.getParameter("nit");
            Gson gson = new Gson();
            BufferedReader reader = req.getReader();
            Cliente cliente = gson.fromJson(reader, Cliente.class);
            try {
                servicioAdministrador.editarCliente(cliente, nit);
                resp.setStatus(HttpServletResponse.SC_OK);
            } catch (ExcepcionApi e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                this.sendError(resp, ExcepcionApi.builder()
                        .code(HttpServletResponse.SC_BAD_REQUEST)
                        .mensaje("Error al procesar el JSON: " + e.getMessage())
                        .build());
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
        String idParametro = req.getParameter("nit");
        if (idParametro != null && !idParametro.isEmpty()) {
            Cliente cliente = clienteDao.obtenerCliente(idParametro);
            if (cliente != null) {
                servicioAdministrador.eliminarCliente(cliente.getNit());
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
