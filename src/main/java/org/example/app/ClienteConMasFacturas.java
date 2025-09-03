package org.example.app;
import org.example.utils.ConnectionFactory;
import org.example.utils.DerbyConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteConMasFacturas {

        public static void run() {
            ConnectionFactory factory = new DerbyConnectionFactory();

            String sql =
                    "SELECT c.idCliente, c.nombre, c.email, COUNT(f.idCliente) AS cantidad_facturas " +
                            "FROM cliente c " +
                            "LEFT JOIN factura f ON c.idCliente = f.idCliente " +
                            "GROUP BY c.idCliente, c.nombre, c.email " +
                            "ORDER BY cantidad_facturas DESC";

            try (Connection conn = factory.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    int idCliente = rs.getInt("idCliente");
                    String nombre = rs.getString("nombre");
                    if (nombre == null) nombre = "";

                    String email = rs.getString("email");
                    if (email == null) email = "";

                    int cantidad = rs.getInt("cantidad_facturas");

                    System.out.println(idCliente + " - " + nombre + " - " + email + " - Facturas: " + cantidad);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


