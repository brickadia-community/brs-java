import com.kmschr.brs.*;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DitheringExample {

    @Test
    void testDithering() throws IOException {
        BufferedImage img = ImageIO.read(new File("test.png"));
        SaveData imageSave = new SaveData();
        imageSave.setDescription("");
        imageSave.setScreenshot(img);
        List<Brick> bricks = new ArrayList<>();

        int matrix[][] = {
                { 1 ,49 ,13 ,61 ,4 ,52 ,16 ,64 },
                { 33 ,17 ,45 ,29 ,36 ,20 ,48 ,32 },
                { 9 ,57 ,5 ,53 ,12 ,60 ,8 ,56 },
                { 41 ,25 ,37 ,21 ,44 ,28 ,40 ,24},
                {3 ,51 ,15 ,63 ,2 ,50 ,14 ,62},
                {35 ,19 ,47 ,31 ,34 ,18 ,46 ,30},
                {11 ,59 ,7 ,55 ,10 ,58 ,6 ,54},
                {43 ,27 ,39 ,23 ,42 ,26 ,38 ,22}
        };

        for (int x=0; x < img.getWidth(); x++) {
            for (int y=0; y < img.getHeight(); y++) {
                int color = img.getRGB(x, y);
                int r = (color >> 16) & 0xFF;

                int gray = r + (r * matrix[x % 8][y % 8]) / 65;

                if (gray < 128) {
                    gray = 0;
                } else {
                    gray = 255;
                }

                Brick pixel = new Brick();
                pixel.setColor(new Color(gray, gray, gray, 255));
                pixel.setSize(new Vec3(1, 1, 1));
                pixel.setPosition(new Vec3(x, y, 1));

                bricks.add(pixel);
            }
        }

        imageSave.setBrickAssets(List.of("PB_DefaultMicroBrick"));
        imageSave.setBricks(bricks);

        BRS.writeSave("dithered.brs", imageSave);
    }

}
