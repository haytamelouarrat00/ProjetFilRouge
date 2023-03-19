package ProjetFilRouge.modele;

import java.util.ArrayList;

public abstract class Recherche {
    private Date date;
    private Mode mode;
    private ArrayList<Resultat> resultats;

    public ArrayList<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(ArrayList<Resultat> resultats) {
        this.resultats = resultats;
    }
}
