package ProjetFilRouge.modele;

public class RechercheImage extends Recherche {
    //attributs
    private Couleurs couleurDominante;
    //constructeur
    public RechercheImage(Couleurs couleurDominante) {
        super();
        this.couleurDominante = couleurDominante;
    }
    //getters et setters
    public Couleurs getCouleurDominante() {
        return couleurDominante;
    }

    public void setCouleurDominante(Couleurs couleurDominante) {
        this.couleurDominante = couleurDominante;
    }
    //toString
    public String toString() {
        return this.getDateRecherche().toString() + " RechercheImage ["+this.getMode()+"][Couleur dominante =" + couleurDominante + "]";
    }

}
