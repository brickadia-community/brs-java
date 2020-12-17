
import com.kmschr.brs.io.Reader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    Reader r;

    @Test
    void testReadNBytes() throws IOException {
        byte[] input = new byte[]{(byte) 0x32, (byte) 0x1E, (byte) 0xF2};
        byte[] expected1 = new byte[]{(byte) 0x32, (byte) 0x1E};
        byte[] expected2 = new byte[]{(byte) 0xF2};
        r = new Reader(input);
        assertArrayEquals(expected1, r.readNBytes(2));
        assertArrayEquals(expected2, r.readNBytes(1));
    }

    @Test
    void testReadShort() throws IOException {
        // Decimal: -11798
        // Binary: 1101000111101010
        // Hex: D1EA
        // (Little Endian) => EAD1
        byte[] input = new byte[]{(byte) 0xEA, (byte) 0xD1};
        r = new Reader(input);
        assertEquals(-11798, r.readShort());
    }

    @Test
    void testReadInt() throws IOException {
        // Decimal: 1374304613
        // Binary: 01010001111010100011100101100101
        // Hex: 51EA3965
        // (Little Endian) => 6539EA51
        byte[] input = new byte[]{(byte) 0x65, (byte) 0x39, (byte) 0xEA, (byte) 0x51};
        r = new Reader(input);
        assertEquals(1374304613, r.readInt());
    }

    @Test
    void testReadAsciiString() throws IOException {
        // "Hello"
        // Length: 5 + 1 (for null)
        byte[] input = new byte[]
                {(byte) 6, (byte) 0, (byte) 0, (byte) 0, // Length 6
                 (byte) 'H', (byte) 'e', (byte) 'l', (byte) 'l', (byte) 'o', (byte) 0};
        r = new Reader(input);
        assertEquals("Hello", r.readString());
    }

    @Test
    void testReadUCS2String() throws IOException {
        // "звать"
        // Length: -((5 + 1) * 2) = -12
        byte[] input = new byte[]
                {(byte) 0xF4, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,  // Length -12
                 (byte) 0x37, (byte) 0x04, // 'з'
                 (byte) 0x32, (byte) 0x04, // 'в'
                 (byte) 0x30, (byte) 0x04, // 'а'
                 (byte) 0x42, (byte) 0x04, // 'т'
                 (byte) 0x4C, (byte) 0x04, // 'ь'
                 (byte) 0, (byte) 0}; // '\0'
        r = new Reader(input);
        assertEquals("звать", r.readString());
    }

    @Test
    void testReadStrings() throws IOException {
        // "He", "llo"
        // 2 items
        // lengths 3, 4
        byte[] input = new byte[] {
                (byte) 2, (byte) 0, (byte) 0, (byte) 0,
                (byte) 3, (byte) 0, (byte) 0, (byte) 0,
                (byte) 'H', (byte) 'e', (byte) 0,
                (byte) 4, (byte) 0, (byte) 0, (byte) 0,
                (byte) 'l', (byte) 'l', (byte) 'o', (byte) 0
        };
        List<String> expected = new ArrayList<>();
        expected.add("He");
        expected.add("llo");
        r = new Reader(input);
        assertArrayEquals(expected.toArray(), r.readStrings().toArray());
    }

    @Test
    void testReadUUID() throws IOException {
        // 8efaeb23-5e82-428e-b575-0dd30270146e
        // 8efaeb23 5e82428e b5750dd3 0270146e
        // 23ebfa8e 8e42825e d30d75b5 6e147002
        byte[] input = new byte[]{
                (byte) 0x23, (byte) 0xeb, (byte) 0xfa, (byte) 0x8e,
                (byte) 0x8e, (byte) 0x42, (byte) 0x82, (byte) 0x5e,
                (byte) 0xd3, (byte) 0x0d, (byte) 0x75, (byte) 0xb5,
                (byte) 0x6e, (byte) 0x14, (byte) 0x70, (byte) 0x02,
        };
        UUID smallguy = UUID.fromString("8efaeb23-5e82-428e-b575-0dd30270146e");
        r = new Reader(input);
        assertEquals(smallguy, r.readUUID());
    }

}
