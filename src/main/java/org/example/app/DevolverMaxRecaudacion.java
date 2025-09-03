package org.example.app;

import org.example.utils.ConnectionFactory;
import org.example.utils.DerbyConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DevolverMaxRecaudacion {
        private static void run() {
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
                    System.out.println("\nProducto que más recaudó:");
                    System.out.println(
                            rs.getInt("idProducto") + " - " +
                                    rs.getString("nombre") + " - Recaudación: " +
                                    rs.getDouble("recaudacion")
                    );
                } else {
                    System.out.println("No hay productos vendidos.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
