package controladoresservlets;

import clases.reportes.ReporteCliente;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioAdministrador;
import util.ExcepcionApi;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "reporte-clientes", urlPatterns = "/reporte-clientes/*")
public class ReporteClienteServlet extends HttpServlet {
    private ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            this.sendResponse(resp, servicioAdministrador.obtenerReporteClientes());

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
