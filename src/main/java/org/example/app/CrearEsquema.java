package org.example.app;

import org.example.utils.ConnectionFactory;
import org.example.utils.DerbyConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearEsquema {

    public static void run() {
        ConnectionFactory factory = new DerbyConnectionFactory();

        try (Connection conn = factory.getConnection();
             Statement stmt = conn.createStatement()) {

            try { stmt.execute("DROP TABLE factura_producto"); } catch (Exception ignored) {}
            try { stmt.execute("DROP TABLE factura"); } catch (SQLException ignored) {}
            try { stmt.execute("DROP TABLE producto"); } catch (SQLException ignored) {}
            try { stmt.execute("DROP TABLE cliente"); } catch (SQLException ignored) {}



            // Tabla Cliente
            String tableCliente = "CREATE TABLE cliente(" +
                    "idCliente INT PRIMARY KEY, " +
                    "nombre VARCHAR(500), " +
                    "email VARCHAR(500))";

            // Tabla Producto
            String tableProducto = "CREATE TABLE producto(" +
                    "idProducto INT PRIMARY KEY, " +
                    "nombre VARCHAR(45), " +
                    "valor FLOAT)";

            // Tabla Factura
            String tableFactura = "CREATE TABLE factura(" +
                    "idFactura INT PRIMARY KEY, " +
                    "idCliente INT REFERENCES cliente(idCliente))";

            // Tabla Factura_Producto (relación N:N)
            String tableFacturaProducto = "CREATE TABLE factura_producto(" +
                    "idFactura INT REFERENCES factura(idFactura), " +
                    "idProducto INT REFERENCES producto(idProducto), " +
                    "cantidad INT, " +
                    "PRIMARY KEY(idFactura, idProducto))";

            // Ejecutar, ignorando si ya existen
            try { stmt.execute(tableCliente); } catch (SQLException ignored) {}
            try { stmt.execute(tableProducto); } catch (SQLException ignored) {}
            try { stmt.execute(tableFactura); } catch (SQLException ignored) {}
            try { stmt.execute(tableFacturaProducto); } catch (SQLException ignored) {}
            System.out.println("Esquema de tablas creado");

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}

