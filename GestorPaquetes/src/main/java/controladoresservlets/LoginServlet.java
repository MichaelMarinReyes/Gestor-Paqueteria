package controladoresservlets;

import clases.roles.Administrador;
import database.LoginDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login/*")
public class LoginServlet extends HttpServlet {
    //private CorsFilter
    private LoginDao loginDao = new LoginDao();

    //OBTENER UN RECURSO
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Administrador administrador = gson.fromJson(reader, Administrador.class);

        int usuario = administrador.getIdAdministrador();
        String contraseña = administrador.getContraseña();
        String rol = administrador.getRol();
        boolean credencialesValidas = loginDao.verificarCredenciales(String.valueOf(usuario), contraseña, rol);

        if (credencialesValidas) {
            resp.setStatus(HttpServletResponse.SC_OK);
            //resp.getContentType("aqui se redirige a la correspondiente");
            resp.getWriter().write("/admin");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Credenciales inválidas. Por favor, inténtalo de nuevo.");
        }
    }

    //GUARDAR UN RECURSO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Administrador administrador = gson.fromJson(reader, Administrador.class);

        int usuario = administrador.getIdAdministrador();
        String contraseña = administrador.getContraseña();
        String rol = administrador.getRol();
        boolean credencialesValidas = loginDao.verificarCredenciales(String.valueOf(usuario), contraseña, rol);

        if (credencialesValidas) {
            resp.setStatus(HttpServletResponse.SC_OK);
            //resp.getContentType("aqui se redirige a la correspondiente");
            resp.getWriter().write("/admin");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Credenciales inválidas. Por favor, inténtalo de nuevo.");
        }
    }

    //MODIFICAR UN RECURSO
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    //BORRAR UN TODOS LOS ATRIBUTOS
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    //MODIFICAR SOLO UN ATRIBUTO

}
