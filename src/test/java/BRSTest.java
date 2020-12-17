import com.kmschr.brs.*;
import com.kmschr.brs.enums.Direction;
import com.kmschr.brs.enums.Rotation;
import com.kmschr.brs.io.BitReader;
import com.kmschr.brs.io.BitWriter;
import com.kmschr.brs.io.Reader;
import com.kmschr.brs.io.Writer;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BRSTest {

    long measureCpuTime(Runnable target) throws Exception {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (!threadMXBean.isThreadCpuTimeSupported()) {
            throw new UnsupportedOperationException("JVM does not support measuring thread CPU-time");
        }
        AtomicLong cpuTime = new AtomicLong(-1);
        Thread thread = new Thread(() -> {
            target.run();
            cpuTime.set(threadMXBean.getThreadCpuTime(Thread.currentThread().getId()));
        });
        thread.start();
        thread.join();
        return cpuTime.get();
    }

    @Test
    void testReadWriteRead() throws IOException {
        SaveData read1 = BRS.readSave("example_saves/BrickadiaCityQA.brs");
        for (Brick b : read1.getBricks())
            b.setColor(new Color(250, 157, 255, 255));
        BRS.writeSave("example_saves/write.brs", read1);
        SaveData read2 = BRS.readSave("example_saves/write.brs");
        assertEquals(read1.getBricks().size(), read2.getBricks().size());
    }

    @Test
    void testSpeeds() throws Exception {
        AtomicReference<SaveData> saveRef = new AtomicReference<>();
        long readDuration = measureCpuTime(() -> {
            try {
                saveRef.set(BRS.readSave("example_saves/BrickadiaCityQA.brs"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SaveData save = saveRef.get();
        System.out.println(save.getBricks().get(0));
        System.out.println(save.getBricks().get(1));
        System.out.println(save.getBrickAssets().size());
        long writeDuration = measureCpuTime(() -> {
            try {
                BRS.writeSave("example_saves/WriteTest.brs", save);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Speed test with " + String.format("%,d", save.getBricks().size()) + " bricks");
        System.out.println("Read: "+ ((double) readDuration / 1_000_000_000.0) + 's');
        System.out.println("Write: "+ ((double) writeDuration / 1_000_000_000.0) + 's');
    }

    @Test
    void testReadScreenshotAndComponents() throws IOException {
        SaveData save = BRS.readSave("example_saves/BrickadiaCity.brs");
        System.out.println(save.getComponents());
        for (Component c : save.getComponents())
            c.testRead();
    }

    @Test
    void testCompressAndUncompress() throws IOException {
        Writer w = new Writer();
        String testString = "Hello";
        w.writeString(testString);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        w.compressToStream(os);
        Reader r = Reader.fromCompressed(new ByteArrayInputStream(os.toByteArray()));
        assertEquals(testString, r.readString());
    }

    @Test
    void testSaveTimeCycle() throws IOException {
        ZonedDateTime saveTime = ZonedDateTime.now(ZoneId.of("Z"));
        Writer w = new Writer();
        w.writeDateTime(saveTime);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        w.writeToStream(os);
        Reader r = new Reader(new ByteArrayInputStream(os.toByteArray()));
        ZonedDateTime readTime = r.readDateTime();
        assertEquals(saveTime, readTime);
    }

    @Test
    void testOrientationCycle() {
        Rotation startRotation = Rotation.Deg90;
        Direction startDirection = Direction.YNegative;
        byte orientation = Utils.combineOrientation(startDirection, startRotation);
        Rotation endRotation = Rotation.getRotation(orientation);
        Direction endDirection = Direction.getDirection(orientation);
        assertEquals(startRotation, endRotation);
        assertEquals(startDirection, endDirection);
    }

    @Test
    void testFlipDirection() {
        assertEquals(Direction.ZNegative, Direction.ZPositive.flip());
    }

    @Test
    void testMakeOrange() throws IOException {
        SaveData brickadiaCity = BRS.readSave("example_saves/BrickadiaCityQA.brs");
        for (Brick brick : brickadiaCity.getBricks())
            brick.setColor(ColorMode.randomDefault());
        BRS.writeSave("example_saves/OrangeCity.brs", brickadiaCity);
    }

    @Test
    void testColorCycle() throws IOException {
        Color custom = new Color(123, 220, 125, 255);
        BitWriter bw = new BitWriter();
        bw.writeIntVectorPacked(new Vec3(23, 57, 12056));
        bw.writeBit(true);
        bw.writeInt(custom.getValue());
        bw.flushByte();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bw.compressToStream(os);
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        BitReader r = BitReader.fromCompressed(is);
        r.readIntVectorPacked();
        if (r.readBit()) {
            Color equal = new Color(r.readInt());
            assertEquals(custom.getValue(), equal.getValue());
        }
    }

    @Test
    void testReadWriteOffsetBytes() throws IOException {
        BitWriter w = new BitWriter();
        w.writeBit(true);
        w.writeBit(false);
        w.writeInt(0xE7F6C912);
        w.writeBit(true);
        w.writeInt(0x1302A601);
        w.flushByte();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        w.writeToStream(os);

        BitReader r = new BitReader(os.toByteArray());
        r.readBit();
        r.readBit();
        assertEquals(0xE7F6C912, r.readInt());
        r.readBit();
        assertEquals(0x1302A601, r.readInt());
    }

    @Test
    void testWriteBigScreenshot() throws IOException {
        SaveData save = new SaveData();
        save.setScreenshot(ImageIO.read(new File("rogue_peepo.PNG")));
        save.setBrickAssets(List.of("PB_DefaultBrick"));
        Brick b = new Brick();
        b.setSize(new Vec3(5, 5, 6));
        b.setPosition(new Vec3(0, 0, 6));
        save.getBricks().add(new Brick());
        BRS.writeSave("peepo_screenshot.brs", save);
    }

}
