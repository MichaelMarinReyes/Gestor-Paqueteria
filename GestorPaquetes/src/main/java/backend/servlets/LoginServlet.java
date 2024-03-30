package backend.servlets;

import backend.clases.principal.FuncionamientoPrincipal;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("alert(\"Recibiendo datos\")");

        FuncionamientoPrincipal funcionamientoPrincipal = new FuncionamientoPrincipal();
        boolean usuarioValido = funcionamientoPrincipal.verificarUsuario(username, password);

        if (usuarioValido) {
            //response.sendRedirect("../webapp/vue_app/gestor_paquetes/src/components/administrador/PaginaAdministrador");
            response.sendRedirect(request.getContextPath() + "/vue_app/gestor_paquetes/src/components/administrador/PaginaAdministrador");

        } else {
            //NO CAMBIA DE PÁGINA
        }
    }
}
