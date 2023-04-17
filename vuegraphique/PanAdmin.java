//Amal Ferhani
package ProjetFilRouge.vuegraphique;


import ProjetFilRouge.control.*;
import ProjetFilRouge.modele.Moteur;
import ProjetFilRouge.modele.Parametres;
import ProjetFilRouge.modele.TypeFichier;
import ProjetFilRouge.modele.TypeRecherche;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Font.BOLD;


public class PanAdmin extends JPanel{
    private ImageIcon imageIconAdmin;

    private ImageIcon imageIconLogo;

    private Box boxTete = Box.createHorizontalBox();

    private Box boxMiseEnPage = Box.createVerticalBox();

    private Box boxMode = Box.createHorizontalBox();

    private Box boxBits = Box.createHorizontalBox();

    private Box boxseuilSimCoul = Box.createHorizontalBox();

    private Box boxseuilSimMin = Box.createHorizontalBox();

    private Box boxTailleFen = Box.createHorizontalBox();

    private Box boxNbFen = Box.createHorizontalBox();

    private Box boxMoteurs = Box.createVerticalBox();

    private Box boxMotsCleMin = Box.createHorizontalBox();

    private Box boxBouton = Box.createHorizontalBox();

    //partie droite de la box mise en page
    private JComboBox<String> combo_mode;
    private JComboBox<String> combo_bits;
    private JSlider sliderSimColor;
    private TextField motCleMin;
    private JSlider sliderSeuilMin;
    private TextField tailleFen;
    private TextField nbFen;
    private JScrollPane moteurs;
    private JLabel pourc_seuilCoul;
    private JLabel pourc_seuilMin;

    private JButton boutonRetour;
    private JButton enregistrer;

    //partie gauche de la box mise en page
    private JLabel label_param;
    private JLabel label_mode;
    private JLabel label_bits;
    private JLabel label_seuilSimCoul;
    private JLabel label_motsCles;
    private JLabel label_seuilSimMin;
    private JLabel label_tailleFen;
    private JLabel label_nbFen;
    private JLabel label_moteurs;
    private ControlMoteurs controlMoteurs;
    private PanChoixProfil panChoixProfil;


    public PanAdmin(ControlMoteurs controlMoteurs){
        this.controlMoteurs = controlMoteurs;
    }

    public void initialisation(){
        Font font1 = new Font("Arial", Font.BOLD, 36);
        Font font2 = new Font("Arial", Font.BOLD, 15);

        this.setBackground(Color.decode("#B3BFB8"));
        combo_mode = new JComboBox<>();
        combo_bits = new JComboBox<>();
        sliderSimColor = new JSlider();
        sliderSeuilMin = new JSlider();

        motCleMin = new TextField();
        tailleFen = new TextField();
        nbFen = new TextField();
        pourc_seuilCoul = new JLabel();
        pourc_seuilMin = new JLabel();
        ArrayList<JCheckBox> listeMoteurs = new ArrayList<>();
        for (int i = 0; i < Parametres.getNbMoteurs(); i++) {
            listeMoteurs.add(new JCheckBox(Moteur.getMoteurs().get(i).getNom()));
            if (controlMoteurs.isMoteurActif(controlMoteurs.getMoteurbyNom(Moteur.getMoteurs().get(i).getNom()))) {
                listeMoteurs.get(i).setSelected(true);
            }
        }

        sliderSimColor.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    float value = source.getValue();
                    pourc_seuilCoul.setText(String.format("%.0f%%", value));

                }
            }
        });

        sliderSeuilMin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    float value = source.getValue();
                    pourc_seuilMin.setText(String.format("%.0f%%", value));
                }
            }
        });

        //image logo
        imageIconAdmin = new ImageIcon(ControlFichier.getCheminRelative()+"\\ressources\\fichier.png");
        JLabel labelImageAdmin = new JLabel(imageIconAdmin);
        labelImageAdmin.setBounds(0, 0, 100, 100);

        //image admin
        imageIconLogo = new ImageIcon(ControlFichier.getCheminRelative()+"\\ressources\\fichier.png");
        JLabel labelImageLogo = new JLabel(imageIconLogo);
        labelImageLogo.setBounds(0, 0, 100, 100);

        //bouton retour
        boutonRetour = new JButton("Retour");

        enregistrer = new JButton("Enregistrer");

        //labels
        label_param = new JLabel("Paramètres");
        label_param.setFont(font1);
        label_param.setForeground(Color.decode("#3C493F"));
        label_mode = new JLabel("Mode :");
        label_mode.setFont(font2);
        label_bits = new JLabel("Bits quantification :");
        label_bits.setFont(font2);
        label_seuilSimCoul = new JLabel("Seuil similarité couleur :");
        label_seuilSimCoul.setFont(font2);
        label_motsCles = new JLabel("Mots clés :");
        label_motsCles.setFont(font2);
        label_seuilSimMin = new JLabel("Seuil similarité min :");
        label_seuilSimMin.setFont(font2);
        label_tailleFen = new JLabel("Taille fenêtre :");
        label_tailleFen.setFont(font2);
        label_nbFen = new JLabel("Nombre fenêtre : ");
        label_nbFen.setFont(font2);
        label_moteurs = new JLabel("Différents moteurs :");
        label_moteurs.setFont(font2);

        //paramètres de droite
        combo_mode.addItem("Ouvert");
        combo_mode.addItem("Fermé");

        combo_bits.addItem("2");
        combo_bits.addItem("3");


        //les boxs
        boxTete.add(labelImageAdmin);
        boxTete.add(Box.createHorizontalStrut(20));
        boxTete.add(label_param);
        boxTete.add(Box.createHorizontalGlue());
        boxTete.add(labelImageLogo);

        label_mode.setPreferredSize(new Dimension(150, 30));
        boxMode.add(label_mode);
        boxMode.add(Box.createHorizontalStrut(20));
        combo_mode.setPreferredSize(new Dimension(150, 20));
        combo_mode.setAlignmentX(Component.RIGHT_ALIGNMENT);
        boxMode.add(combo_mode);
        boxMode.add(Box.createHorizontalGlue());

        label_bits.setPreferredSize(new Dimension(150, 30));
        boxBits.add(label_bits);
        boxBits.add(Box.createHorizontalStrut(100));
        combo_bits.setPreferredSize(new Dimension(150, 20));
        boxBits.add(combo_bits);
        boxBits.add(Box.createHorizontalGlue());

        label_seuilSimCoul.setPreferredSize(new Dimension(250, 30));
        boxseuilSimCoul.add(label_seuilSimCoul);
        boxseuilSimCoul.add(Box.createRigidArea(new Dimension(2, 0)));
        boxseuilSimCoul.add(sliderSimColor);
        boxseuilSimCoul.add(Box.createRigidArea(new Dimension(10, 0)));
        boxseuilSimCoul.add(pourc_seuilCoul);

        boxMotsCleMin.add(label_motsCles);
        boxMotsCleMin.add(Box.createRigidArea(new Dimension(150, 0)));
        boxMotsCleMin.add(motCleMin);

        boxseuilSimMin.add(label_seuilSimMin);
        boxseuilSimMin.add(Box.createRigidArea(new Dimension(8, 0)));
        boxseuilSimMin.add(sliderSeuilMin);
        boxseuilSimMin.add(Box.createRigidArea(new Dimension(10, 0)));
        boxseuilSimMin.add(pourc_seuilMin);


        boxTailleFen.add(label_tailleFen);
        boxTailleFen.add(Box.createRigidArea(new Dimension(50, 0)));
        boxTailleFen.add(tailleFen);
        boxTailleFen.add(Box.createRigidArea(new Dimension(50, 0)));

        boxNbFen.add(label_nbFen);
        boxNbFen.add(Box.createRigidArea(new Dimension(79, 0)));
        boxNbFen.add(nbFen);
        boxNbFen.add(Box.createRigidArea(new Dimension(50, 0)));

        boxBouton.add(enregistrer);
        boxBouton.add(boutonRetour);

        JPanel moteursT = new JPanel();
        boxMoteurs.add(label_moteurs);
        boxMoteurs.add(Box.createRigidArea(new Dimension(100, 5)));
        Box boxMoteursT = Box.createVerticalBox();
        for(JCheckBox j : listeMoteurs){
            boxMoteursT.add(j);
        }
        moteursT.add(boxMoteursT);
        moteurs = new JScrollPane(moteursT);
        moteurs.setPreferredSize(new Dimension(50, 100));
        boxMoteurs.add(moteurs);
        boxMoteurs.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMoteurs.add(boutonRetour);
        boutonRetour.setAlignmentX(Component.RIGHT_ALIGNMENT);

        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanResultats panResultat = new PanResultats(new ControlRecherche(), new ControlResultat(), new ControlHistorique(), TypeRecherche.RECHERCHE_FICHIER);
                PanRecherche panRecherche = new PanRecherche(panResultat);
                panChoixProfil = new PanChoixProfil(panRecherche);
                FrameClient.tabbedPane.removeAll();
                panChoixProfil.initialisation();
                FrameClient.tabbedPane.addTab("Choix Profil", panChoixProfil);
            }
        });

        /*enregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = 0;
                if (sliderSimColor.isEnabled()){
                    value = 4;
                } else if (sliderSeuilMin.isEnabled()){
                    value = 3;
                } else if (combo_mode.isEnabled()){
                    value = 1;
                } else if (combo_bits.isEnabled()){
                    value = 2;
                } else if (motCleMin.isEnabled()){
                    value = 0;
                } else if(tailleFen.isEnabled()){
                    value = 5;
                } else if (nbFen.isEnabled()){
                    value = 6;
                }
            }

        });*/

        boxMiseEnPage.add(boxTete);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxMode);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxBits);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxseuilSimCoul);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxMotsCleMin);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxseuilSimMin);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxTailleFen);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxNbFen);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boxMoteurs);
        boxMiseEnPage.add(Box.createRigidArea(new Dimension(0, 20)));
        boxMiseEnPage.add(boutonRetour);
        this.add(boxMiseEnPage);

    }


}