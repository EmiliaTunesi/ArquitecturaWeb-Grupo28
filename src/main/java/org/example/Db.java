package org.example;

/* import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class Db {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String uri = "jdbc:derby:exampleDB;create=true";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(uri);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            crearTablas(conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crearTablas(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        String tableCliente = "CREATE TABLE cliente(" +
                "idCliente INT PRIMARY KEY, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(500))";

        String tableProducto = "CREATE TABLE producto(" +
                "idProducto INT PRIMARY KEY, " +
                "nombre VARCHAR(45), " +
                "valor FLOAT)";

        String tableFactura = "CREATE TABLE factura(" +
                "idFactura INT PRIMARY KEY, " +
                "idCliente INT REFERENCES cliente(idCliente))";

        String tableFacturaProducto = "CREATE TABLE factura_producto(" +
                "idFactura INT REFERENCES factura(idFactura), " +
                "idProducto INT REFERENCES producto(idProducto), " +
                "cantidad INT, " +
                "PRIMARY KEY(idFactura, idProducto))";

        try { stmt.execute(tableCliente); } catch (SQLException ignored) {}
        try { stmt.execute(tableProducto); } catch (SQLException ignored) {}
        try { stmt.execute(tableFactura); } catch (SQLException ignored) {}
        try { stmt.execute(tableFacturaProducto); } catch (SQLException ignored) {}

        stmt.close();
    }

}*/
