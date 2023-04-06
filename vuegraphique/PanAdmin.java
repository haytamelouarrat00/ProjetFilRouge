package ProjetFilRouge.vuegraphique;


import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.modele.Moteur;
import ProjetFilRouge.modele.Parametres;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;


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


    public PanAdmin(ControlMoteurs controlMoteurs){
        this.controlMoteurs = controlMoteurs;
    }
    public void initialisation(){
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
        imageIconAdmin = new ImageIcon("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ressources\\fichier.png");
        JLabel labelImageAdmin = new JLabel(imageIconAdmin);
        labelImageAdmin.setBounds(0, 0, 100, 100);

        //image admin
        imageIconLogo = new ImageIcon("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ressources\\fichier.png");
        JLabel labelImageLogo = new JLabel(imageIconLogo);
        labelImageLogo.setBounds(0, 0, 100, 100);

        //bouton retour
        boutonRetour = new JButton("Retour");

        //labels
        label_param = new JLabel("Paramètres");
        label_mode = new JLabel("Mode :");
        label_bits = new JLabel("Bits quantification :");
        label_seuilSimCoul = new JLabel("Seuil similarité couleur :");
        label_motsCles = new JLabel("Mots clés :");
        label_seuilSimMin = new JLabel("Seuil similarité min :");
        label_tailleFen = new JLabel("Taille fenêtre :");
        label_nbFen = new JLabel("Nombre fenêtre : ");
        label_moteurs = new JLabel("Différents moteurs :");

        //paramètres de droite
        combo_mode.addItem("Ouvert");
        combo_mode.addItem("Fermé");

        combo_bits.addItem("2");
        combo_bits.addItem("3");


        //les boxs
        boxTete.add(labelImageAdmin);
        boxTete.add(Box.createVerticalStrut(50));
        boxTete.add(label_param);
        boxTete.add(Box.createVerticalStrut(50));
        boxTete.add(labelImageLogo);

        boxMode.add(label_mode);
        boxMode.add(combo_mode);

        boxBits.add(label_bits);
        boxBits.add(combo_bits);

        boxseuilSimCoul.add(label_seuilSimCoul);
        boxseuilSimCoul.add(sliderSimColor);
        boxseuilSimCoul.add(pourc_seuilCoul);

        boxMotsCleMin.add(label_motsCles);
        boxMotsCleMin.add(motCleMin);

        boxseuilSimMin.add(label_seuilSimMin);
        boxseuilSimMin.add(sliderSeuilMin);
        boxseuilSimMin.add(pourc_seuilMin);

        boxTailleFen.add(label_tailleFen);
        boxTailleFen.add(tailleFen);

        boxNbFen.add(label_nbFen);
        boxNbFen.add(nbFen);

        JPanel moteursT = new JPanel();
        boxMoteurs.add(label_moteurs);
        Box boxMoteursT = Box.createVerticalBox();
        for(JCheckBox j : listeMoteurs){
            boxMoteursT.add(j);
        }
        moteursT.add(boxMoteursT);
        moteurs = new JScrollPane(moteursT);
        moteurs.setPreferredSize(new Dimension(100, 50));
        boxMoteurs.add(moteurs);

        boxMiseEnPage.add(boxTete);
        boxMiseEnPage.add(boxMode);
        boxMiseEnPage.add(boxBits);
        boxMiseEnPage.add(boxseuilSimCoul);
        boxMiseEnPage.add(boxMotsCleMin);
        boxMiseEnPage.add(boxseuilSimMin);
        boxMiseEnPage.add(boxTailleFen);
        boxMiseEnPage.add(boxNbFen);
        boxMiseEnPage.add(boxMoteurs);
        boxMiseEnPage.add(boutonRetour);
        this.add(boxMiseEnPage.add(Box.createVerticalGlue()));

    }


}