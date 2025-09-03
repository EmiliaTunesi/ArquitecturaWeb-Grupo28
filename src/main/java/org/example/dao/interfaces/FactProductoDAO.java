package org.example.dao.interfaces;

import org.example.entity.Cliente;
import org.example.entity.FacturaProducto;

import java.util.List;

public interface FactProductoDAO {
    void insertar(FacturaProducto fac);
    FacturaProducto obtenerPorId(int id);
    List<FacturaProducto> obtenerTodos();
    void actualizar(FacturaProducto fac);
    void eliminar(int id);
}
