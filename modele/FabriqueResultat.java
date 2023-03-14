package ProjetFilRouge.modele;

import ProjetFilRouge.controleur.ControlRechercher;
import ProjetFilRouge.controleur.ControlResultats;

import java.util.ArrayList;
import java.util.Objects;

public class FabriqueResultat {
    public static Resultat creerResultat(String path, ArrayList<Moteurs> moteurs) {
        String dirPath = switch (Objects.requireNonNull(modele.Type_Fichier.getTypeFromExtension(ControlRechercher.getFileExtension(path)))) {
            case TEXTE -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8";
            case IMAGE -> ((ControlRechercher.getFileExtension(path).equals("jpg") )|| ControlRechercher.getFileExtension(path).equals("txt")) ? "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB" : "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB";
            case AUDIO -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON";
        };
        Resultat resultat = new Resultat();
        resultat.setPath(Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath))[(int) (Math.random() * (Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath)).length))]);
        resultat.setMoteurs(moteurs);
        return resultat;
    }
}
