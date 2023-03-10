package ProjetFilRouge.modele;

public class FabriqueMoteur {
    public static Moteurs creerMoteur(String nom, int motsClesMin, float seuilSimMin, int bitsQuantif, int tailleFenetre, int nbFenetres) {
        Moteurs moteur = new Moteurs();
        moteur.setNom(nom);
        moteur.setMotsClesMin(motsClesMin);
        moteur.setSeuilSimMin(seuilSimMin);
        moteur.setBitsQuantif(bitsQuantif);
        moteur.setTailleFenetre(tailleFenetre);
        moteur.setNbFenetres(nbFenetres);
        return moteur;
    }
}
