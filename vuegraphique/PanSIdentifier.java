package ProjetFilRouge.vuegraphique;

import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.modele.Parametres;

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

    public PanSIdentifier(/*Control*/) {

    }

    public void initialisation() {
        this.setBackground(Color.decode("#3C493F"));
        //image logo
        imageIconAdmin = new ImageIcon("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ressources\\fichier.png");
        JLabel labelImageAdmin = new JLabel(imageIconAdmin);
        labelImageAdmin.setBounds(0, 0, 100, 100);

        //image admin
        imageIconLogo = new ImageIcon("C:\\Users\\eohay\\Documents\\PFRG7\\src\\ressources\\fichier.png");
        JLabel labelImageLogo = new JLabel(imageIconLogo);
        labelImageLogo.setBounds(0, 0, 100, 100);

        //mdp
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 100, 30);

        controlMoteurs = new ControlMoteurs();
        panAdmin = new PanAdmin(controlMoteurs);

        //Boutons
            //bouton valider
        boutonValider = new JButton("Se connecter");
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (Exception ex){
                    message.setText("Erreur : " + ex.getMessage());
                }
            }
        });

        //bouton retour
        boutonRetour = new JButton("Retour");

        admin = new JLabel("Administrateur");

        boxMiseEnPage.add(labelImageAdmin);
        boxMiseEnPage.add(labelImageLogo);
        boxMiseEnPage.add(admin);
        boxMiseEnPage.add(passwordField);
        boxMiseEnPage.add(boutonValider);
        boxMiseEnPage.add(boutonRetour);
        this.add(boxMiseEnPage);
    }

    public void login() throws Exception{
        String mdpEntre = passwordField.getText();
        if (mdpEntre.equals(Parametres.getPswd())){
            panAdmin.setVisible(true);
        }
        else {
            message.setText("Mot de passe incorrect !");
        }
    }
}