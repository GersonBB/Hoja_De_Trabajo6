package uvg.edu.gt;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que implementa la interfaz MapFactory y crea un TreeMap
 */

public class TreeMapFactory implements MapFactory {
    @Override
    public Map<String, String> createMap() {
        return new TreeMap<>();
    }
}
