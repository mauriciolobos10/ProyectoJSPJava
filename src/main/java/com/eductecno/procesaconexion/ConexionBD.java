package com.eductecno.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static Connection c;
    private static String url = "jdbc:mysql://localhost:3306/modulo5_horoscopo?serverTimezone=America/Santiago";
    private static String usuario = "root";
    private static String clave = "root";

    public static Connection getInstance() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,usuario,clave);
    }

}
