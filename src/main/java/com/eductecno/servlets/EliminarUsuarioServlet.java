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

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            System.out.println("No hay sesión activa.");
            response.sendRedirect("login.jsp");
            return;
        } else {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
            } else {
                Long usuarioId = usuario.getId();

                boolean exitoEliminacion = usuarioDAO.eliminar(usuarioId);

                if (exitoEliminacion) {
                    session.invalidate();
                    request.getSession().setAttribute("mensaje", "Cuenta eliminada correctamente.");
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo eliminar el usuario.");
                }
            }
        }
    }
}
