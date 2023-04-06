package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.Parametres;
import ProjetFilRouge.modele.Profil;
import ProjetFilRouge.modele.TypeRecherche;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class FrameClient extends JFrame{

    public static Profil profil = null;
    PanResultats panResultats;

    PanAdmin panAdmin;
    PanRecherche panRecherche;
    PanChoixProfil panChoixProfil;

    PanSIdentifier panSIdentifier;
    JPanel panContents = new JPanel();
    JPanel panMenu = new JPanel();
    CardLayout cartes = new CardLayout();

    // Le constructeur
    public FrameClient (
            ControlRecherche controlRecherche,ControlMoteurs controlMoteurs,
            ControlResultat controlResultat
    ) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        setTitle("Clientèle");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        panResultats = new PanResultats(controlRecherche, controlResultat, TypeRecherche.RECHERCHE_FICHIER);
        panSIdentifier = new PanSIdentifier();
        panRecherche = new PanRecherche();
        panChoixProfil = new PanChoixProfil(panRecherche);
        panAdmin = new PanAdmin(controlMoteurs);

        panAdmin.initialisation();
        panContents.setLayout(cartes);
        panContents.add(panAdmin, "ADMIN");
        getContentPane().add(panContents);
        /*while(profil == null){
            panChoixProfil.initialisation();
            panContents.setLayout(cartes);
            panContents.add(panChoixProfil, "RECHERCHER");
            getContentPane().add(panContents);
        }
        if(profil == Profil.UTILISATEUR){
            System.out.println("Utilisateur");
        }*/

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ControlRecherche controlRecherche = new ControlRecherche();
        ControlResultat controlResultat = new ControlResultat();
        ControlMoteurs controlMoteurs = new ControlMoteurs();
        controlMoteurs.randomMoteurs(Parametres.getNbMoteurs());
        controlMoteurs.randomActifs(Parametres.getNbMoteurs()/2);
        new FrameClient(controlRecherche, controlMoteurs,controlResultat);
    }
}