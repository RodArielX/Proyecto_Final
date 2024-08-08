import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class MenuAdministrador extends JFrame{
    private JButton SALIRButton;
    private JPanel panel_adminstrador;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField nom_producto;
    private JButton REGISTRARButton;
    private JTextField descrip_productos;
    private JTextField precio_prod;
    private JTextField stock_productos;
    private JTextField imagen_producto;
    private JTextField id_produ;
    private JButton BUSCARPORIDButton;
    private JButton ELIMINARButton;
    private JTextField actua_nom_pro;
    private JTextField actua_descrip_pro;
    private JTextField actua_precio_pro;
    private JTextField actua_stock_pro;
    private JTextField actua_img_pro;
    private JButton SALIRButton1;
    private JButton ACTUALIZARButton;
    private JTabbedPane tabbedPane3;
    private JTextField regi_nom_cajero;
    private JTextField cedu_regi_cajero;
    private JTextField regi_apel_caje;
    private JTextField regi_direc_cajero;
    private JTextField regi_cel_cajero;
    private JTextField regi_edad_cajero;
    private JTextField regi_genero_cajero;
    private JButton SALIRButton2;
    private JButton REGISTRARButton1;
    private JTextField id_pro;
    private JTextField regi_user_cajero;
    private JTextField regi_contra_cajero;
    private JTextField actua_cedu_cajero;
    private JTextField actua_nom_cajero;
    private JTextField actua_apel_cajero;
    private JTextField actua_direc_cajero;
    private JTextField actua_cel_cajero;
    private JTextField actua_edad_cajero;
    private JTextField actua_genero_cajero;
    private JTextField actua_user_cajero;
    private JTextField actua_contra_cajero;
    private JButton ACTUALIZARButton1;
    private JButton SALIRButton3;
    private JTable tabla_productos;
    private JButton VISUALIZARPRODUCTOSButton;
    private JButton SALIRButton4;
    private JPanel panel_visua_elimi;
    private JTextField cedula_cajero;
    private JButton LIMPIARButton;
    private JButton SALIRButton5;
    private JButton ELIMINARButton1;
    private JLabel id_cajero;
    private JLabel cedu_cajero;
    private JLabel nom_cajero;
    private JLabel apel_cajero;
    private JLabel direc_cajero;
    private JLabel cel_cajero;
    private JLabel edad_cajero;
    private JLabel genero_cajero;
    private JLabel user_cajero;
    private JLabel contra_cajero;
    private JButton BUSQUEDAButton;
    private JTextField cedul_cajer;
    private JTable tabla_ventas;
    private JButton SALIRButton6;

    public MenuAdministrador (){
        super("MENU ADMINSTRADOR");
        setContentPane(panel_adminstrador);

        String[] columnNames = {"ID Producto", "Nombre Producto", "Descripción", "Precio", "Stock", "Imagen Producto"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tabla_productos.setModel(model);

        // Configurar el renderizador de celdas para la columna de imagen
        tabla_productos.getColumnModel().getColumn(5).setCellRenderer(new ImagenRender());
        try {
            cargarTodosLosProductos();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //VISUALIZAR  DETALLE

        String[] ventasColumnNames = {"ID Venta", "ID Producto","Nombre Cliente", "Cantidad","Nombre Cajero"};
        DefaultTableModel ventasModel = new DefaultTableModel(ventasColumnNames, 0);
        tabla_ventas.setModel(ventasModel);

        try {
            cargarTodosLosProductos1();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        //BOTON SALIR VENTANA REGISTRO PRODUCTO

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });

        //BOTON REGISTRAR PRODUCTOS
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegistrarProductos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //ACTUALIZACION DE PRODUCTOS
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarProductos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //BOTON REGISTRAR CAJEROS
        REGISTRARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegistrarCajeros();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //BOTON ACTUALIZAR CAJERO
        ACTUALIZARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ActualizarCajero();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        SALIRButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
        //BOTON ELIMINAR PRODUCTOS
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Supongamos que obtienes el productoId de un campo de texto
                    int productoId = Integer.parseInt(id_produ.getText());

                    // Llamar al método EliminarProductos
                    EliminarProductos(productoId);

                    JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID de producto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //BOTON QUE SE VISUALIZA TODOS LOS REGISTROS
        VISUALIZARPRODUCTOSButton.addActionListener(new ActionListener() {
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

        BUSQUEDAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InformacionCajero();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //BOTON ELMINAR CAJEROS
        ELIMINARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarCajero();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //BOTON PARA LIMPIAR LA INTERFAZ
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });
        SALIRButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
        SALIRButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });

        SALIRButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
        SALIRButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
        SALIRButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
    }
    //Cargar
    public void cargarTodosLosProductos() throws SQLException {
        Connection connection = conexion();
        String sql = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla_productos.getModel();
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

        DefaultTableModel model = (DefaultTableModel) tabla_ventas.getModel();
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

    //REGISTRAR PRODUCTOS

    public void RegistrarProductos() throws SQLException {
        String nombre = nom_producto.getText();
        String descripcion = descrip_productos.getText();
        String precio = precio_prod.getText();
        String stock = stock_productos.getText();
        String imagen = imagen_producto.getText();

        Connection connection = conexion();
        String sql = "INSERT INTO Productos (nombre, descripcion, precio, stock, imagen) values (?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, descripcion);
        pst.setFloat(3, Float.parseFloat(precio));
        pst.setInt(4, Integer.parseInt(stock));

        try {
            File imagenFile = new File(imagen);
            FileInputStream fis = new FileInputStream(imagenFile);
            pst.setBlob(5, fis);

            int row = pst.executeUpdate();
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        nom_producto.setText("");
        descrip_productos.setText("");
        precio_prod.setText("");
        stock_productos.setText("");
        imagen_producto.setText("");

        pst.close();
        connection.close();
    }


    //ACTUALIZAR PRODUCTOS

    public void ActualizarProductos() throws SQLException {
        String id_producto = id_pro.getText();
        String nombre_upd = actua_nom_pro.getText();
        String descrip_upd = actua_descrip_pro.getText();
        String precio_upd = actua_precio_pro.getText();
        String stock_upd = actua_stock_pro.getText();
        String img_upd = actua_img_pro.getText();

        Connection connection = null;
        PreparedStatement checkPst = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        FileInputStream fis = null;

        try {
            connection = conexion();

            // Check if the product exists
            String checkSql = "SELECT COUNT(*) FROM Productos WHERE producto_id = ?";
            checkPst = connection.prepareStatement(checkSql);
            checkPst.setString(1, id_producto);
            rs = checkPst.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                String sql = "UPDATE Productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, imagen = ? WHERE producto_id = ?";
                pst = connection.prepareStatement(sql);
                pst.setString(1, nombre_upd);
                pst.setString(2, descrip_upd);
                pst.setFloat(3, Float.parseFloat(precio_upd));
                pst.setInt(4, Integer.parseInt(stock_upd));

                // Check if the file exists and is accessible
                File imagenFile = new File(img_upd);
                if (imagenFile.exists() && !imagenFile.isDirectory()) {
                    try {
                        fis = new FileInputStream(imagenFile);
                        pst.setBlob(5, fis);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo de imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo de imagen no existe o no es accesible.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pst.setInt(6, Integer.parseInt(id_producto));

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (checkPst != null) {
                try {
                    checkPst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        id_pro.setText("");
        actua_nom_pro.setText("");
        actua_descrip_pro.setText("");
        actua_precio_pro.setText("");
        actua_stock_pro.setText("");
        actua_img_pro.setText("");
    }




    //ELIMINAR PRODUCTOS

    public void EliminarProductos(int productoId) throws SQLException {
        Connection connection = conexion();

        // Primero elimina los registros hijos en detalleventa
        String deleteDetalleVenta = "DELETE FROM DetalleVenta WHERE id_producto = ?";
        PreparedStatement pstmtDeleteDetalleVenta = connection.prepareStatement(deleteDetalleVenta);
        pstmtDeleteDetalleVenta.setInt(1, productoId);
        pstmtDeleteDetalleVenta.executeUpdate();
        pstmtDeleteDetalleVenta.close();

        // Luego elimina el registro en productos
        String deleteProducto = "DELETE FROM Productos WHERE producto_id = ?";
        PreparedStatement pstmtDeleteProducto = connection.prepareStatement(deleteProducto);
        pstmtDeleteProducto.setInt(1, productoId);
        pstmtDeleteProducto.executeUpdate();
        pstmtDeleteProducto.close();


        connection.close();
    }


    public void BusquedaTotal() throws SQLException{
        Connection connection = conexion();
        String query = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        DefaultTableModel model = (DefaultTableModel) tabla_productos.getModel();
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


    public void Busqueda()throws SQLException {
        Connection connection = conexion();
        String query = "SELECT producto_id, nombre, descripcion, precio, stock, imagen FROM Productos WHERE producto_id = ?;";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, Integer.parseInt(id_produ.getText()));
        ResultSet rs = pstmt.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla_productos.getModel();
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


    //REGISTRAR CAJEROS

    public void RegistrarCajeros()throws SQLException{
        String cedula_cajero = cedu_regi_cajero.getText();
        String nombres_cajero = regi_nom_cajero.getText();
        String apellidos_cajero = regi_apel_caje.getText();
        String direccion_cajero = regi_direc_cajero.getText();
        String telefono_cajero = regi_cel_cajero.getText();
        String edad_cajero = regi_edad_cajero.getText();
        String genero_cajero = regi_genero_cajero.getText();
        String usuario_cajero = regi_user_cajero.getText();
        String contraseña_cajero = regi_contra_cajero.getText();


        Connection connection = conexion();
        String sql = "INSERT INTO Cajero (cedula_cajero, nombres_cajero, apellidos_cajero, direccion_cajero, telefono_cajero, edad_cajero, genero_cajero, usuario_cajero, contraseña_cajero)values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,Integer.parseInt(cedula_cajero));
        pst.setString(2,nombres_cajero);
        pst.setString(3,apellidos_cajero);
        pst.setString(4,direccion_cajero);
        pst.setInt(5,Integer.parseInt(telefono_cajero));
        pst.setInt(6,Integer.parseInt(edad_cajero));
        pst.setString(7,genero_cajero);
        pst.setString(8, usuario_cajero);
        pst.setString(9, contraseña_cajero);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente", "Éxito",JOptionPane.INFORMATION_MESSAGE);
        }
        cedu_regi_cajero.setText("");
        regi_nom_cajero.setText("");
        regi_apel_caje.setText("");
        regi_direc_cajero.setText("");
        regi_cel_cajero.setText("");
        regi_edad_cajero.setText("");
        regi_genero_cajero.setText("");
        regi_user_cajero.setText("");
        regi_contra_cajero.setText("");

        pst.close();
        connection.close();
    }

    //ACTUALIZAR CAJEROS

    public void ActualizarCajero()throws SQLException{
        //String id_cajero_upd = id_cajero.getText();
        String cedula_cajero_upd = actua_cedu_cajero.getText();
        String nombres_cajero_upd = actua_nom_cajero.getText();
        String apellidos_cajero_upd = actua_apel_cajero.getText();
        String direccion_cajero_upd = actua_direc_cajero.getText();
        String telefono_cajero_upd = actua_cel_cajero.getText();
        String edad_cajero_upd = actua_edad_cajero.getText();
        String genero_cajero_upd = actua_genero_cajero.getText();
        String usuario_cajero_upd = actua_user_cajero.getText();
        String contraseña_cajero_upd = actua_contra_cajero.getText();

        Connection connection = conexion();
        String checkSql = "SELECT COUNT(*) FROM Cajero WHERE cedula_cajero = ?";
        PreparedStatement checkPst = connection.prepareStatement(checkSql);
        checkPst.setString(1, cedula_cajero_upd);
        ResultSet rs = checkPst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0){
            String sql = "UPDATE Cajero set nombres_cajero = ?, apellidos_cajero = ?, direccion_cajero = ?, telefono_cajero = ?, edad_cajero = ?, genero_cajero = ?, usuario_cajero = ?, contraseña_cajero = ? where cedula_cajero = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,nombres_cajero_upd);
            pst.setString(2,apellidos_cajero_upd);
            pst.setString(3,direccion_cajero_upd);
            pst.setInt(4,Integer.parseInt(telefono_cajero_upd));
            pst.setInt(5,Integer.parseInt(edad_cajero_upd));
            pst.setString(6,genero_cajero_upd);
            pst.setString(7,usuario_cajero_upd);
            pst.setString(8,contraseña_cajero_upd);
            pst.setInt(9,Integer.parseInt(cedula_cajero_upd));


            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Cajero actualizado exitosamente.","Éxito",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la informacion del Cajero.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún Cajero con la cédula digitada.","Error",JOptionPane.ERROR_MESSAGE);
        }
        actua_cedu_cajero.setText("");
        actua_nom_cajero.setText("");
        actua_apel_cajero.setText("");
        actua_direc_cajero.setText("");
        actua_cel_cajero.setText("");
        actua_edad_cajero.setText("");
        actua_genero_cajero.setText("");
        actua_user_cajero.setText("");
        actua_contra_cajero.setText("");
    }

    //VISUALIZAR INFORMACION CAJEROS
    public void  InformacionCajero() throws SQLException {
        String cedula_cajero = cedul_cajer.getText();
        Connection conectar = conexion();
        String sql = "SELECT * FROM Cajero WHERE cedula_cajero = ?";
        PreparedStatement strm = conectar.prepareStatement(sql);
        strm.setString(1, cedula_cajero);
        ResultSet rs = strm.executeQuery();
        if (rs.next()) {
            String busqueda = rs.getString("cedula_cajero");
            if (busqueda.equals(cedula_cajero)) {
                id_cajero.setText(rs.getString("cajero_id"));
                cedu_cajero.setText(rs.getString("cedula_cajero"));
                nom_cajero.setText(rs.getString("nombres_cajero"));
                apel_cajero.setText(rs.getString("apellidos_cajero"));
                direc_cajero.setText(rs.getString("direccion_cajero"));
                cel_cajero.setText(rs.getString("telefono_cajero"));
                edad_cajero.setText(rs.getString("edad_cajero"));
                genero_cajero.setText(rs.getString("genero_cajero"));
                user_cajero.setText(rs.getString("usuario_cajero"));
                contra_cajero.setText(rs.getString("contraseña_cajero"));
                cedul_cajer.setText("");
            }

        }
    }
    //LIMPIAR INTERFAZ
    public void Limpiar(){
        id_cajero.setText("");
            cedu_cajero.setText("");
            nom_cajero.setText("");
            apel_cajero.setText("");
            direc_cajero.setText("");
            cel_cajero.setText("");
            edad_cajero.setText("");
            genero_cajero.setText("");
            user_cajero.setText("");
            contra_cajero.setText("");
    }
    //ELIMINAR CAJEROS
    public void EliminarCajero()throws SQLException{
        String cedula_cajero= cedul_cajer.getText();
        Connection connection = conexion();
        String sql = "DELETE FROM Cajero where cedula_cajero = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,cedula_cajero);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Cajero eliminado correctamente","",JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,"No se encontro ningun Cajero con el numero de cedula dado", "Error",JOptionPane.ERROR_MESSAGE);
        }
        cedul_cajer.setText("");
        connection.close();
        pst.close();
    }




    public void iniciar(){
        setVisible(true);
        setSize(770,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
