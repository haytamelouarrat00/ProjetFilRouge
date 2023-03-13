package ProjetFilRouge.modele;

import ProjetFilRouge.controleur.ControlRechercher;
import ProjetFilRouge.controleur.ControlResultats;

import java.util.ArrayList;

public class FabriqueResultat {
    public static Resultat creerResultat(String path, ArrayList<Moteurs> moteurs) {
        Resultat resultat = new Resultat();
        resultat.setPath(ControlResultats.getAllFilesInDirectory(path))[Math.random() * (ControlResultats.getAllFilesInDirectory(path).length() - min + 1) + min];
        resultat.setMoteurs(moteurs);
        return resultat;
    }
}
