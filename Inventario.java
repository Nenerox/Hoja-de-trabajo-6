import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.*;

/**
 * Inventario de productos cargados por nombre y por categoría.
 * <p>
 */
public class Inventario {

    Factory factory = new Factory();
    private Map<String, Producto> mapaPorNombre;
    private Map<String, List<Producto>> mapaPorCategoria;
    private Map<Producto, ProductoUsuario> coleccionUsuario;
    private int tipoMapa;

    /**
     * Construye un inventario utilizando mapas previamente cargados.
     *
     * @param porNombre     mapa que relaciona un nombre con su producto
     * @param porCategoria  mapa que agrupa productos por categoría
     * @param tipoMapa      tipo de mapa a utilizar para la colección del usuario
     */
    public Inventario(Map<String, Producto> porNombre,
                      Map<String, List<Producto>> porCategoria,
                      int tipoMapa) {
        this.tipoMapa = tipoMapa;
        this.mapaPorNombre = porNombre;
        this.mapaPorCategoria = factory.crearMapa(tipoMapa);
        this.mapaPorCategoria.putAll(porCategoria);
        this.coleccionUsuario = factory.crearMapa(tipoMapa);
    }

    /**
     * Agrega un producto a la colección del usuario a partir de su nombre.
     * <p>
     * Si el producto ya existe, aumenta su cantidad.  
     * Si no existe, lo agrega con cantidad inicial de 1.
     * </p>
     *
     * @param nombre nombre del producto a agregar
     */
    public void agregarProducto(String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Nombre inválido");
            return;
        }

        Producto producto = mapaPorNombre.get(stripDiacritics(nombre));

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
     * Muestra la categoría del producto cuyo nombre se proporciona.
     *
     * @param nombre nombre del producto buscado
     */
    public void mostrarCategoriaDeProducto(String nombre) {

        Producto producto = mapaPorNombre.get(nombre);

        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Categoría: " + producto.getCategoria());
    }

    /**
     * Muestra la colección del usuario.
     * <p>
     * Si no tiene productos agregados, informa que la colección está vacía.
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
     * Muestra la colección del usuario agrupada y ordenada por categoría.
     */
    public void mostrarColeccionOrdenadaPorCategoria() {

        if (coleccionUsuario.isEmpty()) {
            System.out.println("La colección está vacía.");
            return;
        }

        Map<String, List<ProductoUsuario>> agrupado = factory.crearMapa(tipoMapa);

        for (ProductoUsuario item : coleccionUsuario.values()) {
            String categoria = item.getProducto().getCategoria();

            agrupado.putIfAbsent(categoria, new ArrayList<>());
            agrupado.get(categoria).add(item);
        }

        for (String categoria : agrupado.keySet()) {
            System.out.println("Categoría: " + categoria);
            for (ProductoUsuario item : agrupado.get(categoria)) {
                System.out.println("  " + item);
            }
        }
    }

    /**
     * Muestra todos los productos registrados
     */
    public void mostrarTodo() {

        if (mapaPorNombre.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }

        for (Producto p : mapaPorNombre.values()) {
            System.out.println(p.getNombre() + " - " + p.getCategoria());
        }
    }

    /**
     * Muestra todos los productos ordenados por categoría.
     */
    public void mostrarTodoOrdenadoPorCategoria() {

        if (mapaPorCategoria.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }

        Map<String, List<Producto>> ordenado = mapaPorCategoria;

        for (String categoria : ordenado.keySet()) {
            System.out.println("Categoría: " + categoria);
            for (Producto p : ordenado.get(categoria)) {
                System.out.println("  " + p.getNombre());
            }
        }
    }

    /**
     * Muestra los productos pertenecientes a una categoría específica.
     *
     * @param categoria categoría a consultar
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
     * Muestra todas las categorías registradas.
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

     /**
     * Transofrma el string ingresado a una version NFD donde se eliminan los acentos para una mejor comparacion
     * 
     * @param s String orignial
     * @return Version del string normalizada sin acentos
     */
    public String stripDiacritics(String s) {
        String normalized = Normalizer.normalize(s, Form.NFD);
        String normal = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normal.toLowerCase().trim();
    }
}