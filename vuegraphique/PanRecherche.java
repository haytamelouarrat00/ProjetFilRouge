package ProjetFilRouge.vuegraphique;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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


    public PanRecherche() {
    }

    public void initialisation() {
        titleLabel = new JLabel("ALPHA DOCS", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setPreferredSize(new Dimension(400, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(Color.decode("#7E8D85"));

        ImageIcon icon = new ImageIcon("C:\\Users\\SM\\Downloads\\TD_COO\\PFR\\src\\logo_icon1.png");
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
                System.out.println(textField.getText());
            }
        });

        buttonRechercheF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(buttonRechercheF);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Do something with the selected file
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
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
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\SM\\Downloads\\TD_COO\\PFR\\src\\fichier\\TEST_SON");
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