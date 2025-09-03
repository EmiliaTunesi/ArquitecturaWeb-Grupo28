package org.example.dao;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.Entity.ClientEntity;
import org.example.factories.AbstractFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientDAO implements dao<ClientEntity>{



    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS clients (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "email VARCHAR(100))";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void dropTable() throws Exception {
        String sql = "DROP TABLE IF EXISTS clients";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void loadCSVData(CSVParser data) throws Exception {
        String sql = "INSERT INTO clients(name, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            data.forEach(row -> {
                try {
                    pstmt.setString(1, row.get("name"));
                    pstmt.setString(2, row.get("email"));
                    pstmt.addBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            pstmt.executeBatch();
        }
    }

    @Override
    public ClientEntity get(int id) throws Exception {
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ClientEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }

    @Override
    public void update(ClientEntity client, String[] params) throws Exception {
        // ejemplo: params[0] = name, params[1] = email
        String sql = "UPDATE clients SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, params[0]);
            pstmt.setString(2, params[1]);
            pstmt.setInt(3, client.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(ClientEntity client) throws Exception {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, client.getId());
            pstmt.executeUpdate();
        }
    }
}
