package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import dao.PrestamoDAO;

public class MainView extends JFrame {
    private JButton btnPrestarLibro;
    private JLabel lblStatus;
    private JTextField txtIdEstudiante;
    private JTextField txtIdLibro;

    public MainView() {
        setTitle("Sistema de Biblioteca Escolar - Main");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("ID Estudiante:"));
        txtIdEstudiante = new JTextField();
        panel.add(txtIdEstudiante);
        
        panel.add(new JLabel("ID Libro:"));
        txtIdLibro = new JTextField();
        panel.add(txtIdLibro);
        
        btnPrestarLibro = new JButton("Registrar Préstamo");
        panel.add(btnPrestarLibro);
        
        lblStatus = new JLabel("");
        panel.add(lblStatus);
        
        add(panel);
    }

    public void setPrestarLibroListener(ActionListener listener) {
        btnPrestarLibro.addActionListener(listener);
    }

    public int getIdEstudiante() {
        try { return Integer.parseInt(txtIdEstudiante.getText()); } catch(Exception e) { return -1; }
    }

    public int getIdLibro() {
        try { return Integer.parseInt(txtIdLibro.getText()); } catch(Exception e) { return -1; }
    }

    public void setStatus(String status) {
        lblStatus.setText(status);
    }
}
