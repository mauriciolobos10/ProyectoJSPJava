package com.eductecno.servlets;
import com.eductecno.dao.UsuarioDAOImp;
import com.eductecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        String repetirPassword = request.getParameter("repetirPassword");

        if (password != null && !password.isEmpty()) {
            if (!password.equals(repetirPassword)) {
                request.setAttribute("error", "Las contraseñas no coinciden.");
                request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
                return;
            }
            usuario.setPassword(password);
        }

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setUserName(username);
        usuario.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));

        boolean exitoModificacion = usuarioDAO.modificar(usuario);

        if (exitoModificacion) {
            request.getSession().setAttribute("mensaje", "Modificación realizada correctamente.");
            session.setAttribute("usuario", usuario);
            response.sendRedirect("menuPrincipal.jsp");
        } else {
            request.setAttribute("error", "No se pudo actualizar el usuario.");
            request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
        }
    }
}
