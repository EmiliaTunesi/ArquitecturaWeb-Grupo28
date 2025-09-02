package org.example.dao.factory;

import org.example.dao.interfaces.ClienteDAO;
import org.example.dao.interfaces.FactProductoDAO;
import org.example.dao.interfaces.FacturaDAO;
import org.example.dao.interfaces.ProductoDAO;

import java.sql.SQLException;

public abstract class DAOFactory {
    //public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public abstract ClienteDAO getClientDAO() throws SQLException;
    public abstract FacturaDAO getFactureDAO() throws SQLException;
    public abstract ProductoDAO getProductDAO() throws SQLException;
    public abstract FactProductoDAO getFacture_ProductDAO() throws SQLException;

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            //case MYSQL_JDBC:
                //return new MYSQLDaoFactory(); // en caso de conectar con mysql
            case DERBY_JDBC:
                return new DerbyDAOFactory();
            default:
                return null;
        }
    }
}

