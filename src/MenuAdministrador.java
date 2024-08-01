import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame{
    private JButton SALIRButton;
    private JRadioButton gestionarCajerosRadioButton;
    private JRadioButton revisarVentasRadioButton;
    private JRadioButton gestionarProductosRadioButton;
    private JPanel panel_adminstrador;

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
