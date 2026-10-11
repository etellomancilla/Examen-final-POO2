package controllers;
import dao.PrestamoDAO;
import views.MainMenuView;
import views.PrestamoView;

public class MainController {
    private MainMenuView menuView;
    private PrestamoView prestamoView;
    private PrestamoDAO prestamoDAO;

    public MainController() {
        menuView = new MainMenuView();
        prestamoView = new PrestamoView();
        prestamoDAO = new PrestamoDAO();
        
        menuView.setMenuListeners(
            e -> { prestamoView.setVisible(true); },
            e -> { javax.swing.JOptionPane.showMessageDialog(menuView, "Módulo de Libros"); },
            e -> { javax.swing.JOptionPane.showMessageDialog(menuView, "Módulo de Estudiantes"); }
        );

        prestamoView.setPrestarLibroListener(e -> registrarPrestamo());
    }

    public void iniciar() {
        menuView.setVisible(true);
    }

    private void registrarPrestamo() {
        int idEstudiante = prestamoView.getIdEstudiante();
        int idLibro = prestamoView.getIdLibro();

        if (idEstudiante > 0 && idLibro > 0) {
            prestamoView.setStatus("Procesando préstamo...");
            
            // IL4. Hilos en procesos críticos
            new Thread(() -> {
                boolean exito = prestamoDAO.registrarPrestamo(idEstudiante, idLibro);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    if (exito) prestamoView.setStatus("Préstamo exitoso.");
                    else prestamoView.setStatus("Error en préstamo.");
                });
            }).start();
        } else {
            prestamoView.setStatus("Ingrese IDs válidos.");
        }
    }
}
