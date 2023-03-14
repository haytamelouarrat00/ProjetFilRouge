package ProjetFilRouge.modele;

import ProjetFilRouge.controleur.ControlRechercher;
import ProjetFilRouge.controleur.ControlResultats;

import java.util.ArrayList;
import java.util.Objects;

public class FabriqueResultat {
    public static Resultat creerResultat(String path, ArrayList<Moteurs> moteurs) {
        Resultat resultat = new Resultat();
        resultat.setPath(ControlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8")[(int) (Math.random() * (ControlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8").length+ 1))]);
        resultat.setMoteurs(moteurs);
        return resultat;
    }
}
