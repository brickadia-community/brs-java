import com.kmschr.brs.io.BitReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BitReaderTest {
    private BitReader r;

    @Test
    void testReadBit() throws IOException {
        r = new BitReader(new byte[]{(byte) 6});
        assertFalse(r.readBit());
        assertTrue(r.readBit());
        assertTrue(r.readBit());
        assertFalse(r.readBit());
        assertFalse(r.readBit());
        assertFalse(r.readBit());
        assertFalse(r.readBit());
        assertFalse(r.readBit());
    }

    @Test
    void testReadInt() throws IOException {
        byte[] input = new byte[]{(byte) 0x07, (byte) 0};
        r = new BitReader(input);
        assertEquals(7, r.readInt(512));
    }

    @Test
    void testReadIntPacked() throws IOException {
        // 5402
        // 151A
        // 1010100011010
        // 101010  0011010
        // 1010100 00110101
        // 0x54    0x35
        byte[] input = new byte[]{(byte) 0x35, (byte) 0x54};
        r = new BitReader(input);
        assertEquals(5402, r.readIntPacked());
    }

}
