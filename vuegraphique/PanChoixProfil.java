package ProjetFilRouge.vuegraphique;
import ProjetFilRouge.modele.Profil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PanChoixProfil extends JPanel {

    private JButton userButton;
    private JButton adminButton;
    private JLabel titleLabel;
    private Profil profil;
    private PanRecherche panRecherche;

    public PanChoixProfil(PanRecherche panRecherche) {
        this.panRecherche = panRecherche;
    }

    public void initialisation() {
        setLayout(new BorderLayout());

        // Création des boutons d'accès utilisateur et administrateur
        userButton = new JButton("Accès utilisateur");
        userButton.setContentAreaFilled(false);
        userButton.setBorderPainted(false);
        URL userIconUrl = getClass().getResource("user_icon.png");
        ImageIcon userIcon = new ImageIcon("C:\\Users\\SM\\Downloads\\user_icon.png");
        Image userImg = userIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        userButton.setIcon(new ImageIcon(userImg));

        adminButton = new JButton("Accès administrateur");
        adminButton.setContentAreaFilled(false);
        adminButton.setBorderPainted(false);
        URL adminIconUrl = getClass().getResource("admin_icon.png");
        ImageIcon adminIcon = new ImageIcon("C:\\Users\\SM\\Downloads\\admin_icon.png");
        Image adminImg = adminIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        adminButton.setIcon(new ImageIcon(adminImg));

        // Création du titre
        titleLabel = new JLabel("Bienvenue dans ALPHA DOCS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Ajout des éléments à la fenêtre
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.decode("#7E8D85"));
        leftPanel.add(adminButton, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.decode("#B3BFB8"));
        rightPanel.add(userButton, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(leftPanel);
        buttonPanel.add(rightPanel);

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameClient.profil = Profil.UTILISATEUR;
                FrameClient.tabbedPane.removeTabAt(0);
                panRecherche.initialisation();
                FrameClient.tabbedPane.addTab("Recherche", panRecherche);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profil = Profil.ADMINISTRATEUR;
            }
        });
    }

    public Profil getProfil() {
        return this.profil;
    }

    public JButton getUserButton() {
        return userButton;
    }
}
