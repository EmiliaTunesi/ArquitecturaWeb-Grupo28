package org.example.app;

import org.example.dao.factory.DAOFactory;
import org.example.dao.factory.DerbyDAOFactory;
import org.example.dao.interfaces.ClienteDAO;
import org.example.entity.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //acá ir armando la app, es decir ir llamando
        CrearEsquema.run();
        CargarDatos.run();
        DevolverMaxRecaudacion.run();
        ClienteConMasFacturas.run();


        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.DERBY_JDBC);
        if (factory == null) {
            throw new RuntimeException("No se pudo crear la factory para Derby");
        }

        ClienteDAO clienteDAO = factory.getClientDAO();
        if (clienteDAO == null) {
            throw new RuntimeException("No se pudo crear el ClienteDAO");
        }

        List<Cliente> lista = clienteDAO.obtenerTodos();

        for (Cliente c : lista) {
            System.out.println(c.getIdClient() + " - " + c.getNombre() + " - " + c.getEmail());
        }
        try {
            java.sql.DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // Esta excepción es normal al cerrar Derby embebido
            System.out.println("Derby cerrado correctamente.");
        }

    }
}