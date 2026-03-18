import java.util.*;

/**
 * Clase que gestiona un inventario de productos, permitiendo organizarlos por nombre,
 * categoría y también registrar una colección personalizada por parte del usuario.
 *
 * <p>
 * Esta clase utiliza tres estructuras principales:
 * </p>
 * <ul>
 *     <li><b>mapaPorNombre:</b> relaciona el nombre del producto con su instancia.</li>
 *     <li><b>mapaPorCategoria:</b> agrupa listas de productos por categoría.</li>
 *     <li><b>coleccionUsuario:</b> almacena productos adquiridos o seleccionados por el usuario,
 *         usando la implementación de {@link Map} definida mediante {@code tipoMapa}.</li>
 * </ul>
 */
public class Inventario {

    private Map<String, Producto> mapaPorNombre;
    private Map<String, List<Producto>> mapaPorCategoria;
    private Map<Producto, ProductoUsuario> coleccionUsuario;

    /**
     * Construye un inventario utilizando mapas para organizar productos por nombre
     * y por categoría. Además, emplea un {@link Factory} para crear la estructura utilizada en
     * la colección personalizada del usuario.
     *
     * @param porNombre     mapa que relaciona nombres de productos con las instancias correspondientes
     * @param porCategoria  mapa que agrupa listas de productos por categoría
     * @param tipoMapa      entero que define qué implementación de {@link Map} se usará
     *                      para la colección del usuario (delegado a {@link Factory#crearMapa(int)})
     */
    public Inventario(Map<String, Producto> porNombre, Map<String, List<Producto>> porCategoria, int tipoMapa) {

        Factory factory = new Factory();

        this.mapaPorNombre = porNombre;
        this.mapaPorCategoria = porCategoria;
        this.coleccionUsuario = factory.crearMapa(tipoMapa);
    }

    /**
     * Agrega un producto a la colección del usuario según su nombre.
     * <p>
     * Si el producto ya existe en la colección, su cantidad aumenta en uno.
     * Si aún no existe, se incorpora con cantidad inicial igual a 1.
     * </p>
     *
     * @param nombre nombre del producto que se desea agregar
     */
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

    /**
     * Muestra por consola la colección de productos del usuario.
     * <p>
     * Si la colección está vacía, se informa al usuario.
     * </p>
     */
    public void mostrarColeccion() {

        if (coleccionUsuario.isEmpty()) {
            System.out.println("La colección está vacía.");
            return;
        }
        for (ProductoUsuario item : coleccionUsuario.values()) {
            System.out.println(item);
        }
    }

    /**
     * Muestra todos los productos asociados a una categoría específica.
     *
     * @param categoria cadena que identifica la categoría buscada
     */
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

    /**
     * Muestra por consola los nombres de todas las categorías disponibles
     * dentro del inventario.
     */
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