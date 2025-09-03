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
            String idClienteStr = row.get("idCliente");
            if (idClienteStr == null || idClienteStr.trim().isEmpty()) {
                System.err.println("Saltando fila - idCliente vacío o null");
                continue;
            }

            String nombre = row.get("nombre");
            if (nombre == null || nombre.trim().isEmpty()) nombre = "";

            String email = row.get("email");
            if (email == null || email.trim().isEmpty()) email = "";

            try {
                Cliente c = new Cliente(
                        Integer.parseInt(idClienteStr.trim()),
                        nombre.trim(),
                        email.trim()
                );
                dao.insertar(c);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing idCliente: " + idClienteStr);
            }
        }
    }

    private static void leerProductosDesdeCSV(ProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            String idProductoStr = row.get("idProducto");
            if (idProductoStr == null || idProductoStr.trim().isEmpty()) {
                System.err.println("Saltando fila - idProducto vacío o null");
                continue;
            }

            String nombre = row.get("nombre");
            if (nombre == null || nombre.trim().isEmpty()) nombre = "";

            float valor = 0;
            String valorStr = row.get("valor");
            if (valorStr != null && !valorStr.trim().isEmpty()) {
                try {
                    valor = Float.parseFloat(valorStr.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing valor: " + valorStr);
                }
            }

            try {
                Producto p = new Producto(
                        Integer.parseInt(idProductoStr.trim()),
                        nombre.trim(),
                        valor
                );
                dao.insertar(p);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing idProducto: " + idProductoStr);
            }
        }
    }

    private static void leerFacturasDesdeCSV(FacturaDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            String idFacturaStr = row.get("idFactura");
            if (idFacturaStr == null || idFacturaStr.trim().isEmpty()) {
                System.err.println("Saltando fila - idFactura vacío o null");
                continue;
            }

            int idFactura = 0;
            try {
                idFactura = Integer.parseInt(idFacturaStr.trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing idFactura: " + idFacturaStr);
                continue;
            }

            int idCliente = 0;
            String idClienteStr = row.get("idCliente");
            if (idClienteStr != null && !idClienteStr.trim().isEmpty()) {
                try {
                    idCliente = Integer.parseInt(idClienteStr.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing idCliente: " + idClienteStr);
                }
            }

            Factura f = new Factura(idFactura, idCliente);
            dao.insertar(f);
        }
    }

    private static void leerFacturaProductosDesdeCSV(FactProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            String idFacturaStr = row.get("idFactura");
            String idProductoStr = row.get("idProducto");
            String cantidadStr = row.get("cantidad");

            if (idFacturaStr == null || idFacturaStr.trim().isEmpty()) {
                System.err.println("Saltando fila - idFactura vacío o null");
                continue;
            }
            if (idProductoStr == null || idProductoStr.trim().isEmpty()) {
                System.err.println("Saltando fila - idProducto vacío o null");
                continue;
            }

            int idFactura = 0;
            int idProducto = 0;
            int cantidad = 0;

            try {
                idFactura = Integer.parseInt(idFacturaStr.trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing idFactura: " + idFacturaStr);
                continue;
            }

            try {
                idProducto = Integer.parseInt(idProductoStr.trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing idProducto: " + idProductoStr);
                continue;
            }

            if (cantidadStr != null && !cantidadStr.trim().isEmpty()) {
                try {
                    cantidad = Integer.parseInt(cantidadStr.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing cantidad: " + cantidadStr);
                }
            }

            FacturaProducto fp = new FacturaProducto(idFactura, idProducto, cantidad);
            dao.insertar(fp);
        }
    }
}

