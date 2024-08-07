import javax.swing.*;

public class MenuCajero extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel_menucajero;
    private JTextField id_pro_compra;
    private JButton BUSCARButton;
    private JTable tabla_compra_pro;
    private JButton COMPRARButton;
    private JButton SALIRButton;
    private JButton NOTADEVENTAButton;

    public MenuCajero(){
        super("MENU CAJERO");
        setContentPane(panel_menucajero);
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
