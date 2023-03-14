package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.*;
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

    //Fonction qui filtre les mots à inclure +mot
    public String[] filtrerRequeteInclusion(String recherche) {
        String[] requetes = recherche.split(" ");
        String[] inclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '+').toArray(String[]::new);
        for (int i = 0; i < inclusion.length; i++) {
            inclusion[i] = inclusion[i].substring(1);
        }
        return inclusion;
    }

    //Fonction qui filtre les mots à exclure -mot
    public String[] filtrerRequeteExclusion(String recherche) {
        String[] requetes = recherche.split(" ");
        String[] exclusion = Arrays.stream(requetes).filter(requete -> requete.charAt(0) == '-').toArray(String[]::new);
        for (int i = 0; i < exclusion.length; i++) {
            exclusion[i] = exclusion[i].substring(1);
        }
        return exclusion;
    }

    //Fonction qui vérifie si le fichier fileName existe dans le dossier directoryPath
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

    //Fonction qui retourne le type de fichier filePath
    public static String getFileExtension(String filePath) {
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

    //function to pick a random number
    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //Fonction qui génere un résultat aléatoire pour une recherche fichier
    public void Rechercher(RechercheFichier recherche) {
        String dirPath = switch (Objects.requireNonNull(Type_Fichier.getTypeFromExtension(getFileExtension(recherche.getCheminRecherche())))) {
            case TEXTE -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8";
            case IMAGE ->
                    ((getFileExtension(recherche.getCheminRecherche()).equals("jpg")) || getFileExtension(recherche.getCheminRecherche()).equals("txt")) ? "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB" : "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB";
            case AUDIO -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON";
        };
        if (verifierValiditeFichier(Objects.requireNonNull(Type_Fichier.getTypeFromExtension(getFileExtension(recherche.getCheminRecherche()))), recherche.getCheminRecherche())) {
            System.out.println("Fichier à rechercher: " + recherche.getCheminRecherche());
            ArrayList<Resultat> resultats = new ArrayList<>();
            while (resultats.size() < this.random(0, Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath)).length)) {
                resultats.add(FabriqueResultat.creerResultat(recherche.getCheminRecherche(), ControlMoteurs.randomMoteurs(Moteurs.getMoteurs().size())));
            }
            recherche.setResultats(resultats);
        } else {
            System.out.println("Fichier invalide");
        }
    }

    //Fonction qui génere un résultat aléatoire pour une recherche mot clé
    public void Rechercher(RechercheMotCle rechercheMotCle) {
        System.out.println("Requête: " + rechercheMotCle.getRequete());
        System.out.println("Mots-Clés à inclure: " + Arrays.asList(rechercheMotCle.getExclusion()));
        System.out.println("Mots-Clés à exclure: " + Arrays.asList(rechercheMotCle.getInclusion()));
        System.out.println("Resultats: ");

        ArrayList<Resultat> resultats = new ArrayList<>();
        while (resultats.size() < this.random(0, Objects.requireNonNull(ControlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8")).length)) {
            resultats.add(FabriqueResultat.creerResultat(rechercheMotCle.getRequete(), ControlMoteurs.randomMoteurs((int) (Math.random() * (9 - 1 + 1) + 1))));
        }
        rechercheMotCle.setResultats(resultats);
    }

    //main TODO
    public static void main(String[] args) {
        ControlRechercher controlRechercher = new ControlRechercher();
        RechercheFichier recherche = FabriqueRecherche.creerRecherche("53.txt", ControlMoteurs.randomMoteurs(10));
        controlRechercher.Rechercher(recherche);
        for (Resultat resultat : recherche.getResultats()) {
            System.out.println(resultat);
        }
        ControlResultats.ouvrirFichier(controlRechercher.random(0, recherche.getResultats().size() - 1), recherche);
    }
}
