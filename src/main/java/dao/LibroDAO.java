package dao;
import config.DatabaseConnection;
import interfaces.ICrud;
import models.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements ICrud<Libro> {
    private Connection con;

    public LibroDAO() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean crear(Libro libro) {
        String query = "INSERT INTO libros (titulo, autor, isbn, editorial, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getEditorial());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Libro> leerTodos() {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM libros";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                libros.add(new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
                        rs.getString("isbn"), rs.getString("editorial"), rs.getInt("stock"), rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {}
        return libros;
    }

    @Override
    public boolean actualizar(Libro libro) {
        String query = "UPDATE libros SET stock = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, libro.getStock());
            ps.setInt(2, libro.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    @Override
    public boolean eliminar(int id) {
        String query = "DELETE FROM libros WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}
