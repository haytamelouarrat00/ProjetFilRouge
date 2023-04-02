package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class PanRechercher extends JPanel {
    // controleurs du cas + panel des cas inclus ou etendus en lien avec un acteur
    ControlRecherche controlRecherche;
    ControlResultat controlResultat;
    // les attributs metiers (ex : numClient)
    TypeRecherche typeRecherche;
    Recherche recherche;
    String cheminFichier = "53.bmp";
    Couleurs couleurs;
    String requete;

    String[] resultats;
    // Les elements graphiques :
    // Declaration et creation des polices d'ecritures
    Font policeTitre = new Font("Calibri", Font.BOLD, 24);
    Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);
    // Declaration et creation des ComboBox
    // Declaration et creation des Button
    JButton boutonRetour = new JButton();
    JButton boutonOuvrir = new JButton();
    // Declaration et creation des TextArea
    // Declaration et creation des Components
    JList<String> list;
    JSplitPane splitPane;
    TextArea textArea;
    JScrollPane picScrollPane;
    JScrollPane audioScrollPane;
    JScrollPane listScrollPane;

    JPanel panelAudio;

    PanAudioPlayer panAudioPlayer;

    JLabel image;
    private JLabel titre = new JLabel("Resultats de la recherche");
    private JLabel picture;
    // Mise en page : les Box
    Box boxMiseEnPageRecherche = Box.createVerticalBox();
    Box boxMiseEnPageResultat = Box.createVerticalBox();
    Box boxMiseEnPageBoutons = Box.createHorizontalBox();

    public PanRechercher(ControlRecherche controlRecherche, ControlResultat controlResultat, TypeRecherche typeRecherche) {
        // initialisation des attributs metiers
        this.typeRecherche = typeRecherche;
        // initilaisation du controleur du cas + panels
        this.controlRecherche = controlRecherche;
        this.controlResultat = controlResultat;
        // des cas inclus ou etendus en lien avec un acteur
    }

    //Methode d'initialisation du panel
    public void initialisation() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        recherche = rechercher(typeRecherche);
        assert recherche != null;
        resultats = controlResultat.getCheminsResultats(recherche);

        JLabel titreRes = new JLabel("Resultats de la recherche");
        titre.setFont(policeTitre);

        list = new JList<>(resultats);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JList<String> source = (JList<String>) e.getSource();
                String selected = source.getSelectedValue();
                try {
                    if (ControlFichier.getFileExtension(list.getSelectedValue()).equals("xml") || ControlFichier.getFileExtension(list.getSelectedValue()).equals("txt")) {
                        textArea.setText(ControlFichier.readFileAsString(selected));
                    } else if (ControlFichier.getFileExtension(list.getSelectedValue()).equals("jpg") || ControlFichier.getFileExtension(list.getSelectedValue()).equals("bmp")) {
                        ImageIcon icon = new ImageIcon(ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(list.getSelectedValue())) + list.getSelectedValue());
                        picture.setIcon(icon);
                    } else if (ControlFichier.getFileExtension(list.getSelectedValue()).equals("wav")){
                        panelAudio.removeAll();
                        panAudioPlayer = new PanAudioPlayer(list.getSelectedValue());
                        panAudioPlayer.initialisation();
                        panelAudio.add(panAudioPlayer);
                    }else {
                        throw new IllegalStateException("Unexpected value: " + ControlFichier.getFileExtension(list.getSelectedValue()));
                    }
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //textArea
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFont(policeParagraphe);
        //image
        panelAudio = new JPanel();
        panelAudio.setLayout(new CardLayout());
        panAudioPlayer = new PanAudioPlayer(list.getSelectedValue());
        if (ControlFichier.getFileExtension(list.getSelectedValue()).equals("wav")) {
            panAudioPlayer.initialisation();
        }
        panelAudio.add(panAudioPlayer);
        //scrollPanes
        listScrollPane = new JScrollPane(list);

        ImageIcon icon = null;
        if (Objects.equals(ControlFichier.getFileExtension(list.getSelectedValue()), "jpg") || Objects.equals(ControlFichier.getFileExtension(list.getSelectedValue()), "bmp")) {
            icon = new ImageIcon(ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(list.getSelectedValue())) + list.getSelectedValue());
        }
        picture = new JLabel(icon);
        JPanel IMpanel = new JPanel();
        IMpanel.add(picture);
        picScrollPane = new JScrollPane(IMpanel);
        audioScrollPane = new JScrollPane(panelAudio);
        //splitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, switch (ControlFichier.getFileExtension(list.getSelectedValue())) {
            case "xml", "txt" -> textArea;
            case "jpg", "bmp" -> picScrollPane;
            case "wav" -> audioScrollPane;
            default ->
                    throw new IllegalStateException("Unexpected value: " + ControlFichier.getFileExtension(list.getSelectedValue()));
        });
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        splitPane.setPreferredSize(new Dimension(300, 300));
        //boutons
        boutonOuvrir.setText("Ouvrir");
        boutonOuvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlFichier.ouvrirFichier(list.getSelectedValue());
            }
        });
        boutonRetour.setText("Retour");

        // mise en page : placements des differents elements graphiques dans des Box
        boxMiseEnPageResultat.add(titreRes);
        boxMiseEnPageResultat.add(splitPane);
        boxMiseEnPageBoutons.add(boutonOuvrir);
        boxMiseEnPageBoutons.add(boutonRetour);
        // mise en page : placements des differentes box dans une box principale
        boxMiseEnPageRecherche.add(titre);
        boxMiseEnPageRecherche.add(boxMiseEnPageResultat);
        boxMiseEnPageRecherche.add(boxMiseEnPageBoutons, BorderLayout.EAST);
        // mise en page : ajout de la box principale dans le panel
        this.add(boxMiseEnPageRecherche);
    }

    // Methodes privees pour le bon deroulement du cas
    private Recherche rechercher(TypeRecherche typeRecherche) {
        switch (typeRecherche) {
            case RECHERCHE_FICHIER:
                return controlRecherche.rechercherFichier(cheminFichier);
            case RECHERCHE_MOT_CLE:
                return controlRecherche.rechercherMotCle(requete);
            case RECHERCHE_IMAGE:
                return controlRecherche.rechercherImage(couleurs);
            case RECHERCHE_AUDIO:
                return controlRecherche.rechercherSon(cheminFichier);
            default:
                return null;
        }
    }
}
