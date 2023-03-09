package controleur;

import modele.Type_Fichier;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class ControlRechercher {
    public void filtrerRequete(String recherche) {
        String[] requetes = recherche.split(" ");
        String[] inclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '+').toArray(String[]::new);
        String[] exclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '-').toArray(String[]::new);
        for (int i = 0; i < inclusion.length; i++) {
            inclusion[i] = inclusion[i].substring(1);
        }
        for (int i = 0; i < exclusion.length; i++) {
            exclusion[i] = exclusion[i].substring(1);
        }
        requetes = Arrays.stream(requetes).filter(requete -> requete.charAt(0) != '+' && requete.charAt(0) != '-').toArray(String[]::new);
        System.out.println("Requetes: " + Arrays.toString(requetes));
        System.out.println("Inclusion: " + Arrays.toString(inclusion));
        System.out.println("Exclusion: " + Arrays.toString(exclusion));
    }

    public static boolean fileExistsInDirectory(String fileName, String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getFileExtension(String filePath) {
        Path path = Path.of(filePath);
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            // File has no extension
            return "";
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }

    public boolean verifierValiditéFichier(Type_Fichier type, String path) {
        if (fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\Textes_UTF8")) {//TODO: changer le chemin
            return Arrays.asList(type.getExtensions()).contains(getFileExtension(path));
        }
        return false;
    }
    public void ouvrirFichier(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lancerRecherche(int choix) {
        switch (choix) {
            case 1 -> filtrerRequete("test +test -test");
            case 2 -> verifierValiditéFichier(Type_Fichier.TEXTE, "12-Musiques_du_monde___les_utf8.xml");
        }

    }

    public static void main(String[] args) {
        ControlRechercher controlRechercher = new ControlRechercher();
        controlRechercher.verifierValiditéFichier(Type_Fichier.TEXTE, "12-Musiques_du_monde___les_utf8.xml");
        controlRechercher.ouvrirFichier("C:\\Users\\eohay\\Documents\\PFR\\src\\Textes_UTF8\\12-Musiques_du_monde___les_utf8.xml");
    }
    //edit
}
