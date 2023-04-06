package ProjetFilRouge.vuegraphique;

import javax.swing.*;
import java.util.Objects;

public class PanSIdentifier extends JPanel {
    //Variables et components
    ImageIcon imageIconAdmin;
    private ImageIcon imageIconLogo;
    private Box boxMiseEnPage = Box.createVerticalBox();
    private JLabel message;

    private JButton boutonValider;
    private JButton boutonRetour;

    private JPasswordField passwordField;

    private JLabel admin;

    public PanSIdentifier(/*Control*/) {

    }

    public void initialisation() {
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

        //Boutons
            //bouton valider
        boutonValider = new JButton("Se connecter");

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
}