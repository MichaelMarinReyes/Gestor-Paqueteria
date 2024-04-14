package controladoresservlets;

import com.google.gson.JsonSyntaxException;
import clases.roles.Usuario;
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
    private LoginDao loginDao = new LoginDao();

    //OBTENER UN RECURSO
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //GUARDAR UN RECURSO
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();

        try {
            Usuario usuario = gson.fromJson(reader, Usuario.class);
            if (usuario != null) {
                int idUsuario = usuario.getUsuario();
                String contraseña = usuario.getContraseña();
                String rol = usuario.getRol();
                boolean credencialesValidas = loginDao.verificarCredenciales(String.valueOf(idUsuario), contraseña, rol);

                if (credencialesValidas) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    //resp.getWriter().write("/admin");
                } else {
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.getWriter().write("Credenciales inválidas. Por favor, inténtalo de nuevo.");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Error al procesar la solicitud. El objeto Usuario es nulo o no se pudo deserializar correctamente.");
            }
        } catch (JsonSyntaxException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error de sintaxis JSON en la solicitud. Verifica el formato de los datos enviados.");
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
