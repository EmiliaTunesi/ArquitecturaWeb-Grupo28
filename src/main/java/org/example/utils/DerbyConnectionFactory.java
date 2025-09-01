package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyConnectionFactory implements ConnectionFactory {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:exampleDB;create=true";

    static {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error cargando driver Derby", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}


//esta es la manera de pedir una conexion derby
//ConnectionFactory factory = new DerbyConnectionFactory(); // o MySQLConnectionFactory en el futuro
//try (Connection conn = factory.getConnection()) {
//    System.out.println("Conectado a la base!");
//}
//Abrir conexiones en todos los DAOS para hacer el CRUD