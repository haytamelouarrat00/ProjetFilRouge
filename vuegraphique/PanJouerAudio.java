package ProjetFilRouge.vuegraphique;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import javax.swing.*;

public class PanJouerAudio extends JPanel implements ActionListener {
    String file;
    private JButton playButton;
    private Clip clip;

    public PanJouerAudio(String file) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playButton = new JButton("Play Audio");
        playButton.addActionListener(this);
        this.file = file;
        this.add(playButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.file));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }
}
