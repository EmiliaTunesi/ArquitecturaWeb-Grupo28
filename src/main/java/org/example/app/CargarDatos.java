package org.example.app;
//DESCOMENTAR ESTA CLASE CUANDO TENGAMOS LOS DAO y las entitys
/* import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.dao.ClienteDAO;
import org.example.dao.ProductoDAO;
import org.example.dao.FacturaDAO;
import org.example.dao.FacturaProductoDAO;
import org.example.dao.factory.DAOFactory;
import org.example.entity.Cliente;
import org.example.entity.Producto;
import org.example.entity.Factura;
import org.example.entity.FacturaProducto;

import java.io.FileReader;

public class CargarDatos {

    public static void run() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.DERBY);
        ClienteDAO clienteDAO = factory.getClienteDAO();
        ProductoDAO productoDAO = factory.getProductoDAO();
        FacturaDAO facturaDAO = factory.getFacturaDAO();
        FacturaProductoDAO facturaProductoDAO = factory.getFacturaProductoDAO();

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
            Cliente c = new Cliente(
                    Integer.parseInt(row.get("idCliente")),
                    row.get("nombre"),
                    row.get("email")
            );
            dao.insertar(c);
        }
    }

    private static void leerProductosDesdeCSV(ProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            Producto p = new Producto(
                    Integer.parseInt(row.get("idProducto")),
                    row.get("nombre"),
                    Float.parseFloat(row.get("valor"))
            );
            dao.insertar(p);
        }
    }

    private static void leerFacturasDesdeCSV(FacturaDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            Factura f = new Factura(
                    Integer.parseInt(row.get("idFactura")),
                    Integer.parseInt(row.get("idCliente"))
            );
            dao.insertar(f);
        }
    }

    private static void leerFacturaProductosDesdeCSV(FacturaProductoDAO dao, String path) throws Exception {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            FacturaProducto fp = new FacturaProducto(
                    Integer.parseInt(row.get("idFactura")),
                    Integer.parseInt(row.get("idProducto")),
                    Integer.parseInt(row.get("cantidad"))
            );
            dao.insertar(fp);
        }
    }
} */
