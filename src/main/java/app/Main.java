package app;

import service.Binary;
import service.Grayscale;
import service.Hough;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage input = ImageIO.read(new File("in.jpeg"));

        BufferedImage output = Grayscale.grayscale(input);
        System.out.println("Grayscale");
        output = Binary.binarize(output);
        System.out.println("Binary");
        output = Hough.transform(output);
        System.out.println("Hough");

        ImageIO.write(output, "jpeg", new File("out.jpeg"));
    }
}
