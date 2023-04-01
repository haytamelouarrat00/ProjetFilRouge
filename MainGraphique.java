package ProjetFilRouge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainGraphique {

    public static void main(String[] args) {
        // Read in X, Y and the values from the file
        int xSize = 0;
        int ySize = 0;
        int[][] values = null;
        try (Scanner scanner = new Scanner(new File("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ProjetFilRouge\\TEST_NB\\63.txt"))) {
            xSize = scanner.nextInt();
            ySize = scanner.nextInt();
            values = new int[xSize][ySize];
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    values[x][y] = scanner.nextInt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a BufferedImage with the same dimensions as the input values
        BufferedImage image = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);

        // Iterate through the values and set each pixel color accordingly
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int value = values[x][y];
                Color color = new Color(value, value, value);
                image.setRGB(x, y, color.getRGB());
            }
        }

        // Write the image to file
        try {
            ImageIO.write(image, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
