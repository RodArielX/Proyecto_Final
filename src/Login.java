import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JPanel panel_login;
    private JButton INGRESARButton;
    private JTextField user;
    private JPasswordField contra;
    private JComboBox comboBox1;

    public Login(){
        super("INICIO DE SESION");
        setContentPane(panel_login);
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Administrador", "Cajero"}));
        INGRESARButton.addActionListener(new ActionListener() {
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

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/TiendaAccesorios";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }

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

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
