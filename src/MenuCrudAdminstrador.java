import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCrudAdminstrador extends JFrame{
    private JButton MENUADMINISTRADORButton;
    private JRadioButton ingresoCajeroRadioButton;
    private JRadioButton visualizarCajeroRadioButton;
    private JRadioButton actualizarCajeroRadioButton;
    private JRadioButton eliminarCajeroRadioButton;
    private JPanel panel_crudAdmini;
    private JRadioButton SALIRRadioButton;

    public MenuCrudAdminstrador(){
        super("CRUD ADMINISTRADOR");
        setContentPane(panel_crudAdmini);
        MENUADMINISTRADORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuAdministrador ventana_menuadmin = new MenuAdministrador();
                ventana_menuadmin.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
