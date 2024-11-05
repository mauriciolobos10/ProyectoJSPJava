package com.eductecno.servlets;
import com.eductecno.dao.UsuarioDAOImp;
import com.eductecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        String repetirPassword = request.getParameter("repetirPassword");

        if (!password.equals(repetirPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setUserName(username);
        nuevoUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento)); // convertir fecha
        nuevoUsuario.setPassword(password);

        boolean registroExitoso = usuarioDAO.agregar(nuevoUsuario);

        if (registroExitoso) {
            request.getSession().setAttribute("mensaje", "Registro realizado correctamente.");
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "No se pudo registrar el usuario. Inténtalo de nuevo.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}
