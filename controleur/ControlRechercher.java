package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.*;
import modele.Type_Fichier;
import ProjetFilRouge.modele.Resultat;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ControlRechercher {

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
        //TODO: changer le chemin
        switch (type) {
            case TEXTE -> {
                return fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8") && Arrays.asList(Type_Fichier.TEXTE.getExtensions()).contains(getFileExtension(path));
            }
            case IMAGE -> {
                return (fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB") || fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB")) && Arrays.asList(Type_Fichier.IMAGE.getExtensions()).contains(getFileExtension(path));
            }
            default -> {
                return fileExistsInDirectory(path, "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON") && Arrays.asList(Type_Fichier.AUDIO.getExtensions()).contains(getFileExtension(path));
            }
        }

    }

    //Fonction qui ouvre le fichier
    public void ouvrirFichier(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Fonction qui lance la recherche TODO


    //function to pick a random number
    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //Fonction qui génere un résultat aléatoire
    public ArrayList<Resultat> Rechercher(Type_Fichier type, String recherche) {
        if (verifierValiditeFichier(type, recherche)) {
            ArrayList<Resultat> resultats = new ArrayList<>();
            while (resultats.size() < this.random(0, Objects.requireNonNull(ControlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8")).length)) {
                resultats.add(FabriqueResultat.creerResultat(recherche, ControlMoteurs.randomMoteurs(Moteurs.getMoteurs().size())));
            }
            return resultats;
        } else {
            System.out.println("Fichier invalide");
        }
        return null;
    }

    public ArrayList<Resultat> Rechercher(String recherche, Mode mode) {
        RechercheMotCle rechercheMotCle = FabriqueRecherche.creerRecherche(filtrerRequete(recherche), filtrerRequeteInclusion(recherche), filtrerRequeteExclusion(recherche), mode);
        System.out.println("Requête: " + rechercheMotCle.getRequete());
        System.out.println("Mots-Clés à inclure: " + Arrays.asList(rechercheMotCle.getExclusion()));
        System.out.println("Mots-Clés à exclure: " + Arrays.asList(rechercheMotCle.getInclusion()));
        System.out.println("Resultats: ");
        ArrayList<Resultat> resultats = new ArrayList<>();
        while (resultats.size() < this.random(0, Objects.requireNonNull(ControlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8")).length)) {
            resultats.add(FabriqueResultat.creerResultat(rechercheMotCle.getRequete(), ControlMoteurs.randomMoteurs((int) (Math.random() * (9 - 1 + 1) + 1))));
        }
        return resultats;
    }

    //edit
    public static void main(String[] args) {
        ControlRechercher controlRechercher = new ControlRechercher();
        for (Resultat resultat : controlRechercher.Rechercher("Test +math -français +orange -rouge -bleu", Mode.OUVERT)){
            System.out.println(resultat);
        }
    }
}
