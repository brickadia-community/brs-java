import com.kmschr.brs.io.BitWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BitWriterTest {
    private BitWriter w;

    @BeforeEach
    void setup() {
        w = new BitWriter();
    }

    @Test
    void testWriteBit() {
        byte[] expected = new byte[]{(byte) 6};
        w.writeBit(false);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(false);
        w.writeBit(false);
        w.writeBit(false);
        w.writeBit(false);
        w.writeBit(false);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteBitNone() {
        byte[] expected = new byte[0];
        w.flushByte();
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteBitZero() {
        byte[] expected = new byte[]{(byte) 0};
        w.writeBit(false);
        w.flushByte();
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteBitMax() {
        byte[] expected = new byte[]{(byte) 0xFF};
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        w.writeBit(true);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    /*
    @Test
    void testWriteBits() {
        byte[] expected = new byte[]{(byte) 0xD3, (byte) 0x7F};
        byte[] input = new byte[]{(byte) 0xD3, (byte) 0xFF};
        w.writeBits(input, 15);
        w.flushByte();
        assertArrayEquals(expected, w.bufToByteArray());
    }*/

    @Test
    void testWriteInt() throws IOException {
        byte[] expected = new byte[]{(byte) 0x07, (byte) 0};
        w.writeInt(7, 512);
        w.flushByte();
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteIntPacked() {
        // 5402
        // 151A
        // 1010100011010
        // 101010  0011010
        // 1010100 00110101
        // 0x54    0x35
        byte[] expected = new byte[]{(byte) 0x35, (byte) 0x54};
        w.writeIntPacked(5402);
        w.flushByte();
        assertArrayEquals(expected, w.bufToByteArray());
    }

}
