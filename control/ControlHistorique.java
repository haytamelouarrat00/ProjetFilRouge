//Haytam El Ouarrat
package ProjetFilRouge.control;

import ProjetFilRouge.modele.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControlHistorique {
    //creation de l'historique
    public boolean creerFichierHistorique(String nom) {
        try {
            File file = new File(ControlFichier.getCheminRelative() + "\\src\\" + nom);
            if (file.createNewFile()) {
                System.out.println("Historique crée: " + file.getName());
                return true;
            }
        } catch (IOException e) {
            System.out.println("Une erreur a occuré.");
            e.printStackTrace();
        }
        return false;
    }

    //fonction pour ecrire dans le fichier
    public void ecrireFichier(String path, Recherche recherche) {
        try {
            // Crée un objet File représentant le fichier dans lequel écrire.
            File file = new File(path);

            //Crée un objet FileWriter pour écrire dans le fichier, avec le paramètre "append" défini sur true.
            FileWriter writer = new FileWriter(file, true);

            // Écrit des données de la recherche dans le fichier.
            writer.write(recherche.toString());
            writer.write("\n"); // Ajoute un séparateur de ligne après le texte.
            writer.write(System.lineSeparator()); // Ajoute un séparateur de ligne après le texte.

            //Ferme l'objet FileWriter pour libérer toutes les ressources qu'il utilise.
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //suppression du fichier
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    //fonction pour lire le contenu du fichier
    public Historique creerHistorique() {
        Historique historique = FabriqueHistorique.creerHistorique();
        if (creerFichierHistorique("historique.txt")) {
            historique.setCheminHistorique(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\historique.txt");
            return historique;
        } else {
            historique.setCheminHistorique(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\historique.txt");
            return historique;
        }
    }

    //ajout d'une recherche dans l'historique
    public void ajouterRecherche(Recherche recherche) {
        ecrireFichier(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\historique.txt", recherche);
    }

    //suppression de l'historique
    public void effacerHistorique() {
        deleteFile(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\historique.txt");
        creerFichierHistorique("historique.txt");
    }

    //suppression d'une ligne de l'historique
    public void removeLine(String lineContent) throws IOException {
        File file = new File(ControlFichier.getCheminRelative() + "\\src\\historique.txt");
        List<String> out = Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    //lecture du contenu de l'historique
    public String lirecontenuHistorique() {
        String contenu = "";
        File file = new File(ControlFichier.getCheminRelative() + "\\src\\historique.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line of the file
                contenu += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenu;
    }

    //effacer de l'historique d'une date à une autre
    public void effacerHistorique(Date dateD, Date dateF) {
        ControlDate controlDate = new ControlDate();
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            Date date = controlDate.dateDepuisString(recherche);
            if (Objects.nonNull(dateD) && Objects.nonNull(dateF)) {
                if (controlDate.isEntre(date, dateD, dateF)) {
                    try {
                        removeLine(recherche);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (Objects.isNull(dateD)) {
                if (controlDate.isAvant(date, dateF)) {
                    try {
                        removeLine(recherche);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (Objects.isNull(dateF)) {
                if (controlDate.isApres(date, dateD)) {
                    try {
                        removeLine(recherche);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    //effacer les recherches d'un mode
    public void effacerHistorique(Mode mode) {
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            if (recherche.contains(mode.toString())) {
                try {
                    removeLine(recherche);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //effacer les recherches d'un type de fichier
    public void effacerHistorique(TypeRecherche typeRecherche) {
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            if (recherche.contains(typeRecherche.toString())) {
                try {
                    removeLine(recherche);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //filtrer l'historique par le mode
    public ArrayList<String> filtrerHistorique(Mode mode) {
        ArrayList<String> fRecherches = new ArrayList<>();
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            if (recherche.contains(mode.toString())) {
                fRecherches.add(recherche);
            }
        }
        return fRecherches;
    }

    //filtrer l'historique par le type de fichier
    public ArrayList<String> filtrerHistorique(TypeFichier typeFichier) {
        ArrayList<String> fRecherches = new ArrayList<>();
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            if (recherche.contains(typeFichier.toString())) {
                fRecherches.add(recherche);
            }
        }
        return fRecherches;
    }
    //filtrer l'historique par le type de recherche
    public ArrayList<String> filtrerHistorique(TypeRecherche typeRecherche) {
        ArrayList<String> fRecherches = new ArrayList<>();
        String type = switch (typeRecherche) {
            case RECHERCHE_MOT_CLE -> "RechercheMotClé";
            case RECHERCHE_FICHIER -> "RechercheFichier";
            case RECHERCHE_IMAGE -> "RechercheImage";
            case RECHERCHE_AUDIO -> "RechercheAudio";
        };
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            if (recherche.contains(type)) {
                fRecherches.add(recherche);
            }
        }
        return fRecherches;
    }
    //filtrer l'historique par une date
    public void filtrerHistorique(Date dateD, Date dateF) {
        ControlDate controlDate = new ControlDate();
        String ctn = lirecontenuHistorique();
        String[] recherches = ctn.split("\\r?\\n");
        for (String recherche : recherches) {
            Date date = controlDate.dateDepuisString(recherche);
            if (controlDate.isEntre(date, dateD, dateF)) {
                System.out.println(recherche);
            }
        }
    }

}
