import javax.swing.*;

public class Login extends JFrame {
    private JPanel panel_login;
    private JButton INGRESARButton;
    private JTextField user;
    private JPasswordField contra;
    private JComboBox comboBox1;

    public Login(){
        super("INICIO DE SESION");
        setContentPane(panel_login);
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
