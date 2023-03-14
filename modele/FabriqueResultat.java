package ProjetFilRouge.modele;

import ProjetFilRouge.controleur.ControlRechercher;
import ProjetFilRouge.controleur.ControlResultats;

import java.util.ArrayList;
import java.util.Objects;

//Fonction fabrique qui crée un résultat aléatoire
public class FabriqueResultat {
    public static Resultat creerResultat(String path, ArrayList<Moteurs> moteurs) {
        String dirPath;
        if (ControlRechercher.getFileExtension(path).equals("xml")) {
            dirPath = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8\\";
        } else if ((ControlRechercher.getFileExtension(path).equals("jpg")
                || ControlRechercher.getFileExtension(path).equals("bmp")
                || ControlRechercher.getFileExtension(path).equals("txt"))) {
            if (ControlRechercher.fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB\\"))
                dirPath = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB\\";
            else if (ControlRechercher.fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB\\"))
                dirPath = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB\\";
            dirPath = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB\\";
        } else {
            dirPath = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON\\";
        }
        Resultat resultat = new Resultat();
        resultat.setPath(Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath))[(int) (Math.random() * (Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath)).length))]);
        resultat.setMoteurs(moteurs);
        return resultat;
    }
}
