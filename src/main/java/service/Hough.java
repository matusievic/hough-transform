package service;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


public class Hough {
    public static BufferedImage transform(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        int maxR = (int) sqrt(width * width + height * height);
        int[][] mt = new int[maxR][90];
        double accurancy = 0.1;

        int white = Color.WHITE.getRGB();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (input.getRGB(j, i) == white) {
                    for (int f = 0; f < 180; f += 2) {
                        for (int r = 0; r < maxR; r++) {
                            double theta = f * Math.PI / 180.0;
                            if (abs((i * sin(theta) + j * cos(theta)) - r) < accurancy) {
                                mt[r][f / 2]++;
                            }
                        }
                    }
                }
                System.out.println('[' + j + ", " + i + ']');
            }
        }


        BufferedImage output = new BufferedImage(maxR, 90, BufferedImage.TYPE_INT_ARGB);
        for (int r = 0; r < maxR; r++) {
            for (int f = 0; f < 90; f++) {
                if (mt[r][f] > 255) {
                    mt[r][f] = 255;
                    int rgb = mt[r][f];
                    output.setRGB(r, f, new Color(rgb, rgb, rgb).getRGB());
                }
            }
        }

        return output;
    }
}
