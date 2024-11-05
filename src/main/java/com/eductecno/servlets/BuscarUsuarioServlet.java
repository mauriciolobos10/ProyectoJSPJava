package com.eductecno.servlets;

import com.eductecno.dao.UsuarioDAOImp;
import com.eductecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/BuscarUsuario")
public class BuscarUsuarioServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");

        Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorNombre(nombre);

        if (usuarioOpt.isPresent()) {
            request.setAttribute("usuarioEncontrado", usuarioOpt.get());
        } else {
            request.setAttribute("usuarioEncontrado", null);
            request.setAttribute("mensaje", "Usuario no encontrado.");
        }

        request.getRequestDispatcher("buscarUsuario.jsp").forward(request, response);
    }
}
