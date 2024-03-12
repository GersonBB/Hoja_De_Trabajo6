package uvg.edu.gt;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.*;

public class LinkedHashMapFactoryTest {

    @Test
    public void testCreateMap() {
        LinkedHashMapFactory factory = new LinkedHashMapFactory();
        Map<String, String> map = factory.createMap();
        assertNotNull(map);
    }
}