import com.kmschr.brs.*;
import com.kmschr.brs.enums.Rotation;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WedgeArtExample {

    @Test
    void testWedgeImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("test.PNG"));
        SaveData imageSave = new SaveData();
        imageSave.setDescription("");
        imageSave.setScreenshot(img);
        List<Brick> bricks = new ArrayList<>();

        for (int x=0; x < (img.getWidth()-3) / 4; x++) {
            for (int y=0; y < (img.getHeight()-3) / 4; y++) {
                int color = img.getRGB(x * 4, y * 4);
                int r = convert((color >> 16) & 0xFF);
                int g = convert((color >> 8) & 0xFF);
                int b = convert(color & 0xFF);

                Brick pixelTL = new Brick();
                pixelTL.setColor(new Color(r, g, b, 255));
                pixelTL.setSize(new Vec3(1, 1, 1));
                pixelTL.setPosition(new Vec3(x, y, 1));

                color = img.getRGB(x * 4 + 3, y * 4 + 3);
                r = convert((color >> 16) & 0xFF);
                g = convert((color >> 8) & 0xFF);
                b = convert(color & 0xFF);

                Brick pixelBR = new Brick();
                pixelBR.setColor(new Color(r, g, b, 255));
                pixelBR.setSize(new Vec3(1, 1, 1));
                pixelBR.setPosition(new Vec3(x, y, 1));
                pixelBR.setRotation(Rotation.Deg180);

                bricks.add(pixelTL);
                bricks.add(pixelBR);
            }
        }

        imageSave.setBrickAssets(List.of("PB_DefaultMicroWedge"));
        imageSave.setBricks(bricks);

        BRS.writeSave("peepo.brs", imageSave);
    }

    int convert(int n) {
        double u = ((double) n) / 255.0;
        if (u <= 0.04045)
            return (int) (((u * 25) / 323) * 255.0);
        double p = (u + 0.055) / 1.055;
        return (int) (Math.pow(p, 2.4) * 255.0);
    }

}
