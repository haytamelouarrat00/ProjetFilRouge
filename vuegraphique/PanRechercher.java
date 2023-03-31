package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.Recherche;
import ProjetFilRouge.modele.Resultat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;

public class PanRechercher extends JPanel {
    // controleurs du cas + panel des cas inclus ou etendus en lien avec un acteur
    ControlRecherche controlRecherche;
    ControlResultat controlResultat;
    ControlFichier controlFichier;

    //TODO:panels
    PanJouerAudio panJouerAudio;
    // les attributs metiers (ex : numClient)
    String pathRecherche;
    Recherche recherche;
    // Les elements graphiques :
    // Declaration et creation des polices d'ecritures
    Font policeTitre = new Font("Calibri", Font.BOLD, 24);
    Font policeParagraphe = new Font("Calibri", Font.ITALIC, 16);
    // Declaration et creation des Button
    Box boxValiderChoix = Box.createHorizontalBox();
    private JButton validerRecherche = new JButton();
    private JButton retour = new JButton();
    private JButton suivant = new JButton();
    private JButton precedent = new JButton();


    // Declaration et creation des Labels

    private JLabel titreResultat = new JLabel();
    private JLabel retourLabel = new JLabel();
    private JLabel suivantLabel = new JLabel();
    private JLabel precedentLabel = new JLabel();
    // Mise en page : les Box
    private Box boxMiseEnPageRecherche = Box.createVerticalBox();
    private Box boxMiseEnPageResultat = Box.createVerticalBox();
    private Box boxMiseEnPageBouton = Box.createHorizontalBox();


    JPanel panelResultats = new JPanel();

    /**
     *
     */

    @Serial
    private static final long serialVersionUID = -5100344924935036107L;

    public PanRechercher(
            // parametres pour l'initialisation des attributs metiers
            // parametres correspondants au controleur du cas + panels
            ControlRecherche controlRecherche,
            ControlResultat controlResultat,
            ControlFichier controlFichier,
            PanJouerAudio panJouerAudio
            // des cas inclus ou etendus en lien avec un acteur
    ) {
        // initialisation des attributs metiers
        // initilaisation du controleur du cas + panels
        this.controlRecherche = controlRecherche;
        this.controlResultat = controlResultat;
        this.controlFichier = controlFichier;
        this.panJouerAudio = panJouerAudio;
        // des cas inclus ou etendus en lien avec un acteur
    }

    //Methode d'initialisation du panel
    public void initialisation() {
        // mise en forme du panel (couleur, ...)
        setBackground(Color.ORANGE);
        // creation des differents elements graphiques (JLabel, Combobox, Button, TextAera ...)
        JLabel titreRecherche = new JLabel("Résultats de la recherche: " + this.recherche.getResultats().size() + ": ");
        titreRecherche.setFont(policeTitre);
        JLabel titreResultat = new JLabel("Résultats:");
        titreResultat.setFont(policeTitre);
        JLabel titreFichier = new JLabel("Fichier:");
        titreFichier.setFont(policeTitre);
        JList<Resultat> listeResultat = new JList<>(this.recherche.getResultats().toArray(new Resultat[this.recherche.getResultats().size()]));
        JSplitPane splitBane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listeResultat, panelResultats);

        retour.setText("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: retour
            }
        });
        suivant.setText("Suivant");
        suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: suivant
            }
        });
        precedent.setText("Précédent");
        suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: précédent
            }
        });


        boxMiseEnPageResultat.add(titreResultat);
        boxMiseEnPageResultat.add(splitBane);

        // mise en page : placements des differents elements graphiques dans des Box

        boxMiseEnPageBouton.add(retour);
        boxMiseEnPageBouton.add(suivant);
        boxMiseEnPageBouton.add(precedent);
        // mise en page : placements des differentes box dans une box principale
        boxMiseEnPageRecherche.add(titreRecherche);
        boxMiseEnPageRecherche.add(boxMiseEnPageResultat);
        boxMiseEnPageRecherche.add(boxMiseEnPageBouton);
        // mise en page : ajout de la box principale dans le panel
        this.add(boxMiseEnPageRecherche);
    }

    // Methode correspondante au nom du cas
    public void afficherResultats(Recherche recherche, String cheminRecherche) {
        switch (recherche.getTypeFichier()) {
            case TEXTE -> {
                this.panelResultats.add(new PanAfficherTexte(new File(ControlFichier.getCheminRelative()+cheminRecherche)));
            }
            case AUDIO -> {
                this.panelResultats.add(new PanJouerAudio(cheminRecherche));
            }
            case IMAGE -> {
                this.panelResultats.add(new PanAfficherImage(cheminRecherche));
            }
        }
    }

    public void ouvrirResultats(String path) {

    }
}

