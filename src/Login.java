import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * La clase {@code Login} representa una interfaz gráfica de usuario para la autenticación de usuarios.
 * Permite a los usuarios iniciar sesión como Administrador o Cajero, verificando las credenciales a través
 * de una base de datos.
 * @author Ariel Ashqui
 * @version 1.0
 */
public class Login extends JFrame {
    private JPanel panel_login;
    private JButton INGRESARButton;
    private JTextField user;
    private JPasswordField contra;
    private JComboBox comboBox1;

    /**
     * Constructor de la clase {@code Login}. Configura la ventana de inicio de sesión,
     * inicializa los componentes GUI y establece las acciones correspondientes para el botón de ingreso.
     */
    public Login() {
        super("INICIO DE SESION");
        setContentPane(panel_login);
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Administrador", "Cajero"}));
        INGRESARButton.addActionListener(new ActionListener() {
            /**
             * Maneja la acción del botón "INGRESAR". Llama al método {@code inicioSesion()}
             * para validar las credenciales del usuario.
             *
             * @param e el evento de acción que se inicia cuando se presiona el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inicioSesion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Establece una conexión con la base de datos.
     * @return un objeto {@code Connection} que representa la conexión con la base de datos.
     * @throws SQLException si ocurre un error al intentar establecer la conexión.
     */
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://uribtjckkrlatjt6:pIvotCQYz3hHKknvtLIw@bpr7lmf7sv069orulopo-mysql.services.clever-cloud.com:3306/bpr7lmf7sv069orulopo";
        String user = "uribtjckkrlatjt6";
        String password = "pIvotCQYz3hHKknvtLIw";

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Verifica las credenciales del usuario y determina si puede iniciar sesión
     * como Administrador o Cajero. Si las credenciales son correctas, se abre el
     * menú correspondiente; de lo contrario, se muestra un mensaje de error.
     * @throws SQLException si ocurre un error al interactuar con la base de datos.
     */
    public void inicioSesion() throws SQLException {
        String usuario = user.getText();
        String contraseña = new String(contra.getPassword());
        String userType = (String) comboBox1.getSelectedItem();

        Connection connection = conexion();
        String sql = "";

        if ("Administrador".equals(userType)) {
            sql = "SELECT * FROM Administrador WHERE usuario_admin = ? AND contraseña_admin = ?";
        } else if ("Cajero".equals(userType)) {
            sql = "SELECT * FROM Cajero WHERE usuario_cajero = ? AND contraseña_cajero = ?";
        }

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, usuario);
        pst.setString(2, contraseña);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            if ("Administrador".equals(userType)) {
                MenuAdministrador ventana_admin = new MenuAdministrador();
                ventana_admin.iniciar();
                dispose();
            } else if ("Cajero".equals(userType)) {
                MenuCajero ventana_cajero = new MenuCajero();
                ventana_cajero.iniciar();
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta. Intente de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        user.setText("");
        contra.setText("");
        pst.close();
        connection.close();
    }

    /**
     * Inicia la ventana de inicio de sesión, configurando su visibilidad, tamaño,
     * ubicación y acción predeterminada al cerrar.
     */
    public void iniciar() {
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
