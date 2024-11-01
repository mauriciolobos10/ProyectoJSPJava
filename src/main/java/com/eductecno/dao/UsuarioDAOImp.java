package com.eductecno.dao;

import com.eductecno.modelo.Usuario;
import com.eductecno.procesaconexion.ConexionBD;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                        rs.getInt("id"),
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
        String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, animal) VALUES('"
                + u.getNombre() + "','"
                + u.getUserName() + "','"
                + u.getEmail() + "','"
                + new java.sql.Date(u.getFechaNacimiento().getTime()) + "','"
                + u.getPassword() + "','"
                + u.getAnimal() + "');";
        try (Statement st = conectar().createStatement()) {
            st.execute(sql);
            return true;
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
}
