package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que implementa la interfaz MapFactory y crea un HashMap
 */

public class HashMapFactory implements MapFactory {
    @Override
    public Map<String, String> createMap() {
        return new HashMap<>();
    }
}
