//Ayoub Hadi
package ProjetFilRouge.vuegraphique;


import ProjetFilRouge.control.ControlFichier;
import ProjetFilRouge.control.ControlHistorique;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ProjetFilRouge.modele.Mode;
import ProjetFilRouge.modele.TypeFichier;
import com.toedter.calendar.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.ParseException;
//import java.util.Date;

public class PanHistorique extends JPanel {


    private static final long serialVersionUID = 1L;
    private ControlHistorique controlHistorique;
    private PanRecherche panRecherche;

    PanResultats panResultats;

    JPanel panneauOPtions;
    JPanel panneauHistorique;

    private JScrollPane scrollRecherche;
    private JList<String> listeHistorique;

    private JCalendar calendrier1, calendrier2;
    private JButton effacerButton;


    private JButton filtrer;


    public PanHistorique(ControlHistorique controlHistorique) {
        super(new BorderLayout());
        this.controlHistorique = controlHistorique;


        // Création des deux panneaux
        panneauOPtions = new JPanel();
        panneauOPtions.setLayout(new BoxLayout(panneauOPtions, BoxLayout.Y_AXIS));
        panneauHistorique = new JPanel(new BorderLayout());


        // Contenu box Filtrer

        //titre
        JLabel titreFiltres = new JLabel(" Filtrer");
        titreFiltres.setFont(new Font("Arial", Font.BOLD, 18));
        panneauOPtions.add(Box.createVerticalStrut(10));
        panneauOPtions.add(titreFiltres);
        panneauOPtions.add(Box.createVerticalStrut(10));

        //mode
        JLabel modeLabel = new JLabel(" Mode");
        JRadioButton modeFerme = new JRadioButton("Fermé");
        JRadioButton modeOuvert = new JRadioButton("Ouvert");
        //modeFerme.setMargin(new Insets(0, 20, 0, 0));
        //modeOuvert.setMargin(new Insets(0, 20, 0, 0));
        panneauOPtions.add(modeLabel);
        panneauOPtions.add(modeFerme);
        panneauOPtions.add(modeOuvert);

        modeFerme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listeOuvert;
                controlHistorique.filtrerHistorique((Mode.OUVERT));



            }
        });

        //Type
        JLabel typeLabel = new JLabel(" Type de recherche");
        String[] options = {"IMAGE", "AUDIO", "TEXT"};
        JComboBox ButtonParFicher = new JComboBox(options);

        JRadioButton ButtonParMotCle = new JRadioButton("Par mot clé");
        //ButtonParFicher.setMargin(new Insets(0, 20, 0, 0));
        //ButtonParMotCle.setMargin(new Insets(0, 20, 0, 0));
        panneauOPtions.add(Box.createVerticalStrut(10));
        panneauOPtions.add(typeLabel);
        panneauOPtions.add(ButtonParFicher);
        panneauOPtions.add(ButtonParMotCle);


        //Effacer


        JLabel labelEffacer = new JLabel("Effacer");
        //labelEffacer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, ));


        panneauOPtions.add(labelEffacer);

        JButton effacerTout = new JButton();
        panneauOPtions.add(effacerTout);



        // Calendrier
        calendrier1 = new JCalendar();
        calendrier2 = new JCalendar();
        calendrier1.setPreferredSize(new Dimension(50, 50));
        calendrier2.setPreferredSize(new Dimension(50, 50));
        JLabel labelDe = new JLabel("De");
        JLabel labelA = new JLabel("A", JLabel.RIGHT);

        panneauOPtions.add(labelDe);
        panneauOPtions.add(calendrier1);
        panneauOPtions.add(labelA);


        panneauOPtions.add(calendrier2);

        filtrer = new JButton("filtrer");
        panneauOPtions.add(filtrer);


        // Ajout du bouton "retour" dans le panneau de droite
        JButton boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panRecherche = new PanRecherche(panResultats);
                panRecherche.initialisation();
                FrameClient.tabbedPane.removeAll();
                FrameClient.tabbedPane.addTab("Recherche", panRecherche);
                FrameClient.tabbedPane.setSelectedIndex(FrameClient.tabbedPane.getTabCount() - 1);
            }
        });
        boutonRetour.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel panneauBouton = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        panneauBouton.add(boutonRetour);
        panneauBouton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panneauHistorique.add(panneauBouton, BorderLayout.SOUTH);

        // Création de la JList à partir d'un fichier
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ControlFichier.getCheminRelative()+"\\src\\historique.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Créer un modèle de liste pour la JList
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String line : lines) {
            model.addElement(line);
        }

        listeHistorique = new JList<>(model);
        listeHistorique.setFixedCellHeight(30);// Ajout de l'espace entre chaque ligne

        listeHistorique.setPreferredSize(new Dimension(400, 400));

        scrollRecherche = new JScrollPane(listeHistorique);
        panneauHistorique.add(scrollRecherche, BorderLayout.CENTER);
        // Configuration du panneauDroit
        //panneauHistorique.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 5));


        // Création du JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panneauOPtions, panneauHistorique);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(350);
        splitPane.setDividerSize(0);
        add(splitPane, BorderLayout.CENTER);

        // Configuration du JPanel
        setPreferredSize(new Dimension(400, 300));
        effacerTout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlHistorique controlHistorique = new ControlHistorique();
                controlHistorique.effacerHistorique();
                model.clear();
            }
        });

        modeOuvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listeOuvert;
                listeOuvert = controlHistorique.filtrerHistorique((Mode.OUVERT));
                model.clear();
                for (String line : listeOuvert) {
                    model.addElement(line);
                }
            }
        });

        modeFerme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listeOuvert;
                listeOuvert = controlHistorique.filtrerHistorique((Mode.FERME));
                model.clear();
                for (String line : listeOuvert) {
                    model.addElement(line);
                }
            }
        });

        ButtonParFicher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) ButtonParFicher.getSelectedItem();
                ArrayList<String> listeImage = null;
                if (selectedOption == "IMAGE") {
                    listeImage = controlHistorique.filtrerHistorique((TypeFichier.IMAGE));
                    model.clear();
                    for (String line : listeImage) {
                        model.addElement(line);

                    }
                } else if (selectedOption == "AUDIO") {
                    ArrayList<String> listeAudio;
                    listeAudio = controlHistorique.filtrerHistorique((TypeFichier.AUDIO));
                    model.clear();

                    for (String line : listeAudio) {
                        model.addElement(line);


                    }
                } else if (selectedOption == "TEXT") {
                    ArrayList<String> liste;
                    /*try {
                        liste= ControlHistorique.readLines("/home/etud-sri/Documents/ProjetFilRouge-master(1)/ProjetFilRouge-master/historique.txt");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }*/


                    liste = controlHistorique.filtrerHistorique((TypeFichier.TEXTE));
                    model.clear();

                    for (String line : liste) {
                        model.addElement(line);
                    }

                }
                ;

            }

        });}}



