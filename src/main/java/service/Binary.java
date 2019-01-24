package service;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Binary {
    public static BufferedImage binarize(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage gray = Grayscale.grayscale(image);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


        int white = new Color(255, 255, 255).getRGB();
        int black = new Color(0, 0, 0).getRGB();

        int[] histogram = Histogram.histogram(gray);
        int allIntensitySum = 0;
        int allPixelCount = 0;

        for (int t = 0; t < 256; t++) {
            allIntensitySum += t * histogram[t];
            allPixelCount += histogram[t];
        }

        double maxSigma = -1;
        int threshold = 0;

        int firstClassIntensitySum = 0;
        int firstClassPixelCount = 0;

        for (int t = 0; t < 256; t++) {
            firstClassIntensitySum += t * histogram[t];
            firstClassPixelCount += histogram[t];

            double firstClassProb = (double) firstClassPixelCount / allPixelCount;
            double firstClassMean = (double) firstClassIntensitySum / firstClassPixelCount;
            double deltaMean = firstClassMean - (double) (allIntensitySum - firstClassIntensitySum) / (allPixelCount - firstClassPixelCount);

            double sigma = firstClassProb * (1 - firstClassProb) * deltaMean * deltaMean;

            if (sigma > maxSigma) {
                maxSigma = sigma;
                threshold = t;
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (new Color(gray.getRGB(i, j)).getRed() > threshold) {
                    result.setRGB(i, j, white);
                } else {
                    result.setRGB(i, j, black);
                }
            }
        }

        return result;
    }
}
