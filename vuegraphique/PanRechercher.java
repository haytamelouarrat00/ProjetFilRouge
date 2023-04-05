package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class PanRechercher extends JPanel {
    // controleurs du cas + panel des cas inclus ou etendus en lien avec un acteur
    ControlRecherche controlRecherche;
    ControlResultat controlResultat;
    // les attributs metiers (ex : numClient)
    TypeRecherche typeRecherche;
    Recherche recherche;
    String cheminFichier = "05-Le_Colombien_Juan_Pablo_Montoya_utf8.wav";
    Couleurs couleurs;
    String requete;

    String[] resultats;
    // Les elements graphiques :
    // Declaration et creation des polices d'ecritures
    Font policeTitre = new Font("Calibri", Font.BOLD, 24);
    Font policeParagraphe = new Font("Calibri", Font.ITALIC, 16);
    // Declaration et creation des ComboBox
    // Declaration et creation des Button
    JButton boutonRetour = new JButton();
    JButton boutonOuvrir = new JButton();
    JButton boutonTrouver = new JButton();
    // Declaration et creation des TextArea
    // Declaration et creation des Components
    JList<String> list;
    JSplitPane splitPane;
    JTextArea textArea;
    JScrollPane picScrollPane;
    JScrollPane audioScrollPane;
    JScrollPane listScrollPane;

    JTextField textField;

    JPanel panelAudio;

    PanAudioPlayer panAudioPlayer;

    JLabel image;
    private JLabel titre = new JLabel("Resultats de la recherche");
    private JLabel picture;
    private JScrollPane textScrollPane;
    Highlighter hilit;
    Highlighter.HighlightPainter painter;
    // Mise en page : les Box
    Box boxMiseEnPageTexte = Box.createVerticalBox();
    Box boxMiseEnPageRecherche = Box.createVerticalBox();
    Box boxMiseEnPageResultat = Box.createVerticalBox();
    Box boxMiseEnPageBoutons = Box.createHorizontalBox();
    Box boxMiseEnPageBarreTrouver = Box.createHorizontalBox();

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
        textField = new JTextField(20);
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
                    } else if (ControlFichier.getFileExtension(selected).equals("wav")) {
                        panelAudio.removeAll();
                        panAudioPlayer = new PanAudioPlayer(selected);
                        panAudioPlayer.initialisation();
                        panelAudio.add(panAudioPlayer);
                    } else {
                        throw new IllegalStateException("Unexpected value: " + ControlFichier.getFileExtension(list.getSelectedValue()));
                    }
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //textArea
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(policeParagraphe);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setPreferredSize(new Dimension(550, 450));
        hilit = textArea.getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        textArea.setFont(policeParagraphe);
        boxMiseEnPageBarreTrouver.add(textField);
        boxMiseEnPageBarreTrouver.add(boutonTrouver);
        boxMiseEnPageTexte.add(boxMiseEnPageBarreTrouver);
        boxMiseEnPageTexte.add(textScrollPane);
        JPanel panelText = new JPanel();
        panelText.setLayout(new BorderLayout());
        panelText.add(boxMiseEnPageTexte, BorderLayout.CENTER);
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
        picScrollPane.setPreferredSize(new Dimension(400, 200));
        audioScrollPane = new JScrollPane(panelAudio);
        audioScrollPane.setPreferredSize(new Dimension(550, 450));
        //splitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, switch (ControlFichier.getFileExtension(list.getSelectedValue())) {
            case "xml", "txt" -> panelText;
            case "jpg", "bmp" -> picScrollPane;
            case "wav" -> audioScrollPane;
            default ->
                    throw new IllegalStateException("Unexpected value: " + ControlFichier.getFileExtension(list.getSelectedValue()));
        });
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        //boutons
        boutonOuvrir.setText("Ouvrir");
        boutonOuvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlFichier.ouvrirFichier(list.getSelectedValue());
            }
        });
        boutonRetour.setText("Retour");

        boutonTrouver.setText("Trouver");
        boutonTrouver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightText(textField.getText(), hilit, painter, textArea);
            }
        });


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

    private void highlightText(String str, Highlighter hilit, Highlighter.HighlightPainter painter, JTextArea textArea) {
        int index = textArea.getText().indexOf(str);
        while (index >= 0 && !str.equals("")) {
            int end = index + str.length();
            try {
                hilit.addHighlight(index, end, painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            index = textArea.getText().indexOf(str, end);
        }
    }

}
