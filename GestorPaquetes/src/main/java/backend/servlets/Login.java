package backend.servlets;

import backend.clases.principal.FuncionamientoPrincipal;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");

        // Crea un escritor para enviar la respuesta al cliente
        PrintWriter out = response.getWriter();

        // Llama al método verificarUsuario de tu lógica principal para validar las credenciales
        FuncionamientoPrincipal funcionamientoPrincipal = new FuncionamientoPrincipal();
        boolean esUsuarioValido = funcionamientoPrincipal.verificarUsuario(usuario, contraseña);

        // Verifica si el usuario es válido y envía la respuesta correspondiente
        if (esUsuarioValido) {
            // Si el usuario es válido, envía una respuesta exitosa
            out.println("success");
        } else {
            // Si el usuario no es válido, envía una respuesta de error
            out.println("error");
        }
    }
}
