package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
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
    String cheminFichier;
    Couleurs couleurs = Couleurs.BLEU;
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
    ImageIcon imageIcon;
    JScrollPane picScrollPane;

    private JLabel titre = new JLabel("Resultats de la recherche");
    private JLabel image;
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
    public void initialisation() {
        recherche = rechercher(typeRecherche);
        resultats = controlResultat.getCheminsResultats(recherche);
        // mise en forme du panel (couleur, ...)
        setBackground(Color.RED);
        // creation des differents elements graphiques (JLabel, Combobox, Button, TextAera ...)
        JLabel titreRes = new JLabel("Resultats de la recherche");
        titre.setFont(policeTitre);
        list = new JList<>(resultats);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(0);
        list.setSelectedIndex(0);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JList<String> source = (JList<String>) e.getSource();
                String selected = source.getSelectedValue();
                try {
                    textArea.setText(ControlFichier.readFileAsString(selected));
                    updateLabel(resultats[list.getSelectedIndex()]);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                };
            }
        });
        String choice = list.getSelectedValue();
        //textArea
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFont(policeParagraphe);
        //imageViewer
        picScrollPane = new JScrollPane(image);
        Dimension minimumSize = new Dimension(100, 50);
        picScrollPane.setMinimumSize(minimumSize);

        image = new JLabel();
        image.setFont(image.getFont().deriveFont(Font.ITALIC));
        image.setHorizontalAlignment(JLabel.CENTER);
        //splitPane
        //splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, textArea);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, switch (Objects.requireNonNull(TypeFichier.getTypeFromExtension(ControlFichier.getFileExtension(list.getSelectedValue())))){

            case TEXTE -> textArea;
            case IMAGE -> picScrollPane;
            case AUDIO -> null;
        });
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        splitPane.setPreferredSize(new Dimension(400, 200));
        //boutons
        boutonOuvrir.setText("Ouvrir");
        boutonOuvrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlFichier.ouvrirFichier(choice);
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
    private Recherche rechercher(TypeRecherche typeRecherche){
        switch (typeRecherche){
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
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList)e.getSource();
        updateLabel(resultats[list.getSelectedIndex()]);
    }
    protected void updateLabel (String path) {
        ImageIcon icon = createImageIcon(path);
        image.setIcon(icon);
        if  (icon != null) {
            image.setText(null);
        } else {
            image.setText("Image not found");
        }
    }
    protected static ImageIcon createImageIcon(String path) {
        path = ControlFichier.getCheminRelative()+TypeFichier.getRepertoireResultatFromExtension(ControlFichier.getFileExtension(path))+path;
        ImageIcon im = new ImageIcon(path);
        if (im != null) {
            return new ImageIcon(path);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}