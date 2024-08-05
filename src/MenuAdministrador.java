import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame{
    private JButton SALIRButton;
    private JRadioButton gestionarCajerosRadioButton;
    private JPanel panel_adminstrador;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField textField1;
    private JButton REGISTRARButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
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
        gestionarCajerosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCrudAdminstrador ventana_crudAdmin = new MenuCrudAdminstrador();
                ventana_crudAdmin.iniciar();
                dispose();
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_inicio = new Login();
                ventana_inicio.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
