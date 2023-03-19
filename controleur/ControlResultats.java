package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.Recherche;
import ProjetFilRouge.modele.RechercheFichier;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ControlResultats {
    //retourne tous les fichiers dispo dans le dossier directoryPath
    public static String[] getAllFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                fileNames[i] = files[i].getName();
            }
            return fileNames;
        }
        return null;
    }

    //ouvre le fichier choisi
    public static void ouvrirFichier(int choix, Recherche recherche) {
        if (recherche.getResultats().size() != 0) {
            String path = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8\\" + recherche.getResultats().get(choix).getPath();
            if (recherche instanceof RechercheFichier) {
                switch (((RechercheFichier) recherche).getTypeFichier()) {
                    case TEXTE -> {
                        path = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8\\" + recherche.getResultats().get(choix).getPath();
                    }
                    case IMAGE -> {
                        path = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB\\" + recherche.getResultats().get(choix).getPath();
                    }
                    default -> {
                        path = "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON\\" + recherche.getResultats().get(choix).getPath();
                    }
                }
            }
            try {
                Desktop.getDesktop().open(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun résultat à ouvrir");
        }
    }
}
