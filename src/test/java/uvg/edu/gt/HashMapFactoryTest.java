package uvg.edu.gt;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.*;

public class HashMapFactoryTest {

    @Test
    public void testCreateMap() {
        HashMapFactory factory = new HashMapFactory();
        Map<String, String> map = factory.createMap();
        assertNotNull(map);
    }
}