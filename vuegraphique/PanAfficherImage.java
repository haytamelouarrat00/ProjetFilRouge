package ProjetFilRouge.vuegraphique;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanAfficherImage extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));

        // Load the image
        ImageIcon imageIcon = new ImageIcon(this.file);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        // Create a JLabel with the scaled image
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Add the JLabel to a JPanel
        JPanel panel = new JPanel();
        panel.add(imageLabel);

        // Add the JPanel to the JFrame and show the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
