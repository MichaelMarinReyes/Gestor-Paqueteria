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
       /* String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");

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
        }*/

        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");

        // Aquí deberías realizar la validación de las credenciales y determinar el rol del usuario
        FuncionamientoPrincipal funcionamientoPrincipal = new FuncionamientoPrincipal();
        String rolUsuario = funcionamientoPrincipal.obtenerRolUsuario(usuario, contraseña);

        // Redirige a la página correspondiente según el rol del usuario
        if (rolUsuario != null) {
            if (rolUsuario.equals("administrador")) {
                response.sendRedirect("pagina-administrador.html");
            } else if (rolUsuario.equals("operador")) {
                response.sendRedirect("pagina-operador.html");
            } else if (rolUsuario.equals("recepcionista")) {
                response.sendRedirect("pagina-recepcionista.html");
            } else {
                // Manejo para otros roles o situaciones no previstas
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Rol de usuario no válido");
            }
        } else {
            // Si las credenciales no son válidas, redirige de nuevo a la página de inicio de sesión con un mensaje de error
            response.sendRedirect("login.html?error=credenciales_invalidas");
        }
    }
}
