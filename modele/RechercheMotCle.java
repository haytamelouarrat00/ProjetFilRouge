package ProjetFilRouge.modele;

import java.util.HashMap;

public class RechercheMotCle extends Recherche {
    //Attributs
    private String motCle;
    //TODO: add HASHMAP
    private HashMap<String, Character> motsClesComplexes;


    public RechercheMotCle(String motCle) {
        super(TypeFichier.TEXTE);
        this.motCle = motCle;
        this.motsClesComplexes = new HashMap<String, Character>();
    }
    public void setMotsClesComplexes(HashMap<String, Character> motsClesComplexes) {
        this.motsClesComplexes = motsClesComplexes;
    }
    public String toString() {
        return this.getDateRecherche().toString() + " RechercheMotClé ["+this.getMode()+"][Mot Clé=" + motCle +"]";
    }
}
