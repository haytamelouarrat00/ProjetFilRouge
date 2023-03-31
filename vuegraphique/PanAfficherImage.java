package ProjetFilRouge.vuegraphique;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanAfficherImage extends JPanel {
    public PanAfficherImage(String file) {
        // Load the image
        ImageIcon imageIcon = new ImageIcon(file);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

        // Create a JLabel with the scaled image
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Add the JLabel to a JPanel
        JPanel panel = new JPanel();
        panel.add(imageLabel);

        // Add the JPanel to the JFrame and show the frame
        this.add(panel);
        this.setVisible(true);
    }
}
