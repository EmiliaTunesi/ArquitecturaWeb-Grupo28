package org.example.dao.interfaces;

import org.example.entity.Cliente;
import org.example.entity.Producto;

import java.util.List;

public interface ProductoDAO {
    void insertar(Producto p);
    Producto obtenerPorId(int id);
    List<Producto> obtenerTodos();
    void actualizar(Producto p);
    void eliminar(int id);
}
