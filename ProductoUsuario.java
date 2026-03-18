/**
 * Representa un producto asociado a una cantidad dentro de la colección del usuario.
 * <p>
 * Esta clase sirve como envoltorio para un {@link Producto}, permitiendo llevar
 * un registro de cuántas unidades del mismo han sido agregadas por el usuario.
 * </p>
 */
public class ProductoUsuario {

    private Producto producto;
    private int cantidad;

    /**
     * Construye un objeto que asocia un producto con una cantidad inicial.
     *
     * @param producto producto al que se le llevará el conteo
     * @param cantidad cantidad inicial del producto
     */
    public ProductoUsuario(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado.
     *
     * @return el producto almacenado
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad actual del producto.
     *
     * @return cantidad del producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Aumenta la cantidad del producto almacenado.
     *
     * @param cantidad cantidad a sumar a la actual
     */
    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    /**
     * Devuelve una representación textual del producto y su cantidad.
     *
     * @return cadena con el formato: {@code nombre (categoria) - Cantidad: X}
     */
    @Override
    public String toString() {
        return producto.getNombre() + " (" + producto.getCategoria() + ") - Cantidad: " + cantidad;
    }
}