import javax.swing.*;

public class MenuAdministrador extends JFrame{
    private JButton SALIRButton;
    private JRadioButton gestionarCajerosRadioButton;
    private JPanel panel_adminstrador;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField nom_producto;
    private JButton REGISTRARButton;
    private JTextField descrip_productos;
    private JTextField precio_prod;
    private JTextField stock_productos;
    private JTextField imagen_producto;
    private JTextField textField6;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton SALIRButton1;
    private JButton ACTUALIZARButton;
    private JTabbedPane tabbedPane3;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JButton SALIRButton2;
    private JButton REGISTRARButton1;

    public MenuAdministrador (){
        super("MENU ADMINSTRADOR");
        setContentPane(panel_adminstrador);

    }



    public void iniciar(){
        setVisible(true);
        setSize(600,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
