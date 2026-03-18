import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Gestiona la carga de productos desde un .txt
 * <p>
 * Esta clase organiza los productos en dos estructuras:
 * </p>
 * <ul>
 *     <li><b>porCategoria:</b> un mapa que agrupa listas de productos según su categoría.</li>
 *     <li><b>porNombre:</b> un mapa que permite acceder rápidamente a un producto por su nombre.</li>
 * </ul>
 *
 * <p>
 * El formato esperado del archivo es una línea por producto así:
 * <br>{@code categoria | nombre}
 * </p>
 */
public class TxtManager {

    private Map<String, List<Producto>> porCategoria = new HashMap<>();
    private Map<String, Producto> porNombre = new HashMap<>();

    /**
     * Carga productos desde un archivo de texto.
     * <p>
     * Cada línea del archivo debe contener una categoría y un nombre separados por el símbolo "|".
     * Los productos se almacenan tanto por categoría como por nombre.
     * </p>
     *
     *
     * @param filePath ruta del archivo que contiene los productos
     */
    public void cargarProductos(String filePath) {
        try {
            List<String> lineas = Files.readAllLines(Path.of(filePath));

            for (String linea : lineas) {

                String[] partes = linea.split("\\|");

                if (partes.length != 2) continue;

                String categoria = partes[0].trim();
                String nombre = partes[1].trim();

                Producto producto = new Producto(nombre, categoria);

                porCategoria.putIfAbsent(categoria, new ArrayList<>());
                porCategoria.get(categoria).add(producto);

                porNombre.put(nombre, producto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el mapa que agrupa productos por categoría.
     *
     * @return un mapa donde la llave es la categoría y el valor es una lista de productos
     */
    public Map<String, List<Producto>> getPorCategoria() {
        return porCategoria;
    }

    /**
     * Obtiene el mapa que permite acceder a los productos por su nombre.
     *
     * @return un mapa donde la llave es el nombre del producto y el valor es su instancia
     */
    public Map<String, Producto> getPorNombre() {
        return porNombre;
    }
}