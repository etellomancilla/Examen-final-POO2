package dao;
import config.DatabaseConnection;
import interfaces.ICrud;
import models.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements ICrud<Estudiante> {
    private Connection con;

    public EstudianteDAO() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean crear(Estudiante est) {
        String query = "INSERT INTO estudiantes (nombre, rut, curso, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getRut());
            ps.setString(3, est.getCurso());
            ps.setString(4, est.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Estudiante> leerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                lista.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("rut"),
                        rs.getString("correo"), rs.getString("curso")));
            }
        } catch (SQLException e) {}
        return lista;
    }

    @Override
    public boolean actualizar(Estudiante est) {
        String query = "UPDATE estudiantes SET nombre=?, curso=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getCurso());
            ps.setInt(3, est.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM estudiantes WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
