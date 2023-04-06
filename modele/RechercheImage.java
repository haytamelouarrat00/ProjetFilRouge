package ProjetFilRouge.modele;

import java.awt.*;

public class RechercheImage extends Recherche {
    //attributs
    private Color couleurDominante;
    //constructeur
    public RechercheImage(Color couleurDominante) {
        super(TypeFichier.IMAGE);
        this.couleurDominante = couleurDominante;
    }
    //getters et setters
    public Color getCouleurDominante() {
        return couleurDominante;
    }

    public void setCouleurDominante(Color couleurDominante) {
        this.couleurDominante = couleurDominante;
    }
    //toString
    public String toString() {
        return this.getDateRecherche().toString() + " RechercheImage ["+this.getMode()+"][Couleur dominante =" + couleurDominante + "]";
    }

}
