package ch.bbw.gameboy;

import ch.bbw.gameboy.api.PixelGraphic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    private final int[] data;

    private final PixelGraphic pixelGraphic;
    private final int width;
    private final int height;

    public Sprite(String path, PixelGraphic pixelGraphic) throws IOException {
        this.pixelGraphic = pixelGraphic;

        BufferedImage image = ImageIO.read(new File(path));
        data = new int[image.getWidth() * image.getHeight()];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int color = image.getRGB(j, i);
                data[i * image.getWidth() + j] = mapColor(Color.decode(String.valueOf(color)));
            }
        }

        width = image.getWidth();
        height = image.getHeight();
    }

    private int mapColor(Color c) {
        int brightness = c.getBlue() + c.getGreen() + c.getRed();
        if (brightness < 50) {
            return 3;
        }
        if (brightness < 300) {
            return 2;
        }
        if (brightness < 400) {
            return 1;
        }
        return 0;
    }

    public void draw(int x, int y) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelGraphic.setPixel(x + i, y + j, data[j * width + i]);
            }
        }
    }
}
