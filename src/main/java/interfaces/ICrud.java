package interfaces;
import java.util.List;
public interface ICrud<T> {
    boolean crear(T entidad);
    List<T> leerTodos();
    boolean actualizar(T entidad);
    boolean eliminar(int id);
}
