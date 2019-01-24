package service;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Histogram {
    public static int[] histogram(BufferedImage input) {
        return histogram(input, 'r');
    }

    public static int[] histogram(BufferedImage input, char key) {
        int[] result = new int[256];

        int width = input.getWidth();
        int height = input.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int index;
                switch (key) {
                    case 'r':
                        index = new Color(input.getRGB(i, j)).getRed();
                        break;
                    case 'g':
                        index = new Color(input.getRGB(i, j)).getGreen();
                        break;
                    case 'b':
                        index = new Color(input.getRGB(i, j)).getBlue();
                        break;
                    default:
                        throw new IllegalArgumentException("Please provide a valid key");
                }
                result[index]++;
            }
        }

        return result;
    }
}
