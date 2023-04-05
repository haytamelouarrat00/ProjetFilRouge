package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.Parametres;
import ProjetFilRouge.modele.TypeRecherche;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameClient extends JFrame {
    // Les attributs metiers (ex : numClient)
    // Declaration et creation des elements graphiques (JLabel)
    // Declaration et creation de la barre de menu (MenuBar)
    // Declaration et creation des differents panels
    PanRechercher panRechercher;
    JPanel panContents = new JPanel();
    CardLayout cartes = new CardLayout();
    // Declaration et creation du gestionnaire des cartes (CardLayout)

    // Le constructeur
    public FrameClient (
            // parametres pour l'initialisation des attributs metiers
            // parametres correspondants aux controleurs des cas utiliser par
            ControlRecherche controlRecherche,
            ControlResultat controlResultat
            // l'acteur en relation avec cette frame
    ) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // initialisation des attributs metiers
        // mise en forme de la frame (titre, dimension, ...)
        setTitle("Clientèle");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // initialisation des differents panels : appel a leur methode d'initialisation
        panRechercher = new PanRechercher(controlRecherche, controlResultat, TypeRecherche.RECHERCHE_FICHIER);
        panRechercher.initialisation();
        // ajout des pannels dans le ContentPane de la Frame
        panContents.setLayout(cartes);
        panContents.add(panRechercher, "RECHERCHER");
        getContentPane().add(panContents);
        // mise en page : mises en place des cartes
        // mise en place du menu
        // appel à la methode d'initialisation du menu
        // appel a la methode d'initialisation de la page d'accueil (optionnel)

        this.setVisible(true);
    }


    private void initialisationMenu() {
    }

    private void initialisationAcceuil(){
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ControlRecherche controlRecherche = new ControlRecherche();
        ControlResultat controlResultat = new ControlResultat();
        ControlMoteurs controlMoteurs = new ControlMoteurs();
        controlMoteurs.randomMoteurs(Parametres.getNbMoteurs());
        controlMoteurs.randomActifs(Parametres.getNbMoteurs()/2);
        new FrameClient(controlRecherche, controlResultat);
    }
}