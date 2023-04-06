package ProjetFilRouge.modele;

import java.util.ArrayList;
import java.util.HashSet;

//Moteurs de recherche
public class Moteur {
    //Parametres du moteur/indexation
    String nom;
    private int motsClesMin;
    private float seuilSimMin;
    private int bitsQuantif;
    private float seuilSimCouleur;
    private int tailleFenetre;
    private int nbFenetres;

    //Liste totale de moteurs
    public static ArrayList<Moteur> moteurs = new ArrayList<>();
    //Liste des moteurs actifs
    public static ArrayList<Moteur> moteursActifs = new ArrayList<Moteur>();

    //Constructeur
    public Moteur(String nom, int motsClesMin, float seuilSimMin, int bitsQuantif, float seuilSimCouleur,int tailleFenetre, int nbFenetres) {
        this.nom = nom;
        this.motsClesMin = motsClesMin;
        this.seuilSimMin = seuilSimMin;
        this.bitsQuantif = bitsQuantif;
        this.seuilSimCouleur = seuilSimCouleur;
        this.tailleFenetre = tailleFenetre;
        this.nbFenetres = nbFenetres;
    }

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

    public float getSeuilSimCouleur() {
        return seuilSimCouleur;
    }

    public void setSeuilSimCouleur(float seuilSimCouleur) {
        this.seuilSimCouleur = seuilSimCouleur;
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

    public static ArrayList<Moteur> getMoteur() {
        return moteurs;
    }

    public static void setMoteur(ArrayList<Moteur> moteurs) {
        Moteur.moteurs = moteurs;
    }

    public static ArrayList<Moteur> getMoteurActifs() {
        return moteursActifs;
    }

    public static void setMoteursActifs(ArrayList<Moteur> moteursActifs) {
        Moteur.moteursActifs = moteursActifs;
    }

    public static ArrayList<Moteur> getMoteurs() {
        return moteurs;
    }

    public static void setMoteurs(ArrayList<Moteur> moteurs) {
        Moteur.moteurs = moteurs;
    }

    public static ArrayList<Moteur> getMoteursActifs() {
        return moteursActifs;
    }

    //FIN GETTERS AND SETTERS

    //Fonction qui formatte l'affichage des moteurs
    public String toString() {
        return nom;
    }
}
