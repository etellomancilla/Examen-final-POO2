package dao;
import config.DatabaseConnection;
import interfaces.ICrud;
import interfaces.IPrestamo;
import models.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// IL3. Implementa múltiples interfaces en una misma clase
public class PrestamoDAO implements ICrud<Prestamo>, IPrestamo {
    private Connection con;

    public PrestamoDAO() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    // IL5. Sincronización
    @Override
    public synchronized boolean registrarPrestamo(int idEstudiante, int idLibro) {
        try {
            con.setAutoCommit(false);
            String checkStock = "SELECT stock FROM libros WHERE id = ? AND stock > 0 FOR UPDATE";
            PreparedStatement psCheck = con.prepareStatement(checkStock);
            psCheck.setInt(1, idLibro);
            ResultSet rs = psCheck.executeQuery();
            
            if (rs.next()) {
                String insertPrestamo = "INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 0)";
                PreparedStatement psInsert = con.prepareStatement(insertPrestamo);
                psInsert.setInt(1, idEstudiante);
                psInsert.setInt(2, idLibro);
                psInsert.executeUpdate();
                
                String updateStock = "UPDATE libros SET stock = stock - 1 WHERE id = ?";
                PreparedStatement psUpdate = con.prepareStatement(updateStock);
                psUpdate.setInt(1, idLibro);
                psUpdate.executeUpdate();
                
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException e) {}
        }
    }

    @Override
    public boolean crear(Prestamo entidad) { return false; } // Handled by registrarPrestamo

    @Override
    public List<Prestamo> leerTodos() {
        List<Prestamo> lista = new ArrayList<>();
        String query = "SELECT * FROM prestamos";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Prestamo(rs.getInt("id"), rs.getInt("id_estudiante"), rs.getInt("id_libro"), rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"), rs.getBoolean("devuelto")));
            }
        } catch (SQLException e) {}
        return lista;
    }

    @Override
    public boolean actualizar(Prestamo entidad) { return false; }

    @Override
    public boolean eliminar(int id) { return false; }
}
