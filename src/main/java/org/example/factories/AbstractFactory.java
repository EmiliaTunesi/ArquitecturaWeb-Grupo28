package org.example.factories;

import java.sql.Connection;

public class AbstractFactory    {
    public static final int MYSQL = 1;
    public static final int DERBY = 2; // 👈 nueva constante

    public static AbstractFactory getDAOFactory(int factory) {
        switch (factory) {
            case DERBY: return DerbyDAOFactory.getInstance(); // 👈 nuevo caso
            default: return null;
        }
    }

    public abstract Connection connect() throws Exception;
    public abstract void disconnect() throws Exception;

    public abstract ClientDAO getClientDAO();
    public abstract InvoiceDAO getInvoiceDAO();
    public abstract InvoiceProductDAO getInvoiceProductDAO();
    public abstract ProductDAO getProductDAO();
}
