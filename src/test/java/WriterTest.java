import com.kmschr.brs.io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {

    private Writer w;

    @BeforeEach()
    void setup() {
        w = new Writer();
    }

    @Test
    void testWriteShort() {
        // Decimal: 53738
        // Binary: 1101000111101010
        // Hex: D1EA
        // (Little Endian) => EAD1
        byte[] expected = new byte[]{(byte) 0xEA, (byte) 0xD1};
        w.writeShort((short) 53738);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteInt() throws IOException {
        // Decimal: 1374304613
        // Binary: 01010001111010100011100101100101
        // Hex: 51EA3965
        // (Little Endian) => 6539EA51
        byte[] expected = new byte[]{(byte) 0x65, (byte) 0x39, (byte) 0xEA, (byte) 0x51};
        w.writeInt(1374304613);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteAsciiString() throws IOException {
        // "Hello"
        // Length: 5 + 1 (for null)
        byte[] expected = new byte[]
                {(byte) 6, (byte) 0, (byte) 0, (byte) 0, // Length 6
                 (byte) 'H', (byte) 'e', (byte) 'l', (byte) 'l', (byte) 'o', (byte) 0};
        w.writeString("Hello");
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteUCS2String() throws IOException {
        // "звать"
        // Length: -((5 + 1) * 2) = -12
        byte[] expected = new byte[]
                {(byte) 0xF4, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,  // Length -12
                 (byte) 0x37, (byte) 0x04, // 'з'
                 (byte) 0x32, (byte) 0x04, // 'в'
                 (byte) 0x30, (byte) 0x04, // 'а'
                 (byte) 0x42, (byte) 0x04, // 'т'
                 (byte) 0x4C, (byte) 0x04, // 'ь'
                 (byte) 0, (byte) 0}; // '\0'
        w.writeString("звать");
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteInvalidString() {
        assertThrows(IOException.class, () -> w.writeString("\uD9A5\uDDDD bad string bad"));
    }

    @Test
    void testWriteUUID() throws IOException {
        // 8efaeb23-5e82-428e-b575-0dd30270146e
        // 8efaeb23 5e82428e b5750dd3 0270146e
        // 23ebfa8e 8e42825e d30d75b5 6e147002
        byte[] expected = new byte[]{
                (byte) 0x23, (byte) 0xeb, (byte) 0xfa, (byte) 0x8e,
                (byte) 0x8e, (byte) 0x42, (byte) 0x82, (byte) 0x5e,
                (byte) 0xd3, (byte) 0x0d, (byte) 0x75, (byte) 0xb5,
                (byte) 0x6e, (byte) 0x14, (byte) 0x70, (byte) 0x02,
        };
        UUID smallguy = UUID.fromString("8efaeb23-5e82-428e-b575-0dd30270146e");
        w.writeUUID(smallguy);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteDateTime() throws IOException {
        w.writeDateTime(ZonedDateTime.now());
    }

    @Test
    void testWriteStrings() throws IOException {
        // "He", "llo"
        // 2 items
        // lengths 3, 4
        byte[] expected = new byte[] {
                (byte) 2, (byte) 0, (byte) 0, (byte) 0,
                (byte) 3, (byte) 0, (byte) 0, (byte) 0,
                (byte) 'H', (byte) 'e', (byte) 0,
                (byte) 4, (byte) 0, (byte) 0, (byte) 0,
                (byte) 'l', (byte) 'l', (byte) 'o', (byte) 0
        };
        List<String> stringList = new ArrayList<>();
        stringList.add("He");
        stringList.add("llo");
        w.writeStrings(stringList);
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testWriteAll() {
        byte[] expected = new byte[]{(byte) 6, (byte) 9, (byte) 4, (byte) 2, (byte) 0};
        w.writeAll(new byte[]{(byte) 6, (byte) 9});
        w.writeAll(new byte[]{(byte) 4, (byte) 2, (byte) 0});
        assertArrayEquals(expected, w.bufToByteArray());
    }

    @Test
    void testCompressToStream() throws IOException {
        String uncompressed = "Helllllllllllllllllllllllllllllllllllllllo";
        w.writeString(uncompressed);
        System.out.println("Uncompressed:");
        w.writeToStream(System.out);
        System.out.println("\nCompressed");
        boolean benefits = w.compressToStream(System.out);
        System.out.println("\nBenefits from compression: " + benefits);
        assertTrue(benefits);
    }

    @Test
    void testCompressWithoutBenefit() throws IOException {
        String uncompressed = "This string varies a lot and will not benefit from compression!";
        w.writeString(uncompressed);
        boolean benefits = w.compressToStream(System.out);
        assertFalse(benefits);
    }

}
