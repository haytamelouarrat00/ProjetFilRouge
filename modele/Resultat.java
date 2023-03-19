package ProjetFilRouge.modele;

import java.util.ArrayList;

public class Resultat {
    //Attributs
    //Chemin du resultat
    private String cheminResultat;
    //Liste des moteurs de recherche utilisés pour générer le résultat
    private ArrayList<Moteur> moteurs;

    //Constructeur
    public Resultat(String cheminResultat) {
        this.cheminResultat = cheminResultat;
        moteurs = new ArrayList<>();
    }
    public String getCheminResultat() {
        return cheminResultat;
    }

    public void setMoteurs(ArrayList<Moteur> moteurs) {
        this.moteurs = moteurs;
    }

    public ArrayList<Moteur> getMoteurs() {
        return moteurs;
    }
}
