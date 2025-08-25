package org.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static void main(String[] args) {

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String uri = "jdbc:derby:exampleDB;create=true";

        try {
            Connection conn = DriverManager.getConnection(uri);

           String tableCliente = "CREATE OR REPLACE TABLE cliente("
                    + "idCliente INT, "
                    + "nombre VARCHAR(500),"
                    + "email VARCHAR(500),"
                    + "PRIMARY KEY(idCliente))";
            String tableProducto = "CREATE TABLE Producto("
                    + "idProducto INT, "
                    + "nombre VARCHAR(45), "
                    + "valor FLOAT,"
                    + "PRIMARY KEY(idProducto))";
            String tableFactura = "CREATE TABLE factura("
                    + "idFactura INT, "
                    + "idCLiente INT, "
                    + "PRIMARY KEY(idFactura),"
                    + "FOREIGN KEY(idCliente) REFERENCES cliente(idCliente))";

            String tableFacturaProducto = "CREATE TABLE Factura_producto("
                    + "idFactura INT, "
                    + "idProducto INT, "
                    + "cantidad INT, "
                    + "PRIMARY KEY(idFactura)," +
                    "FOREIGN KEY(idProducto) REFERENCES producto(idProducto))";



            conn.prepareStatement(tableCliente).execute();
            conn.prepareStatement(tableProducto).execute();
            conn.prepareStatement(tableFactura).execute();
            conn.prepareStatement(tableFacturaProducto).execute();
            conn.commit();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

}
