package org.example;
/*
import java.io.FileReader;
import java.sql.*;

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
            stmt.executeUpdate("DELETE FROM factura_producto");
            stmt.executeUpdate("DELETE FROM factura");
            stmt.executeUpdate("DELETE FROM producto");
            stmt.executeUpdate("DELETE FROM cliente");

            crearTablas(conn);

            leerClientesDesdeCSV(conn, "src/main/resources/clientes.csv");
            leerProductosDesdeCSV(conn, "src/main/resources/productos.csv");
            leerFacturasDesdeCSV(conn, "src/main/resources/facturas.csv");
            leerFacturaProductoDesdeCSV(conn, "src/main/resources/factura_producto.csv");

            System.out.println("Datos cargados exitosamente.");
            mostrarProductoMasRecaudo(conn);
            System.out.println("\nClientes con mas facturas:");
            clienteConMasFacturas(conn);

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

    private static void mostrarProductoMasRecaudo(Connection conn) {
        try {
            String sqlMaxRecaudacion =
                    "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                            "FROM producto p " +
                            "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                            "GROUP BY p.idProducto, p.nombre " +
                            "ORDER BY recaudacion DESC " +
                            "FETCH FIRST ROW ONLY";
            PreparedStatement stmt = conn.prepareStatement(sqlMaxRecaudacion);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\nProducto que más recaudó:");
                System.out.println(
                        rs.getInt("idProducto") + " - " +
                                rs.getString("nombre") + " - Recaudación: " +
                                rs.getDouble("recaudacion")
                );
            } else {
                System.out.println("No hay productos vendidos.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void clienteConMasFacturas(Connection con){
        try {
            String sql =
                    "SELECT c.idCliente, c.nombre, c.email, COUNT(f.idCliente) AS cantidad_facturas "
                            + "FROM cliente c "
                            + "LEFT JOIN factura f ON c.idCliente = f.idCliente "
                            + "GROUP BY c.idCliente, c.nombre, c.email "
                            + "ORDER BY cantidad_facturas DESC";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println("-" + rs.getString("nombre") +
                        ", Email: " + rs.getString("email") +
                        " - Facturas: " + rs.getInt("cantidad_facturas"));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
*/