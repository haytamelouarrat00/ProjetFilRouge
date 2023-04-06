package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.TypeRecherche;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PanRecherche extends JPanel {
    private JButton fileButton;
    private JLabel titleLabel;

    private Box boxMiseEnPage = Box.createVerticalBox();
    private Box boxBarreRecherche = Box.createHorizontalBox();
    private Box boxBouton = Box.createHorizontalBox();
    private JTextField textField;
    private JButton buttonRechercheMC;

    private JButton buttonRechercheF;
    private JButton buttonRechercheC;
    private JButton buttonRechercheE;
    private JButton buttonRetour;
    private PanResultats panResultat;

    public PanRecherche(PanResultats panResultat) {
        this.panResultat = panResultat;
    }

    public void initialisation() {
        titleLabel = new JLabel("ALPHA DOCS", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setPreferredSize(new Dimension(400, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(Color.decode("#7E8D85"));

        ImageIcon icon = new ImageIcon("");
        JLabel picture = new JLabel(icon);
        picture.setPreferredSize(new Dimension(400, 400));


        textField = new JTextField();

        buttonRechercheMC = new JButton("Rechercher");
        buttonRechercheF = new JButton("Fichier");
        buttonRechercheC = new JButton("Couleur");
        buttonRechercheE = new JButton("Extrait");
        buttonRetour = new JButton("Retour");


        boxMiseEnPage.add(titleLabel);
        boxMiseEnPage.add(Box.createVerticalStrut(10));
        boxMiseEnPage.add(picture);
        boxMiseEnPage.add(Box.createVerticalStrut(50));
        boxBarreRecherche.add(textField);
        boxBarreRecherche.add(Box.createHorizontalStrut(5));
        boxBarreRecherche.add(buttonRechercheMC);
        boxMiseEnPage.add(boxBarreRecherche);
        boxMiseEnPage.add(Box.createVerticalStrut(20));
        boxBouton.add(buttonRechercheF);
        boxBouton.add(Box.createHorizontalStrut(200));
        boxBouton.add(buttonRechercheC);
        boxBouton.add(Box.createHorizontalStrut(200));
        boxBouton.add(buttonRechercheE);
        boxMiseEnPage.add(boxBouton);
        boxMiseEnPage.add(Box.createVerticalStrut(40));
        boxMiseEnPage.add(buttonRetour);
        this.add(boxMiseEnPage);

        buttonRechercheMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panResultat.requete = textField.getText();
            }
        });

        buttonRechercheF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(ControlFichier.getCheminRelative()+"\\src\\ProjetFilRouge\\");
                int result = fileChooser.showOpenDialog(buttonRechercheF);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Do something with the selected file

                    // Create a new instance of the panel
                    PanResultats panResultat = new PanResultats(new ControlRecherche(), new ControlResultat(), TypeRecherche.RECHERCHE_FICHIER);
                    panResultat.cheminFichier = selectedFile.getAbsolutePath();

                    System.out.println(FrameClient.tabbedPane.getTabCount());
                    FrameClient.tabbedPane.addTab("Resultats", panResultat);
                    FrameClient.tabbedPane.setSelectedIndex(1);

                    try {
                        panResultat.initialisation();
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        buttonRechercheC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(buttonRechercheC, "Sélectionner une couleur", Color.WHITE);

                System.out.println("Couleur sélectionnée : " + color);
            }
        });

        buttonRechercheE.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(ControlFichier.getCheminRelative()+"\\src\\ProjetFilRouge\\TEST_SON\\");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers audio et texte", "wav", "bin", "txt");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(buttonRechercheE);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String extension = getExtension(selectedFile);
                if (extension != null && (extension.equals("wav") || extension.equals("bin") || extension.equals("txt"))) {
                    System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(buttonRechercheE, "Veuillez sélectionner un fichier avec une extension valide (.wav, .bin ou .txt).", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String getExtension(File file) {
        String extension = null;
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            extension = fileName.substring(index + 1).toLowerCase();
        }
        return extension;
    }
}