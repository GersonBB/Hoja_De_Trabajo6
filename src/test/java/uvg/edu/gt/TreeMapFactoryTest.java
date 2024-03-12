package uvg.edu.gt;

import org.junit.Test;
import java.util.Map;

import static org.junit.Assert.*;

public class TreeMapFactoryTest {

    @Test
    public void testCreateMap() {
        TreeMapFactory factory = new TreeMapFactory();
        Map<String, String> map = factory.createMap();
        assertNotNull(map);
    }
}