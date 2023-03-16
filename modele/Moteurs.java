package ProjetFilRouge.modele;

import java.util.ArrayList;

public class Moteurs {
    String nom;
    private int motsClesMin;
    private float seuilSimMin;
    private int bitsQuantif;
    private int tailleFenetre;
    private int nbFenetres;
    //Liste totale de moteurs
    public static ArrayList<Moteurs> moteurs = new ArrayList<Moteurs>();
    //Liste des moteurs actifs
    public static ArrayList<Moteurs> moteursActifs = new ArrayList<Moteurs>();
    //Liste des moteurs inactifs
    public static ArrayList<Moteurs> moteursInactifs = new ArrayList<Moteurs>();

//GETTERS
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMotsClesMin() {
        return motsClesMin;
    }

    public void setMotsClesMin(int motsClesMin) {
        this.motsClesMin = motsClesMin;
    }

    public float getSeuilSimMin() {
        return seuilSimMin;
    }

    public void setSeuilSimMin(float seuilSimMin) {
        this.seuilSimMin = seuilSimMin;
    }

    public int getBitsQuantif() {
        return bitsQuantif;
    }

    public void setBitsQuantif(int bitsQuantif) {
        this.bitsQuantif = bitsQuantif;
    }

    public int getTailleFenetre() {
        return tailleFenetre;
    }

    public void setTailleFenetre(int tailleFenetre) {
        this.tailleFenetre = tailleFenetre;
    }

    public int getNbFenetres() {
        return nbFenetres;
    }

    public void setNbFenetres(int nbFenetres) {
        this.nbFenetres = nbFenetres;
    }

    public static ArrayList<Moteurs> getMoteurs() {
        return moteurs;
    }

    public static void setMoteurs(ArrayList<Moteurs> moteurs) {
        Moteurs.moteurs = moteurs;
    }

    public static ArrayList<Moteurs> getMoteursActifs() {
        return moteursActifs;
    }
//FIN GETTERS AND SETTERS
    //Fonction qui formatte l'affichage des moteurs
    public String toString() {
        return nom + " [TEXTE]Mots Clés Minimum: " + motsClesMin + " Seuil de Similiraité Minimum: " + seuilSimMin + " [IMAGE] Bits de quantification: " + bitsQuantif + " [AUDIO] Taille des Fenetres " + tailleFenetre + " Nombres de fenetres " + nbFenetres;
    }
}
