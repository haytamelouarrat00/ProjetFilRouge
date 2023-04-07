package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlHistorique;
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

    PanHistorique panHistorique;
    PanSIdentifier panSIdentifier;
    JPanel panContents = new JPanel();
    JPanel panMenu = new JPanel();
    CardLayout cartes = new CardLayout();
    static JTabbedPane tabbedPane = new JTabbedPane();

    // Le constructeur
    public FrameClient (
            ControlRecherche controlRecherche,ControlMoteurs controlMoteurs,
            ControlResultat controlResultat, ControlHistorique controlHistorique
    ) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        setTitle("Clientèle");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        panResultats = new PanResultats(controlRecherche, controlResultat, TypeRecherche.RECHERCHE_FICHIER);
        panRecherche = new PanRecherche(panResultats);
        panChoixProfil = new PanChoixProfil(panRecherche);
        panChoixProfil.initialisation();
        tabbedPane.addTab("Choix Profil", panChoixProfil);
        panContents.setLayout(cartes);
        panContents.add(tabbedPane);
        getContentPane().add(panContents);



        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ControlRecherche controlRecherche = new ControlRecherche();
        ControlResultat controlResultat = new ControlResultat();
        ControlMoteurs controlMoteurs = new ControlMoteurs();
        ControlHistorique controlHistorique = new ControlHistorique();
        controlMoteurs.randomMoteurs(Parametres.getNbMoteurs());
        controlMoteurs.randomActifs(Parametres.getNbMoteurs()/2);
        new FrameClient(controlRecherche, controlMoteurs,controlResultat, controlHistorique);
    }
}