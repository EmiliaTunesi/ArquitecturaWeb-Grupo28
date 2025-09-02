package org.example.app;

import org.example.dao.factory.DAOFactory;
import org.example.dao.factory.DerbyDAOFactory;
import org.example.dao.interfaces.ClienteDAO;
import org.example.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //acá ir armando la app, es decir ir llamando
        CrearEsquema.run();
        CargarDatos.run();


        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.DERBY_JDBC);
        ClienteDAO clienteDAO = factory.getClientDAO();

        List<Cliente> lista = clienteDAO.obtenerTodos();

        for (Cliente c : lista) {
            System.out.println(c.getIdClient() + " - " + c.getNombre() + " - " + c.getEmail());
        }

    }
}