import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Factory {
    
    public Map<String, Producto> crearMapa(int mapa) {
        switch (mapa) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new AssertionError("Implementacion de mapa no dispoible");
        }
    }

}
