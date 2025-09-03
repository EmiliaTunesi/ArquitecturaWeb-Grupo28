package org.example.app;

import org.example.utils.ConnectionFactory;
import org.example.utils.DerbyConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DevolverMaxRecaudacion {

    public static void run() {
        ConnectionFactory factory = new DerbyConnectionFactory();

        String sqlMaxRecaudacion =
                "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                        "FROM producto p " +
                        "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                        "GROUP BY p.idProducto, p.nombre " +
                        "ORDER BY recaudacion DESC " +
                        "FETCH FIRST ROW ONLY";

        try (Connection conn = factory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlMaxRecaudacion);
             var rs = stmt.executeQuery()) {

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");

                String nombre = rs.getString("nombre");
                if (nombre == null) nombre = ""; // Validación de null

                double recaudacion = rs.getDouble("recaudacion"); // si no hay ventas, devuelve 0.0

                System.out.println("\nProducto que más recaudó:");
                System.out.println(idProducto + " - " + nombre + " - Recaudación: " + recaudacion);

            } else {
                System.out.println("No hay productos vendidos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
