package dao;
import config.DatabaseConnection;
import interfaces.ICrud;
import models.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements ICrud<Usuario> {
    private Connection con;

    public UsuarioDAO() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public Usuario autenticar(String correo, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("rut"),
                        rs.getString("correo"), rs.getString("contraseña"), rs.getString("rol"));
            }
        } catch (SQLException e) {}
        return null;
    }
    
    @Override
    public boolean crear(Usuario u) { return false; }
    
    @Override
    public List<Usuario> leerTodos() { return new ArrayList<>(); }
    
    @Override
    public boolean actualizar(Usuario u) { return false; }
    
    @Override
    public boolean eliminar(int id) { return false; }
}
