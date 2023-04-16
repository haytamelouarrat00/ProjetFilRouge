//Amal Ferhani
package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlHistorique;
import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.control.ControlRecherche;
import ProjetFilRouge.control.ControlResultat;
import ProjetFilRouge.modele.Parametres;
import ProjetFilRouge.modele.TypeRecherche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PanSIdentifier extends JPanel {
    //Variables et components
    private ImageIcon imageIconAdmin;
    private ImageIcon imageIconLogo;
    private Box boxMiseEnPage = Box.createVerticalBox();
    private JLabel message;

    private JButton boutonValider;
    private JButton boutonRetour;

    private JPasswordField passwordField;

    private JLabel admin;


    private PanAdmin panAdmin;
    private ControlMoteurs controlMoteurs;

    // Box horizontale pour le bas de la page
    Box boxHorizontal = Box.createHorizontalBox();

    public PanSIdentifier(/*Control*/) {

    }

    public void initialisation() {
        this.setBackground(Color.decode("#3C493F"));
        message = new JLabel();
        //image Admin
        imageIconAdmin = new ImageIcon("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ressources\\admin.png");
        JLabel labelImageAdmin = new JLabel(imageIconAdmin);
        labelImageAdmin.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //mdp
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 20));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        controlMoteurs = new ControlMoteurs();
        panAdmin = new PanAdmin(controlMoteurs);

        //Boutons
        //bouton valider
        boutonValider = new JButton("Se connecter");
        boutonValider.setPreferredSize(new Dimension(300, 30));
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (loginSucc()) {
                        FrameClient.tabbedPane.removeTabAt(0);
                        panAdmin = new PanAdmin(controlMoteurs);
                        panAdmin.initialisation();
                        FrameClient.tabbedPane.addTab("Administrateur", panAdmin);
                    }
                } catch (Exception ex){
                    message.setText("Erreur : " + ex.getMessage());
                }
            }
        });
        boutonValider.setAlignmentX(Component.CENTER_ALIGNMENT);

        //bouton retour
        boutonRetour = new JButton("Retour");
        boutonRetour.setPreferredSize(new Dimension(100, 30));
        boutonRetour.setAlignmentX(Component.RIGHT_ALIGNMENT);
        boutonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanResultats panResultat = new PanResultats(new ControlRecherche(), new ControlResultat(), new ControlHistorique(), TypeRecherche.RECHERCHE_MOT_CLE);
                PanRecherche panRecherche = new PanRecherche(panResultat);
                PanChoixProfil panChoixProfil = new PanChoixProfil(panRecherche);
                FrameClient.tabbedPane.removeAll();
                panChoixProfil.initialisation();
                FrameClient.tabbedPane.addTab("Choix Profil", panChoixProfil);
            }
        });

        admin = new JLabel("Administrateur");
        admin.setFont(new Font("Arial", Font.BOLD, 30));
        admin.setForeground(Color.white);
        admin.setAlignmentX(Component.CENTER_ALIGNMENT);


        boxHorizontal.add(boutonRetour);
        boxHorizontal.add(Box.createHorizontalGlue());

        boxMiseEnPage.add(Box.createVerticalStrut(150));
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(Box.createHorizontalStrut(10));
        boxMiseEnPage.add(horizontalBox);
        boxMiseEnPage.add(labelImageAdmin);
        boxMiseEnPage.add(Box.createVerticalStrut(50));
        boxMiseEnPage.add(admin);
        boxMiseEnPage.add(Box.createVerticalStrut(20));
        boxMiseEnPage.add(passwordField);
        boxMiseEnPage.add(Box.createVerticalStrut(20));
        boxMiseEnPage.add(boutonValider);
        boxMiseEnPage.add(Box.createVerticalStrut(30));
        boxMiseEnPage.add(boxHorizontal);
        this.add(boxMiseEnPage);
    }

    public boolean loginSucc() throws Exception{
        String mdpEntre = passwordField.getText();
        if (mdpEntre.equals(Parametres.getPswd())){
            return true;
        }
        else {
            JOptionPane.showMessageDialog(this, "Mot de passe incorrect, Réessayez.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}