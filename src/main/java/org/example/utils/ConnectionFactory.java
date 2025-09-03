package org.example.utils;

import java.sql.Connection;
import java.sql.SQLException;
//Interfaz a implementar si quisieramos conectar con otra base de datos
//que no sea Derby, por ejemplo creamos una MYSQLconnection e implementamos esto
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
}