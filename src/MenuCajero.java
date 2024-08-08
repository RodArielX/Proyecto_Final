import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MenuCajero extends JFrame {
    private JTabbedPane tabla_ventas;
    private JPanel panel_menucajero;
    private JTextField id_pro_compra;
    private JButton BUSCARPORIDButton;
    private JTable tabla_compra_pro;
    private JButton VISUALIZARTODOButton;
    private JButton SALIRButton;
    private JButton COMPRARButton;
    private JTextField cantidad_compra;
    private JTextField nombre_cliente;
    private JButton VISUALIZARCOMPRAButton;
    private JButton FACTURAButton;
    private JTextField nom_cajero;
    private JTable table_ventas;
    private JButton VISUALIZARButton;
    private JButton GENERARFACTURAButton;

    public MenuCajero(){
        super("MENU CAJERO");
        setContentPane(panel_menucajero);

        String[] columnNames = {"ID Producto", "Nombre Producto", "Descripción", "Precio", "Stock", "Imagen Producto"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tabla_compra_pro.setModel(model);

        // Configurar el renderizador de celdas para la columna de imagen
        tabla_compra_pro.getColumnModel().getColumn(5).setCellRenderer(new ImagenRender());
        try {
            cargarTodosLosProductos();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String[] ventasColumnNames = {"ID Venta", "ID Producto","Nombre Cliente", "Cantidad","Nombre Cajero"};
        DefaultTableModel ventasModel = new DefaultTableModel(ventasColumnNames, 0);
        table_ventas.setModel(ventasModel);

        try {
            cargarTodosLosProductos1();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        VISUALIZARTODOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BusquedaTotal();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BUSCARPORIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Busqueda();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        COMPRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarCompra();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        VISUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BusquedaDetalle();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        FACTURAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarFactura();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
    }

    public void cargarTodosLosProductos() throws SQLException {
        Connection connection = conexion();
        String sql = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla_compra_pro.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            int id = rs.getInt("producto_id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            int stock = rs.getInt("stock");
            byte[] imgBytes = rs.getBytes("imagen");

            ImageIcon icon = null;
            if (imgBytes != null) {
                Image img = Toolkit.getDefaultToolkit().createImage(imgBytes);
                icon = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            }

            model.addRow(new Object[]{id, nombre, descripcion, precio, stock, icon});
        }

        rs.close();
        pstmt.close();
        connection.close();
    }

    public void cargarTodosLosProductos1() throws SQLException {
        Connection connection = conexion();
        String sql = "SELECT * FROM DetalleVenta";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) table_ventas.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            int id_vemta = rs.getInt("id_detalle");
            int id_pro = rs.getInt("id_producto");
            String nom_cli = rs.getString("nombre_cliente");
            int cantidad = rs.getInt("cantidad");
            String nom_cajero = rs.getString("nombre_cajero");

            model.addRow(new Object[]{id_vemta, id_pro, nom_cli, cantidad, nom_cajero});
        }
        rs.close();
        pstmt.close();
        connection.close();
    }

    //CONEXION A LA BASE DE DATOS
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/TiendaAccesorios";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }
    //BUSQUEDA  TOTAL PRODUCTOS
    public void BusquedaTotal() throws SQLException{
        Connection connection = conexion();
        String query = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        DefaultTableModel model = (DefaultTableModel) tabla_compra_pro.getModel();
        model.setRowCount(0); // Clear existing data
        while (rs.next()) {
            Integer id_pro = rs.getInt("producto_id");
            String nombre_producto = rs.getString("nombre");
            String descripcion_producto = rs.getString("descripcion");
            String precio_producto = rs.getString("precio");
            String stock_producto = rs.getString("stock");
            String imagen_producto = rs.getString("imagen");

            model.addRow(new Object[]{id_pro, nombre_producto, descripcion_producto, precio_producto, stock_producto, imagen_producto});
        }
    }
    //BUSQUEDA TOTAL DETALLE
    public void BusquedaDetalle() throws SQLException{
        Connection connection = conexion();
        String query = "SELECT * FROM DetalleVenta;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        DefaultTableModel model = (DefaultTableModel) table_ventas.getModel();
        model.setRowCount(0); // Clear existing data
        while (rs.next()) {
            Integer id_detalle = rs.getInt("id_detalle");
            String id_produ = rs.getString("id_producto");
            String nom_cli = rs.getString("nombre_cliente");
            String stock = rs.getString("cantidad");
            String nom_caje = rs.getString("nombre_cajero");

            model.addRow(new Object[]{id_detalle, id_produ, nom_cli, stock, nom_caje});
        }
    }


    public void Busqueda()throws SQLException {
        Connection connection = conexion();
        String query = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos WHERE producto_id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, Integer.parseInt(id_pro_compra.getText()));
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla_compra_pro.getModel();
        model.setRowCount(0); // Clear existing data
        while (rs.next()) {
            Integer id_produ = rs.getInt("producto_id");
            String nombre_produ = rs.getString("nombre");
            String descripcion_produ = rs.getString("descripcion");
            String precio_produ = rs.getString("precio");
            String stock_produ = rs.getString("stock");
            String imagen_produ = rs.getString("imagen");
            model.addRow(new Object[]{id_produ, nombre_produ, descripcion_produ, precio_produ, stock_produ, imagen_produ});
        }
    }

    public void realizarCompra() throws SQLException {
        int productoId = Integer.parseInt(id_pro_compra.getText());
        int cantidad = Integer.parseInt(cantidad_compra.getText());
        String nombreCliente = nombre_cliente.getText();
        String nombreCajero = nom_cajero.getText();

        Connection connection = conexion();
        String querySelect = "SELECT stock FROM Productos WHERE producto_id = ?";
        PreparedStatement pstmtSelect = connection.prepareStatement(querySelect);
        pstmtSelect.setInt(1, productoId);
        ResultSet rs = pstmtSelect.executeQuery();

        if (rs.next()) {
            int stockActual = rs.getInt("stock");
            if (cantidad > stockActual) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock disponible.");
            } else {
                String queryUpdate = "UPDATE Productos SET stock = ? WHERE producto_id = ?";
                PreparedStatement pstmtUpdate = connection.prepareStatement(queryUpdate);
                pstmtUpdate.setInt(1, stockActual - cantidad);
                pstmtUpdate.setInt(2, productoId);
                pstmtUpdate.executeUpdate();

                // Insertar en DetalleVenta
                String queryInsert = "INSERT INTO DetalleVenta (id_producto, nombre_cliente, cantidad, nombre_cajero) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmtInsert = connection.prepareStatement(queryInsert);
                pstmtInsert.setInt(1, productoId);
                pstmtInsert.setString(2, nombreCliente);
                pstmtInsert.setInt(3, cantidad);
                pstmtInsert.setString(4, nombreCajero);
                pstmtInsert.executeUpdate();

                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");

                // Actualizar tabla después de la compra
                cargarTodosLosProductos();
            }
            id_pro_compra.setText("");
            cantidad_compra.setText("");
            nombre_cliente.setText("");
            nom_cajero.setText("");
        }
    }

    public void generarFactura() throws SQLException {
        int productoId = Integer.parseInt(id_pro_compra.getText());
        int cantidad = Integer.parseInt(cantidad_compra.getText());
        String cliente = nombre_cliente.getText();

        Connection connection = conexion();
        String querySelect = "SELECT nombre, descripcion, precio FROM Productos WHERE producto_id = ?";
        PreparedStatement pstmtSelect = connection.prepareStatement(querySelect);
        pstmtSelect.setInt(1, productoId);
        ResultSet rs = pstmtSelect.executeQuery();

        if (rs.next()) {
            String nombreProducto = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");

            double subtotal = precio * cantidad;
            double iva = subtotal * 0.15; // IVA del 15%
            double total = subtotal + iva;

            String factura = "Nombre del cliente: " + cliente + "\n" +
                    "Producto: " + nombreProducto + "\n" +
                    "Descripción: " + descripcion + "\n" +
                    "Cantidad: " + cantidad + "\n" +
                    "Subtotal: " + subtotal + "\n" +
                    "IVA: " + iva + "\n" +
                    "Total a pagar: " + total;

            // Mostrar la factura en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, factura, "Factura", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        pstmtSelect.close();
        connection.close();
    }


    public void iniciar(){
        setVisible(true);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
