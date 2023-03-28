package ProjetFilRouge.modele;

public class RechercheFichier extends Recherche {
    //Attributs
    private String cheminRecherche;
    private TypeFichier typeFichier;

    //Constructeur
    public RechercheFichier(String cheminRecherche, TypeFichier typeFichier) {
        super();
        this.cheminRecherche = cheminRecherche;
        this.typeFichier = typeFichier;
    }
    //Getters et Setters
    public String getCheminRecherche() {
        return cheminRecherche;
    }

    public void setCheminRecherche(String cheminRecherche) {
        this.cheminRecherche = cheminRecherche;
    }

    public TypeFichier getTypeFichier() {
        return typeFichier;
    }

    public void setTypeFichier(TypeFichier typeFichier) {
        this.typeFichier = typeFichier;
    }

    //toString
    public String toString() {
        return this.getDateRecherche().toString() + " RechercheFichier[" + this.typeFichier + "][" +this.getMode()+ "][cheminRecherche=" + cheminRecherche + "]";
    }
}
