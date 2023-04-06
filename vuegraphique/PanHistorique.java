package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlHistorique;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanHistorique extends JPanel {
    private ControlHistorique controlHistorique;
    private ControlFichier controlFichier;

    private JLabel titreHistorique;
    private JLabel titreOptions;

    private JPanel panOptions;

    private JList<String> listeRecherches;
    private Box boxMiseEnPage = Box.createHorizontalBox();
    private Box boxOptions = Box.createVerticalBox();
    private Box boxHistorique = Box.createVerticalBox();
    private Font policeTitre = new Font("Arial", Font.BOLD, 48);
    private Font policeTexte = new Font("Arial", Font.PLAIN, 15);

    private JScrollPane scrollPaneHistorique;

    private JButton buttonRetour; // boutton retour



    public PanHistorique(ControlHistorique controlHistorique, ControlFichier controlFichier){
        this.controlHistorique = controlHistorique;
        this.controlFichier = controlFichier;
        
    }



    public void initialisation(){
        panOptions = new JPanel();
        titreOptions = new JLabel("Options");
        titreOptions.setFont(policeTitre);
        JLabel titreFiltres = new JLabel("Filtres");
        titreFiltres.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel titreEffacer = new JLabel("Effacer");
        titreEffacer.setFont(new Font("Arial", Font.BOLD, 18));

        JRadioButton dateButton = new JRadioButton("Date");
        JLabel texteDate = new JLabel("A partir de ");

        JRadioButton modeButton = new JRadioButton("Mode");
        JRadioButton modeFerme = new JRadioButton("Fermé");
        JRadioButton modeOuvert = new JRadioButton("Ouvert");
        modeFerme.setMargin(new Insets(0, 20, 0, 0));
        modeOuvert.setMargin(new Insets(0, 20, 0, 0));

        JRadioButton TypeButton = new JRadioButton("Type de recherche");
        JRadioButton ButtonParFicher = new JRadioButton("Par fichier");
        JRadioButton ButtonParMotCle = new JRadioButton("Par mot clé");
        ButtonParFicher.setMargin(new Insets(0, 20, 0, 0));
        ButtonParMotCle.setMargin(new Insets(0, 20, 0, 0));


        ButtonGroup group = new ButtonGroup();
        group.add(dateButton);
        group.add(modeButton);


        buttonRetour = new JButton("Retour");


        titreHistorique = new JLabel("Historique");
        scrollPaneHistorique = new JScrollPane(listeRecherches);


        boxMiseEnPage.add(boxOptions);



        boxHistorique.add(listeRecherches);
        boxHistorique.add(titreHistorique);
        boxHistorique.add(Box.createHorizontalGlue());
        boxHistorique.add(buttonRetour);
        boxMiseEnPage.add(boxHistorique);


        listeRecherches = new JList<String>();





        /*buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/



        this.add(boxMiseEnPage);
    }
    
    
}
