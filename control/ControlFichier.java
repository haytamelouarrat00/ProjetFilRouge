package ProjetFilRouge.control;

import ProjetFilRouge.modele.Recherche;
import ProjetFilRouge.modele.TypeFichier;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class ControlFichier {

    //Fonctions qui retourne tous les fichiers dans un répertoire
    public static String[] getFichiersDansRepertoire(String directoryPath) {
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

    //Fonction qui retourne le chemin relatif du projet
    public static String getCheminRelative() {
        File relativeFile = new File("");
        return relativeFile.getAbsolutePath();
    }

    //fonction pour récuperer l'extension d'un fichier
    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    //Fonction qui ouvre un fichier
    public static void ouvrirFichier(int choix, Recherche recherche) {
        if (recherche.getResultats().size() != 0) {
            String path = ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(getFileExtension(recherche.getResultats().get(choix - 1).getCheminResultat())) + recherche.getResultats().get(choix - 1).getCheminResultat();
            try {
                Desktop.getDesktop().open(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun résultat à ouvrir");
        }
    }
    //fonction qui ouvre un fichier
    public static void ouvrirFichier(String path) {
        try {
            String chemin = ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(getFileExtension(path)) + path;
            Desktop.getDesktop().open(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFileAsString(String fileName) throws IOException, IOException {
        fileName = ControlFichier.getCheminRelative() + TypeFichier.getRepertoireResultatFromExtension(getFileExtension(fileName)) + fileName;
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }


}
