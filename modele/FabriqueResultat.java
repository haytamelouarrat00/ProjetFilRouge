package ProjetFilRouge.modele;

import java.util.ArrayList;

public class FabriqueResultat {

    public static Resultat creerResultat(String path, ArrayList<Moteurs> moteurs) {
        Resultat resultat = new Resultat();
        resultat.setPath(path);
        resultat.setMoteurs(moteurs);
        return resultat;
    }
}
