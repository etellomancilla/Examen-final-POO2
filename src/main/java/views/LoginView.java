package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Biblioteca - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel p = new JPanel(new GridLayout(3,2,10,10));
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        p.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        p.add(txtCorreo);
        p.add(new JLabel("Contraseña:"));
        txtPass = new JPasswordField();
        p.add(txtPass);
        btnLogin = new JButton("Ingresar");
        p.add(new JLabel("")); p.add(btnLogin);
        add(p);
    }
    public void setLoginListener(ActionListener listener) { btnLogin.addActionListener(listener); }
    public String getCorreo() { return txtCorreo.getText(); }
    public String getPassword() { return new String(txtPass.getPassword()); }
}
