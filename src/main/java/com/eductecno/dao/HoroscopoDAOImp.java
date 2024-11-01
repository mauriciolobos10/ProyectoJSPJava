package com.eductecno.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eductecno.modelo.Horoscopo;
import com.eductecno.modelo.Usuario;
import com.eductecno.procesaconexion.ConexionBD;

public class HoroscopoDAOImp implements IDAO{
    private Connection conectar() throws SQLException, ClassNotFoundException {
        return ConexionBD.getInstance();
    }

    @Override
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> listaHoroscopo = new ArrayList<>();
        try (Statement st = conectar().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM horoscopo;")) {
            while(rs.next()) {
                Horoscopo horoscopo = new Horoscopo(
                        rs.getString("animal"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin")
                );
                listaHoroscopo.add(horoscopo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return listaHoroscopo;
    }

    @Override
    public Object porId(Long id) {
        return null;
    }

    @Override
    public Object agregar(Usuario p) {
        return null;
    }

    @Override
    public Object eliminar(Long id) {
        return null;
    }

}
