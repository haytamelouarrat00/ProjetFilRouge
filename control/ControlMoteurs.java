package ProjetFilRouge.control;

import ProjetFilRouge.modele.FabriqueMoteur;
import ProjetFilRouge.modele.Mode;
import ProjetFilRouge.modele.Moteur;
import ProjetFilRouge.modele.Parametres;


import java.util.ArrayList;

public class ControlMoteurs {
    //fonction qui retourne un nombre aléatoire entre min et max
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //fonction qui crée un moteur aléatoire
    public static Moteur creerMoteur() {
        return FabriqueMoteur.creerMoteur("Moteur " + random(1, Parametres.getNbMoteurs()),
                random(10, 30),
                (float) random(1, 300) / 10,
                random(2, 3),
                (float) random(1, 750) / 10,
                (int) Math.pow(2, random(9, 14)), random(1, 50) * 10);
    }

    //fonction qui crée une liste de moteurs aléatoire
    public void randomMoteurs(int nbMoteurs) {
        ArrayList<Moteur> moteurs = new ArrayList<Moteur>();
        for (int i = 0; i < nbMoteurs; i++) {
            moteurs.add(creerMoteur());
        }
        Moteur.setMoteurs(moteurs);
    }

    //Fonction pour activer un moteur
    public void setActifMoteur(Moteur moteur) {
        if (!Moteur.moteursActifs.contains(moteur)) {
            Moteur.moteursActifs.add(moteur);
        } else {
            System.out.println("Moteur déjà actif");
        }
    }

    //fonction pour activer un nombre aléatoire de moteurs
    public void randomActifs(int nbMoteurs) {
        ArrayList<Moteur> moteurs = new ArrayList<Moteur>();
        for (int i = 0; i < nbMoteurs; i++) {
            moteurs.add(Moteur.moteurs.get(random(0, Moteur.moteurs.size() - 1)));
        }
        Moteur.setMoteursActifs(moteurs);
    }

    //fonction pour désactiver un moteur
    public void setInactifMoteur(Moteur moteur) {
        if (Moteur.moteursActifs.contains(moteur)) {
            Moteur.moteursActifs.remove(moteur);
        } else {
            System.out.println("Moteur déjà inactif");
        }
    }

    //Fonction qui retourne un moteur par son nom
    public Moteur getMoteurbyNom(String nom) {
        for (Moteur moteur : Moteur.moteurs) {
            if (moteur.getNom().equals(nom)) {
                return moteur;
            }
        }
        return null;
    }

    public void modParam(int choix, Object o) {
        for (Moteur m : Moteur.getMoteurs()) {
            switch (choix) {
                case 1:
                    Parametres.setMode((Mode) o);
                case 2:
                    m.setBitsQuantif((int) o);
                case 3:
                    m.setSeuilSimMin((float) o);
                case 4:
                    m.setSeuilSimCouleur((float) o);
                case 5:
                    m.setTailleFenetre((int) o);
                case 6:
                    m.setNbFenetres((int) o);
                default:
                    m.setMotsClesMin((int) o);
            }


        }
    }
}
