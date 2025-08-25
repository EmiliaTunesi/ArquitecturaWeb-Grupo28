package org.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class BaseDeDatos {

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

                String table = "CREATE TABLE persona("
                        + "id INT, "
                        + "nombre VARCHAR(500),"
                        + "edad INT,"
                        + "PRIMARY KEY(id))";

                conn.prepareStatement(table).execute();
                conn.commit();

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return;
        }


    }


