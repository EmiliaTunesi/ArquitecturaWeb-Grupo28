package org.example;
/*
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Insert {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String uri = "jdbc:derby:exampleDB;create=true";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(uri);
        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM factura_producto");
        stmt.executeUpdate("DELETE FROM factura");
        stmt.executeUpdate("DELETE FROM producto");
        stmt.executeUpdate("DELETE FROM cliente");

        leerClientesDesdeCSV(conn, "src/main/resources/clientes.csv");
        leerProductosDesdeCSV(conn, "src/main/resources/productos.csv");
        leerFacturasDesdeCSV(conn, "src/main/resources/facturas.csv");
        leerFacturaProductoDesdeCSV(conn, "src/main/resources/factura_producto.csv");

        conn.commit();
        conn.close();

        System.out.println("Datos cargados exitosamente.");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }



    private static void leerClientesDesdeCSV(Connection conn, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        String sql = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (CSVRecord row : parser) {
            pstmt.setInt(1, Integer.parseInt(row.get("idCliente")));
            pstmt.setString(2, row.get("nombre"));
            pstmt.setString(3, row.get("email"));
            pstmt.executeUpdate();
        }

        pstmt.close();
    }

    private static void leerProductosDesdeCSV(Connection conn, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (CSVRecord row : parser) {
            pstmt.setInt(1, Integer.parseInt(row.get("idProducto")));
            pstmt.setString(2, row.get("nombre"));
            pstmt.setFloat(3, Float.parseFloat(row.get("valor")));
            pstmt.executeUpdate();
        }

        pstmt.close();
    }

    private static void leerFacturasDesdeCSV(Connection conn, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        String sql = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (CSVRecord row : parser) {
            pstmt.setInt(1, Integer.parseInt(row.get("idFactura")));
            pstmt.setInt(2, Integer.parseInt(row.get("idCliente")));
            pstmt.executeUpdate();
        }

        pstmt.close();
    }

    private static void leerFacturaProductoDesdeCSV(Connection conn, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        String sql = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (CSVRecord row : parser) {
            pstmt.setInt(1, Integer.parseInt(row.get("idFactura")));
            pstmt.setInt(2, Integer.parseInt(row.get("idProducto")));
            pstmt.setInt(3, Integer.parseInt(row.get("cantidad")));
            pstmt.executeUpdate();
        }

        pstmt.close();
    }
}
*/