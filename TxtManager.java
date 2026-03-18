import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TxtManager {

    private Map<String, List<Producto>> porCategoria = new HashMap<>();
    private Map<String, Producto> porNombre = new HashMap<>();

    /**
     * Lee el archivo y carga los productos en las estructuras internas.
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

    public Map<String, List<Producto>> getPorCategoria() {
        return porCategoria;
    }

    public Map<String, Producto> getPorNombre() {
        return porNombre;
    }
}