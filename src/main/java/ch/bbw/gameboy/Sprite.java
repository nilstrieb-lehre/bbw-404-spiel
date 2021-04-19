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

    public Sprite(String path, PixelGraphic pixelGraphic) {
        this.pixelGraphic = pixelGraphic;

        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println(c.getAlpha());
        if (c.getAlpha() < 100) {
            System.out.println("transparent");
            return 5;
        }
        if (brightness < 50) {
            return 3;
        }
        if (brightness < 400) {
            return 2;
        }
        if (brightness < 600) {
            return 1;
        }
        return 0;
    }

    public void draw(int x, int y) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (x + i < pixelGraphic.getPixelWidth() && y + j< pixelGraphic.getPixelHeight()) {
                    int color = data[j * width + i];
                    if (color == 5) {
                        continue;
                    }
                    pixelGraphic.setPixel(x + i, y + j, color);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
