package com.eductecno.servlets;

import com.eductecno.dao.UsuarioDAOImp;
import com.eductecno.modelo.Usuario;
import com.eductecno.procesaconexion.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/IniciaSesion")
public class IniciaSesionServlet extends HttpServlet {

    private UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("username");
        String c = req.getParameter("password");

        String sql = "SELECT * FROM usuarios WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conn = ConexionBD.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u);
            stmt.setString(2, c);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUserName(rs.getString("username"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    usuario.setPassword(rs.getString("password")); // Evita almacenar la contraseña en producción


                    HttpSession session = req.getSession();
//                    session.setAttribute("usuario", rs.getString("username"));
                    session.setAttribute("usuario", usuario);
                    resp.sendRedirect(req.getContextPath() + "/menuPrincipal.jsp");
                } else {
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario o contraseña incorrectos.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en MySQL: " + e.getMessage());
        }
    }
}