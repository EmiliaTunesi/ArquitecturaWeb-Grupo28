package org.example.app;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.dao.interfaces.ClienteDAO;
import org.example.dao.interfaces.ProductoDAO;
import org.example.dao.interfaces.FacturaDAO;
import org.example.dao.interfaces.FactProductoDAO;
import org.example.dao.factory.DAOFactory;
import org.example.entity.Cliente;
import org.example.entity.Producto;
import org.example.entity.Factura;
import org.example.entity.FacturaProducto;

import java.io.FileReader;
import java.sql.SQLException;

public class CargarDatos {

    public static void run() throws SQLException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.DERBY_JDBC);
        ClienteDAO clienteDAO = factory.getClientDAO();
        ProductoDAO productoDAO = factory.getProductDAO();
        FacturaDAO facturaDAO = factory.getFactureDAO();
        FactProductoDAO facturaProductoDAO = factory.getFacture_ProductDAO();

        try {
            leerClientesDesdeCSV(clienteDAO, "src/main/resources/clientes.csv");
            leerProductosDesdeCSV(productoDAO, "src/main/resources/productos.csv");
            leerFacturasDesdeCSV(facturaDAO, "src/main/resources/facturas.csv");
            leerFacturaProductosDesdeCSV(facturaProductoDAO, "src/main/resources/factura_producto.csv");

            System.out.println("Datos cargados exitosamente desde CSV.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void leerClientesDesdeCSV(ClienteDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            String nombre = row.get("nombre");
            if (nombre == null || nombre.isBlank()) {
                nombre = "";
            }

            String email = row.get("email");
            if (email == null || email.isBlank()) {
                email = "";
            }

            Cliente c = new Cliente(
                    Integer.parseInt(row.get("idCliente")),
                    nombre,
                    email
            );
            dao.insertar(c);
        }
    }

    private static void leerProductosDesdeCSV(ProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            String nombre = row.get("nombre");
            if (nombre == null || nombre.isBlank()) {
                nombre = "";
            }

            String valorStr = row.get("valor");
            float valor = 0;
            if (valorStr != null && !valorStr.isBlank()) {
                valor = Float.parseFloat(valorStr);
            }

            Producto p = new Producto(
                    Integer.parseInt(row.get("idProducto")),
                    nombre,
                    valor
            );
            dao.insertar(p);
        }
    }

    private static void leerFacturasDesdeCSV(FacturaDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idCliente = 0;
            String idClienteStr = row.get("idCliente");
            if (idClienteStr != null && !idClienteStr.isBlank()) {
                idCliente = Integer.parseInt(idClienteStr);
            }

            Factura f = new Factura(
                    Integer.parseInt(row.get("idFactura")),
                    idCliente
            );
            dao.insertar(f);
        }
    }

    private static void leerFacturaProductosDesdeCSV(FactProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idFactura = 0;
            int idProducto = 0;
            int cantidad = 0;

            String idFacturaStr = row.get("idFactura");
            if (idFacturaStr != null && !idFacturaStr.isBlank()) {
                idFactura = Integer.parseInt(idFacturaStr);
            }

            String idProductoStr = row.get("idProducto");
            if (idProductoStr != null && !idProductoStr.isBlank()) {
                idProducto = Integer.parseInt(idProductoStr);
            }

            String cantidadStr = row.get("cantidad");
            if (cantidadStr != null && !cantidadStr.isBlank()) {
                cantidad = Integer.parseInt(cantidadStr);
            }

            FacturaProducto fp = new FacturaProducto(
                    idFactura,
                    idProducto,
                    cantidad
            );
            dao.insertar(fp);
        }
    }
}

