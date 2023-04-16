//El Ouarrat Haytam
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

public class PanAudioPlayer extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private Clip clip;
    private JButton playButton, pauseButton, stopButton;

    private Box boxMiseEnPageBoutons = Box.createHorizontalBox();
    private Box boxMiseEnPageAudioPlayer = Box.createVerticalBox();

    String audioFile;
    public PanAudioPlayer(String audioFile) {
        this.audioFile = ControlFichier.getCheminRelative()+TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(audioFile))+audioFile;
    }

    public void initialisation() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
                assert clip != null;
                clip.setFramePosition(0);
                clip.start();
            }
        });
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
            }
        });
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null) {
                    clip.stop();
                    clip.setFramePosition(0);
                }
            }
        });
        File file = new File(this.audioFile);
        System.out.println("Chemin du fichier audio : "+this.audioFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        boxMiseEnPageAudioPlayer.add(new JLabel("Audio Player"));
        boxMiseEnPageAudioPlayer.add(new JLabel(Long.toString(getClipDuration())));

        boxMiseEnPageBoutons.add(playButton);
        boxMiseEnPageBoutons.add(pauseButton);
        boxMiseEnPageBoutons.add(stopButton);
        boxMiseEnPageAudioPlayer.add(boxMiseEnPageBoutons);
        this.add(boxMiseEnPageAudioPlayer);
    }

    public void setClip(String audioFile){
        this.audioFile = ControlFichier.getCheminRelative()+TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(audioFile))+audioFile;
    }
    //function to get the duration of the clip
    public long getClipDuration() {
        return clip.getMicrosecondLength()/1000000;
    }
}
