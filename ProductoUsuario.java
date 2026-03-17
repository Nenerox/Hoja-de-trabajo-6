public class ProductoUsuario {
    private Producto producto;
    private int cantidad;

    public ProductoUsuario(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void aumentarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    @Override
    public String toString() {
        return producto.getNombre() + " (" + producto.getCategoria() + ") - Cantidad: " + cantidad;
    }
}