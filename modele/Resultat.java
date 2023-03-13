package ProjetFilRouge.modele;

import java.util.ArrayList;

public class Resultat {
    private String path;
    private ArrayList<Moteurs> moteurs = new ArrayList<Moteurs>();

    public String getPath() {
        return path;
    }
    public ArrayList<Moteurs> getMoteurs() {
        return moteurs;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMoteurs(ArrayList<Moteurs> moteurs) {
        this.moteurs = moteurs;
    }

    public String toString() {
        return path;
    }

}
