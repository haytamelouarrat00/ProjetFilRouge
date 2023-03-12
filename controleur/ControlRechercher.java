package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.*;
import modele.Type_Fichier;
import ProjetFilRouge.modele.Resultat;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ControlRechercher {
    ControlResultats controlResultats = new ControlResultats();

    //Fonctions qui filtre la requête de l'utilisateur
    public String filtrerRequete(String recherche) {
        String[] requetes = recherche.split(" ");
        return Arrays.stream(requetes).filter(requete1 -> requete1.charAt(0) != '+' && requete1.charAt(0) != '-').reduce("", (a, b) -> a + " " + b);
    }

    public String[] filtrerRequeteInclusion(String recherche) {
        String[] requetes = recherche.split(" ");
        String[] inclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '+').toArray(String[]::new);
        for (int i = 0; i < inclusion.length; i++) {
            inclusion[i] = inclusion[i].substring(1);
        }
        return inclusion;
    }

    public String[] filtrerRequeteExclusion(String recherche) {
        String[] requetes = recherche.split(" ");
        String[] exclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '-').toArray(String[]::new);
        for (int i = 0; i < exclusion.length; i++) {
            exclusion[i] = exclusion[i].substring(1);
        }
        return exclusion;
    }

    //Fonction qui vérifie si le fichier existe dans le dossier
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

    //Fonction qui retourne le type de fichier
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

    //Fonction qui vérifie si le fichier est valide
    public boolean verifierValiditeFichier(Type_Fichier type, String path) {
        if (fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\Textes_UTF8")) {//TODO: changer le chemin
            return Arrays.asList(type.getExtensions()).contains(getFileExtension(path));
        }
        return false;
    }

    //Fonction qui ouvre le fichier
    public void ouvrirFichier(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Fonction qui lance la recherche


    //function to pick a random number
    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
    //Fonction qui génere un résultat aléatoire

    public ArrayList<Resultat> Rechercher(Type_Fichier type, String recherche) {
        ArrayList<Resultat> resultats = new ArrayList<Resultat>();
        while (resultats.size() < this.random(0, this.controlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8").length)) {
            resultats.add(FabriqueResultat.creerResultat(recherche, ControlMoteurs.randomMoteurs(Moteurs.getMoteurs().size())));
        }
        return resultats;
    }

    public ArrayList<Resultat> Rechercher(String recherche) {
        RechercheMotCle rechercheMotCle = new RechercheMotCle(filtrerRequete(recherche), filtrerRequeteInclusion(recherche), filtrerRequeteExclusion(recherche));
        ArrayList<Resultat> resultats = new ArrayList<Resultat>();
        while (resultats.size() < this.random(0, this.controlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8").length)) {
            resultats.add(FabriqueResultat.creerResultat(rechercheMotCle.getRequete(), ControlMoteurs.randomMoteurs(Moteurs.getMoteurs().size())));
        }
        return resultats;
    }
    //edit
}
