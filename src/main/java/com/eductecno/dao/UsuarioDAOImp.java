package com.eductecno.dao;

import com.eductecno.modelo.Usuario;
import com.eductecno.procesaconexion.ConexionBD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class UsuarioDAOImp implements IDAO{
    private Connection conectar() throws SQLException, ClassNotFoundException {
        return ConexionBD.getInstance();
    }
    @Override
    public List obtenerHoroscopo() {
        return null;
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        Optional<Usuario> optionalUsuario = Optional.empty();
        try (Statement st = conectar().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE id = " + id + ";")) {
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("password"),
                        rs.getString("animal")
                );
                optionalUsuario = Optional.of(usuario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return optionalUsuario;
    }

    @Override
    public Boolean agregar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getUserName());
            stmt.setString(3, u.getEmail());
            stmt.setDate(4, new java.sql.Date(u.getFechaNacimiento().getTime()));
            stmt.setString(5, u.getPassword());
            stmt.setString(6, u.getAnimal());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean eliminar(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = " + id + ";";
        try (Statement st = conectar().createStatement()) {
            st.execute(sql);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean modificar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, username = ?, fecha_nacimiento = ?" +
                (u.getPassword() != null && !u.getPassword().isEmpty() ? ", password = ?" : "") +
                " WHERE id = ?";

        try (Connection conn = ConexionBD.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getUserName());
            stmt.setDate(4, new java.sql.Date(u.getFechaNacimiento().getTime()));

            int parameterIndex = 5;
            if (u.getPassword() != null && !u.getPassword().isEmpty()) {
                stmt.setString(parameterIndex++, u.getPassword());
            }

            stmt.setLong(parameterIndex, u.getId());

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Optional<Usuario> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";

        try (Connection conn = ConexionBD.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUserName(rs.getString("username"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setEmail(rs.getString("email"));
                return Optional.of(usuario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Optional.empty(); // Retorna vacío si no encuentra al usuario
    }
}
