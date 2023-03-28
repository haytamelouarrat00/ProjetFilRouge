package ProjetFilRouge.modele;

import ProjetFilRouge.modele.Date;

import java.util.ArrayList;

public abstract class Recherche{

    // Attributs
    private Date dateRecherche; // La date de la recherche
    private Mode mode; // Le mode de recherche


    private ArrayList<Resultat> resultats; // Les résultats de la recherche

    // Constructeur
    public Recherche() {
        // Initialise le mode avec les paramètres de l'application
        this.mode = Parametres.getMode();
        // Initialise la date de recherche avec la date actuelle
        this.dateRecherche = FabriqueDate.creerDate();
        // Initialise la liste des résultats avec une nouvelle liste vide
        resultats = new ArrayList<Resultat>();
    }

    // Méthodes

    // Récupère la liste des résultats de la recherche
    public ArrayList<Resultat> getResultats() {
        return resultats;
    }

    // Modifie la liste des résultats de la recherche
    public void setResultats(ArrayList<Resultat> resultats) {
        this.resultats = resultats;
    }

    // Récupère la date de recherche
    public Date getDateRecherche() {
        return dateRecherche;
    }

    // Modifie la date de recherche
    public void setDateRecherche(Date dateRecherche) {
        this.dateRecherche = dateRecherche;
    }

    // Récupère le mode de recherche
    public Mode getMode() {
        return mode;
    }

    // Modifie le mode de recherche
    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
