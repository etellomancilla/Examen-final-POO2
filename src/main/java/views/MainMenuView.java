package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private JButton btnPrestamo, btnLibros, btnEstudiantes;

    public MainMenuView() {
        setTitle("Sistema de Biblioteca - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel p = new JPanel(new GridLayout(3,1,10,10));
        p.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        btnPrestamo = new JButton("Registro de Préstamos");
        btnLibros = new JButton("Gestión de Libros");
        btnEstudiantes = new JButton("Gestión de Estudiantes");
        p.add(btnPrestamo); p.add(btnLibros); p.add(btnEstudiantes);
        add(p);
    }

    public void setMenuListeners(ActionListener pListener, ActionListener lListener, ActionListener eListener) {
        btnPrestamo.addActionListener(pListener);
        btnLibros.addActionListener(lListener);
        btnEstudiantes.addActionListener(eListener);
    }
}
