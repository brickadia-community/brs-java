import com.kmschr.brs.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    Color testColor = new Color((byte) 243, (byte) 192, (byte) 200, (byte) 193);

    @Test
    void testR() {
        assertEquals((byte) 243, testColor.r());
    }

    @Test
    void testG() {
        assertEquals((byte) 192, testColor.g());
    }

    @Test
    void testB() {
        assertEquals((byte) 200, testColor.b());
    }

    @Test
    void testA() {
        assertEquals((byte) 193, testColor.a());
    }

    @Test
    void testToString() {
        assertEquals("#f3c0c8c1", testColor.toString());
    }

    @Test
    void testEquals() {
        assertEquals(new Color((byte) 243, (byte) 192, (byte) 200, (byte) 193), testColor);
        assertEquals(testColor, testColor);
        assertNotEquals(new Object(), testColor);
        assertNotEquals(new Color((byte) 7, (byte) 44, (byte) 200, (byte) 8), testColor);
    }

}
