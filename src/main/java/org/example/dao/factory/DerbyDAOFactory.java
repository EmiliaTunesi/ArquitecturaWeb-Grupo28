package org.example.dao.factory;

import org.example.dao.interfaces.ClienteDAO;
import org.example.dao.interfaces.FactProductoDAO;
import org.example.dao.interfaces.FacturaDAO;
import org.example.dao.interfaces.ProductoDAO;
import org.example.dao.impl.ClienDAODerby;
import org.example.dao.impl.FacturDAODerby;
import org.example.dao.impl.FacturProdDAODerby;
import org.example.dao.impl.ProducDAODerby;
import org.example.utils.ConnectionFactory;
import org.example.utils.DerbyConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DerbyDAOFactory extends DAOFactory{
    private final ConnectionFactory connectionFactory;

    public DerbyDAOFactory(){
        this.connectionFactory = new DerbyConnectionFactory();
    }
    private Connection getConnection() throws SQLException{
        return connectionFactory.getConnection();
    }

    @Override
    public ClienteDAO getClientDAO() throws SQLException {
        return new ClienDAODerby(getConnection());
    }

    @Override
    public FacturaDAO getFactureDAO() throws SQLException {
        return new FacturDAODerby(getConnection());
    }

    @Override
    public ProductoDAO getProductDAO() throws SQLException {
        return new ProducDAODerby(getConnection());
    }

    @Override
    public FactProductoDAO getFacture_ProductDAO() throws SQLException {
        return new FacturProdDAODerby(getConnection());
    }
}
