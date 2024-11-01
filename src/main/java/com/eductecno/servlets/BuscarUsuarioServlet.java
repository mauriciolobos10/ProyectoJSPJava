package com.eductecno.servlets;

import com.eductecno.dao.UsuarioDAOImp;
import com.eductecno.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/BuscarUsuario")
public class BuscarUsuarioServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro id de la solicitud
        String idParam = request.getParameter("id");
        Long id = idParam != null ? Long.parseLong(idParam) : null;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Buscar el usuario y preparar la respuesta
        try (PrintWriter out = response.getWriter()) {
            if (id != null) {
                Optional<Usuario> usuarioOpt = usuarioDAO.porId(id);
                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();
                    // Convertir el usuario a JSON manualmente
                    String usuarioJson = "{"
                            + "\"id\": " + usuario.getId() + ","
                            + "\"nombre\": \"" + usuario.getNombre() + "\","
                            + "\"username\": \"" + usuario.getUserName() + "\","
                            + "\"email\": \"" + usuario.getEmail() + "\","
                            + "\"fechaNacimiento\": \"" + usuario.getFechaNacimiento() + "\","
                            + "\"animal\": \"" + usuario.getAnimal() + "\""
                            + "}";
                    out.print(usuarioJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\": \"Usuario no encontrado\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"ID de usuario no proporcionado\"}");
            }
        }
    }
}
