import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Factory {

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