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
    private JPanel panHistorique;

    private JList<String> listeRecherches;
    private Box boxMiseEnPage = Box.createHorizontalBox();
    private Box boxOptions = Box.createVerticalBox();
    private Box boxHistorique = Box.createVerticalBox();
    private Box boxFiltrer = Box.createVerticalBox();
    private Box boxEffacer = Box.createVerticalBox();
    private JButton effacerTout;

    private Font policeTitre = new Font("Arial", Font.BOLD, 48);
    private Font policeTexte = new Font("Arial", Font.PLAIN, 15);

    private JScrollPane scrollPaneHistorique;

    private JButton buttonRetour;



    public PanHistorique(ControlHistorique controlHistorique){
        this.controlHistorique = controlHistorique;
        
    }



    public void initialisation(){
        // initialisation des attributs
        listeRecherches = new JList<String>();

        //Initialisation des panels
        panOptions = new JPanel();
        panHistorique = new JPanel();

        //contenu panel options
        titreOptions = new JLabel("Options");
        titreOptions.setFont(policeTitre);
        panOptions.add(titreOptions);
        boxFiltrer.add(titreOptions);


        JLabel titreFiltres = new JLabel("Filtrer");
        titreFiltres.setFont(new Font("Arial", Font.BOLD, 18));
        /*
        JLabel titreEffacer = new JLabel("Effacer");
        titreEffacer.setFont(new Font("Arial", Font.BOLD, 18));
       */
        boxFiltrer.add(titreFiltres);


        JRadioButton dateButton = new JRadioButton("Par date");
        JLabel texteDate = new JLabel("A partir de ");

        JLabel modeButton = new JLabel("Mode");
        JRadioButton modeFerme = new JRadioButton("Fermé");
        JRadioButton modeOuvert = new JRadioButton("Ouvert");
        modeFerme.setMargin(new Insets(0, 20, 0, 0));
        modeOuvert.setMargin(new Insets(0, 20, 0, 0));
        boxFiltrer.add(modeButton);
        boxFiltrer.add(modeFerme);
        boxFiltrer.add(modeOuvert);


        JLabel typeButton = new JLabel("Type de recherche");
        JRadioButton ButtonParFicher = new JRadioButton("Par fichier");
        JRadioButton ButtonParMotCle = new JRadioButton("Par mot clé");
        ButtonParFicher.setMargin(new Insets(0, 20, 0, 0));
        ButtonParMotCle.setMargin(new Insets(0, 20, 0, 0));
        boxFiltrer.add(ButtonParFicher);
        boxFiltrer.add(ButtonParMotCle);




        ButtonGroup group = new ButtonGroup();
        group.add(dateButton);
        //group.add(typeButton);

        group.add(modeOuvert);
        group.add(modeFerme);


        buttonRetour = new JButton("Retour");


        titreHistorique = new JLabel("Historique");
        scrollPaneHistorique = new JScrollPane(listeRecherches);


        boxMiseEnPage.add(boxOptions);



        boxHistorique.add(titreHistorique);
        boxHistorique.add(listeRecherches);
        boxHistorique.add(Box.createHorizontalGlue());
        boxHistorique.add(buttonRetour);

        boxMiseEnPage.add(boxHistorique);


        /*buttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/



        this.add(boxMiseEnPage);
    }
    
    
}
