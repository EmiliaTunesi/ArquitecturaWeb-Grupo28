package org.example.dao;

import org.apache.commons.csv.CSVParser;

public interface dao <T> {

    void createTable() throws Exception;
    void dropTable() throws Exception;

    // CRUD
    void loadCSVData(CSVParser data) throws Exception;
    T get(int id) throws Exception;
    void update(T entity, String[] params) throws Exception;
    void delete(T entity) throws Exception;
}
