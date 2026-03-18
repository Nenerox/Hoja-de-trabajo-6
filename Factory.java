import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Factory para la creación de diferentes implementaciones de {@link Map}.
 * <p>
 * Esta clase permite instanciar varios tipos de mapas según un parámetro entero.
 * </p>
 *
 * <p>Implementaciones disponibles:</p>
 * <ul>
 *   <li><b>1:</b> {@link HashMap}</li>
 *   <li><b>2:</b> {@link TreeMap}</li>
 *   <li><b>3:</b> {@link LinkedHashMap}</li>
 * </ul>
 *
 */
public class Factory {

    /**
     * Crea una implementación específica de {@link Map} según el tipo solicitado.
     *
     * @param <K> tipo de las llaves del mapa
     * @param <V> tipo de los valores del mapa
     * @param tipo un entero que define la implementación a crear:
     *             <ul>
     *               <li>1 → {@link HashMap}</li>
     *               <li>2 → {@link TreeMap}</li>
     *               <li>3 → {@link LinkedHashMap}</li>
     *             </ul>
     * @return una instancia de {@link Map} correspondiente al tipo solicitado
     *
     * @throws IllegalArgumentException si el tipo no corresponde a ninguna implementación válida
     */
    public <K, V> Map<K, V> crearMapa(int tipo) {
        switch (tipo) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Implementación no disponible");
        }
    }
}