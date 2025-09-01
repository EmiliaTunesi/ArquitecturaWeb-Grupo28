package org.example;

/*import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Select {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String uri = "jdbc:derby:exampleDB;create=true";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(uri);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            mostrarProductoMasRecaudo(conn);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarProductoMasRecaudo(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlMaxRecaudacion =
                    "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                            "FROM producto p " +
                            "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                            "GROUP BY p.idProducto, p.nombre " +
                            "ORDER BY recaudacion DESC " +
                            "FETCH FIRST ROW ONLY";

            var rs = stmt.executeQuery(sqlMaxRecaudacion);

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
*/