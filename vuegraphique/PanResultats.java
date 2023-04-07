package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class PanResultats extends JPanel {
    ControlRecherche controlRecherche;
    ControlResultat controlResultat;
    TypeRecherche typeRecherche;
    Recherche recherche;
    String cheminFichier;
    Color couleurs;
    String requete;
    String[] resultats;
    Font policeTitre = new Font("Calibri", Font.BOLD, 48);
    Font policeParagraphe = new Font("Calibri", Font.ITALIC, 16);
    JButton boutonOuvrir = new JButton();
    JButton boutonRetour = new JButton();
    JButton boutonTrouver = new JButton();

    JButton boutonInfo = new JButton();
    JList<String> list;
    JSplitPane splitPane;
    JTextArea textArea;
    JScrollPane picScrollPane;
    JScrollPane audioScrollPane;
    JScrollPane listScrollPane;
    JTextField textField;
    JPanel panelAudio;
    JPanel panelListe;
    PanAudioPlayer panAudioPlayer;
    private JLabel titre = new JLabel("Resultats de la recherche");
    private JLabel picture;
    private JScrollPane textScrollPane;
    Highlighter hilit;
    Highlighter.HighlightPainter painter;
    Box boxMiseEnPageTexte = Box.createVerticalBox();
    Box boxMiseEnPageEnTete = Box.createHorizontalBox();
    Box boxMiseEnPageRecherche = Box.createVerticalBox();
    Box boxMiseEnPageResultat = Box.createVerticalBox();
    Box boxMiseEnPageBoutons = Box.createHorizontalBox();
    Box boxMiseEnPageBarreTrouver = Box.createHorizontalBox();
    Box boxMiseEnPageListeResultats = Box.createVerticalBox();
    ImageIcon imageLogo;

    public PanResultats(ControlRecherche controlRecherche, ControlResultat controlResultat, TypeRecherche typeRecherche) {
        this.setBackground(Color.decode("#7E8D85"));
        this.typeRecherche = typeRecherche;
        this.controlRecherche = controlRecherche;
        this.controlResultat = controlResultat;
    }

    public void initialisation() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        recherche = rechercher(typeRecherche);
        assert recherche != null;
        resultats = controlResultat.getCheminsResultats(recherche);

        titre.setFont(policeTitre);
        JLabel titreRes = new JLabel(recherche.getResultats().size()+" resultats: ");
        titreRes.setFont(new Font("Calibri", Font.BOLD, 18));
        titreRes.setHorizontalAlignment(SwingConstants.CENTER);

        list = new JList<>(resultats);
        list.setBackground(Color.decode("#B3BFB8"));
        list.setSelectionBackground(Color.decode("#7E8D85"));
        list.setSelectionForeground(Color.WHITE);
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
                    } else if (ControlFichier.getFileExtension(selected).equals("wav") || ControlFichier.getFileExtension(selected).equals("mp3")) {
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
        listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(300, 450));
        boxMiseEnPageListeResultats.add(titreRes);
        boxMiseEnPageListeResultats.add(listScrollPane);
        panelListe = new JPanel();
        panelListe.setBackground(Color.decode("#7E8D85"));
        panelListe.setLayout(new BorderLayout());
        panelListe.add(boxMiseEnPageListeResultats);
        panelListe.setPreferredSize(new Dimension(300, 450));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(policeParagraphe);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = textScrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMinimum());
        textScrollPane.setPreferredSize(new Dimension(750, 450));


        hilit = textArea.getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        textField = new JTextField(20);
        textArea.setFont(policeParagraphe);
        boxMiseEnPageBarreTrouver.add(textField);
        boxMiseEnPageBarreTrouver.add(boutonTrouver);
        boxMiseEnPageTexte.add(boxMiseEnPageBarreTrouver);
        boxMiseEnPageTexte.add(Box.createVerticalStrut(3));
        boxMiseEnPageTexte.add(textScrollPane);
        JPanel panelText = new JPanel();
        panelText.setLayout(new BorderLayout());
        panelText.add(boxMiseEnPageTexte, BorderLayout.CENTER);


        panelAudio = new JPanel();
        panelAudio.setLayout(new CardLayout());
        panAudioPlayer = new PanAudioPlayer(list.getSelectedValue());
        if (ControlFichier.getFileExtension(list.getSelectedValue()).equals("wav")) {
            panAudioPlayer.initialisation();
        }
        panelAudio.add(panAudioPlayer);
        audioScrollPane = new JScrollPane(panelAudio);
        audioScrollPane.setPreferredSize(new Dimension(550, 450));

        ImageIcon icon = null;
        if (Objects.equals(ControlFichier.getFileExtension(list.getSelectedValue()), "jpg") || Objects.equals(ControlFichier.getFileExtension(list.getSelectedValue()), "bmp")) {
            icon = new ImageIcon(ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(list.getSelectedValue())) + list.getSelectedValue());
        }
        picture = new JLabel(icon);
        JPanel IMpanel = new JPanel();
        IMpanel.add(picture);
        picScrollPane = new JScrollPane(IMpanel);
        picScrollPane.setPreferredSize(new Dimension(400, 200));


        //splitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelListe, switch (ControlFichier.getFileExtension(list.getSelectedValue())) {
            case "xml", "txt" -> panelText;
            case "jpg", "bmp" -> picScrollPane;
            case "wav", "mp3" -> audioScrollPane;
            default ->
                    throw new IllegalStateException("Unexpected value: " + ControlFichier.getFileExtension(list.getSelectedValue()));
        });
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(350);
        BasicSplitPaneDivider divider = (BasicSplitPaneDivider) splitPane.getComponent(2);
        divider.setDividerSize(0);
        splitPane.setBorder(null);
        //boutons
        boutonOuvrir.setText("Ouvrir");
        boutonRetour.setText("Fermer");
        boutonOuvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlFichier.ouvrirFichier(list.getSelectedValue());
            }
        });
        boutonTrouver.setText("Trouver");
        boutonTrouver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightText(textField.getText(), hilit, painter, textArea);
            }
        });

        boutonOuvrir.setFont(new Font("Arial", Font.BOLD, 15));
        boutonOuvrir.setBackground(Color.decode("#B3BFB8"));
        boutonTrouver.setFont(new Font("Arial", Font.BOLD, 15));
        boutonTrouver.setBackground(Color.decode("#B3BFB8"));
        boutonRetour.setFont(new Font("Arial", Font.BOLD, 15));
        boutonRetour.setBackground(Color.decode("#B3BFB8"));

        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClient.tabbedPane.removeTabAt(FrameClient.tabbedPane.getSelectedIndex());
            }
        });
        boxMiseEnPageResultat.add(titre);
        boxMiseEnPageResultat.add(splitPane);
        boxMiseEnPageBoutons.add(boutonOuvrir);
        boxMiseEnPageBoutons.add(boutonRetour);
        boxMiseEnPageRecherche.add(boxMiseEnPageResultat);
        boxMiseEnPageRecherche.add(Box.createVerticalStrut(10));
        boxMiseEnPageRecherche.add(boxMiseEnPageBoutons);
        boxMiseEnPageBoutons.setAlignmentX(Box.LEFT_ALIGNMENT);
        boxMiseEnPageRecherche.setBackground(Color.RED);
        this.add(boxMiseEnPageRecherche, BorderLayout.CENTER);
    }

    private Recherche rechercher(TypeRecherche typeRecherche) {
        return switch (typeRecherche) {
            case RECHERCHE_FICHIER -> controlRecherche.rechercherFichier(cheminFichier);
            case RECHERCHE_MOT_CLE -> controlRecherche.rechercherMotCle(requete);
            case RECHERCHE_IMAGE -> controlRecherche.rechercherImage(couleurs);
            case RECHERCHE_AUDIO -> controlRecherche.rechercherSon(cheminFichier);
        };
    }

    private void highlightText(String str, Highlighter hilit, Highlighter.HighlightPainter painter, JTextArea textArea) {
        hilit.removeAllHighlights();
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
