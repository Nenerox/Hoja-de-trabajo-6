import java.util.*;

public class Inventario {

    private Map<String, Producto> mapaPorNombre;
    private Map<String, List<Producto>> mapaPorCategoria;
    private Map<Producto, ProductoUsuario> coleccionUsuario;

    public Inventario(Map<String, Producto> porNombre, Map<String, List<Producto>> porCategoria, int tipoMapa) {

        Factory factory = new Factory();

        this.mapaPorNombre = porNombre;
        this.mapaPorCategoria = porCategoria;
        this.coleccionUsuario = factory.crearMapa(tipoMapa);
    }

    public void agregarProducto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Nombre inválido");
            return;
        }

        Producto producto = mapaPorNombre.get(nombre);
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        ProductoUsuario item = coleccionUsuario.get(producto);

        if (item != null) {
            item.aumentarCantidad(1);
        } else {
            coleccionUsuario.put(producto, new ProductoUsuario(producto, 1));
        }
    }

    public void mostrarColeccion() {

        if (coleccionUsuario.isEmpty()) {
            System.out.println("La colección está vacía.");
            return;
        }
        for (ProductoUsuario item : coleccionUsuario.values()) {
            System.out.println(item);
        }
    }

    public void mostrarPorCategoria(String categoria) {

        List<Producto> lista = mapaPorCategoria.get(categoria);

        if (lista == null) {
            System.out.println("Categoría no encontrada");
            return;
        }
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    public void mostrarCategorias() {

        if (mapaPorCategoria.isEmpty()) {
            System.out.println("No hay categorías disponibles.");
            return;
        }
        for (String categoria : mapaPorCategoria.keySet()) {
            System.out.println(categoria);
        }
    }
}