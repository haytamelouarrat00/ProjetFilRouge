package ProjetFilRouge.modele;

import java.util.ArrayList;

public class Historique {
    //liste des recherches effectuées
    private ArrayList<Recherche> listeRecherche;
    String cheminHistorique;

    //constructeur
    public Historique(){
        this.listeRecherche = new ArrayList<>();
    }

    //Getters et Setters
    public String getCheminHistorique() {
        return cheminHistorique;
    }

    public void setCheminHistorique(String cheminHistorique) {
        this.cheminHistorique = cheminHistorique;
    }

    public ArrayList<Recherche> getListeRecherche() {
        return listeRecherche;
    }
}
