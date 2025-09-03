package org.example.factories;

import org.example.dao.ClientDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DerbyDAOFactory extends AbstractFactory{
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URI = "jdbc:derby:memory:integradorDB;create=true";
    // 👆 crea una DB en memoria llamada "integradorDB"

    private static DerbyDAOFactory instance = new DerbyDAOFactory();
    private static Connection connection;

    private DerbyDAOFactory() {}

    public static synchronized DerbyDAOFactory getInstance() {
        if (instance == null) {
            instance = new DerbyDAOFactory();
        }
        return instance;
    }

    @Override
    public Connection connect() throws Exception {
        if (connection == null) {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URI);
            connection.setAutoCommit(false);
        }
        return connection;
    }

    @Override
    public void disconnect() throws Exception {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    @Override
    public ClientDAO getClientDAO() {
        try {
            return new ClientDAO(connect());
        } catch (Exception e) {
            throw new RuntimeException("Error creando ClientDAO", e);
        }
    }



    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAO();
    }
}
