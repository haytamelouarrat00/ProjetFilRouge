package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.modele.TypeFichier;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

public class PanAudioPlayer extends JPanel implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    private Clip clip;
    private JButton playButton, pauseButton, stopButton;

    private Box boxMiseEnPageBoutons = Box.createHorizontalBox();
    private Box boxMiseEnPageAudioPlayer = Box.createVerticalBox();

    String audioFile;
    public PanAudioPlayer(String audioFile) {
        this.audioFile = ControlFichier.getCheminRelative()+"\\src\\ProjetFilRouge\\TEST_SON\\"+audioFile;

    }

    public void initialisation() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        File file = new File(audioFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        System.out.println(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        boxMiseEnPageBoutons.add(playButton);
        boxMiseEnPageBoutons.add(pauseButton);
        boxMiseEnPageBoutons.add(stopButton);
        boxMiseEnPageAudioPlayer.add(boxMiseEnPageBoutons);
        this.add(boxMiseEnPageAudioPlayer);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            assert clip != null;
            clip.setFramePosition(0);
            clip.start();
        } else if (e.getSource() == pauseButton) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        } else if (e.getSource() == stopButton) {
            if (clip != null) {
                clip.stop();
                clip.setFramePosition(0);
            }
        }
    }
}
