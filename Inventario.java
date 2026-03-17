import java.util.*;

public class Inventario {

    private Map<String, Producto> mapaPorNombre;
    private Map<Producto, ProductoUsuario> coleccionUsuario;

    public Inventario(Map<String, Producto> mapaPorNombre) {
        this.mapaPorNombre = mapaPorNombre;
        this.coleccionUsuario = new HashMap<>();
    }

    public void agregarProducto(String nombre) {
        Producto producto = mapaPorNombre.get(nombre);

        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        if (coleccionUsuario.containsKey(producto)) {
            coleccionUsuario.get(producto).aumentarCantidad(1);
        } else {
            coleccionUsuario.put(producto, new ProductoUsuario(producto, 1));
        }
    }
    public void mostrarColeccion() {
        if (coleccionUsuario.isEmpty()) {
            System.out.println("La colección está vacía.");
            return;
        }
        for (Map.Entry<Producto, ProductoUsuario> entry : coleccionUsuario.entrySet()) {
            ProductoUsuario item = entry.getValue();
            System.out.println(item);
        }
    }
}