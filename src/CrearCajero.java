import javax.swing.*;

public class CrearCajero extends JFrame{
    private JPanel panel_IngresoCajero;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton MENUButton;
    private JButton INGRESARButton;

    public CrearCajero(){
        super("INGRESO CAJEROS");
        setContentPane(panel_IngresoCajero);
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
