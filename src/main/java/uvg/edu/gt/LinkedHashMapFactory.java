package uvg.edu.gt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que implementa la interfaz MapFactory y crea un LinkedHashMap
 */
public class LinkedHashMapFactory implements MapFactory {
    @Override
    public Map<String, String> createMap() {
        return new LinkedHashMap<>();
    }
}
