import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Clase utilitaria para la lectura y escritura de archivos de texto.
 *
 * Permite leer expresiones desde un archivo y escribir
 * el resultado final de la calculadora.
 */
public class TxtManager {

    /**
     * Lee el contenido de un archivo y elimina todos los espacios.
     *
     * @param filePath ruta del archivo a leer
     * @return un mapa con los productos y categorias.
     *         cadena vacía si ocurre un error
     */
    public Map<String, List<Producto>> cargarProductos(String filePath){
        Map<String, List<Producto>> mapa = new HashMap<>();
        try {
            List<String> lineas = Files.readAllLines(Path.of(filePath));
            for(String linea : lineas)
            {
                String[] partes = linea.split("\\|");
                if(partes.length != 2)
                {
                    continue;
                }
                String categoria = partes[0].trim();
                String nombre = partes[1].trim();
                Producto producto = new Producto(nombre, categoria);
                mapa.putIfAbsent(categoria, new ArrayList<>());
                mapa.get(categoria).add(producto);
            }
        } catch (IOException e){
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return mapa;
    }
}