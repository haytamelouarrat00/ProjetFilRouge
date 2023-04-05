//Haytam El Ouarrat
package ProjetFilRouge.control;

import ProjetFilRouge.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ControlRecherche {
    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    //Recherche par Mot clé
    public Recherche rechercherMotCle(String rechercheUTILISATEUR) {
        ControlResultat controlResultat = new ControlResultat();
        String[] motsCles = rechercheUTILISATEUR.split(" ");
        //récuperation de la requete principale
        String requete = Arrays.stream(motsCles).filter(requete1 -> requete1.charAt(0) != '+' && requete1.charAt(0) != '-').reduce("", (a, b) -> a + " " + b);
        //création de la recherche
        Recherche recherche = FabriqueRecherche.creerRechercheMC(rechercheUTILISATEUR);
        //répartition des mots clés complexes
        HashMap<String, Character> motsClesComplexes = new HashMap<>();
        for (String motCle : motsCles) {
            if (motCle.charAt(0) == '+') {
                motsClesComplexes.put(motCle.substring(1), '+');
            } else if (motCle.charAt(0) == '-') {
                motsClesComplexes.put(motCle.substring(1), '-');
            }
        }
        if (recherche instanceof RechercheMotCle) ((RechercheMotCle) recherche).setMotsClesComplexes(motsClesComplexes);
        //création des résultats aléatoires
        ArrayList<Resultat> res = new ArrayList<>();
        for (int i = 0;
             i < random(1, Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\Textes_UTF8\\")).length);
             i++) {
            res.add(controlResultat.creerResultat("\\src\\ProjetFilRouge\\Textes_UTF8\\"));
        }

        res = controlResultat.removeDuplicates(res);
        //ajout des résultats à la recherche
        recherche.setResultats(res);
        return recherche;
    }

    //Recherche par Fichier
    public Recherche rechercherFichier(String cheminFichier) {
        //Détection automatique du type de fichier
        TypeFichier type = TypeFichier.getTypeFromExtension(ControlFichier.getFileExtension(cheminFichier));
        String dirPath;
        if (type != null) {
            dirPath = switch (type) {
                case TEXTE -> "\\src\\ProjetFilRouge\\Textes_UTF8\\";
                case IMAGE ->
                        (ControlFichier.getFileExtension(cheminFichier).equals("bmp") ? "\\src\\ProjetFilRouge\\TEST_NB" : "\\src\\ProjetFilRouge\\TEST_RGB");
                case AUDIO -> "\\src\\ProjetFilRouge\\TEST_SON";
            };
        } else {
            //TODO: gestion des erreurs
            throw new IllegalStateException("Unexpected extension: " + ControlFichier.getFileExtension(cheminFichier));
        }

        ControlResultat controlResultat = new ControlResultat();
        //Création de la recherche
        Recherche recherche = FabriqueRecherche.creerRechercheFichier(cheminFichier, type);
        //Création des résultats aléatoires de taille (1, nombre de fichiers dans le dossier)
        ArrayList<Resultat> res = new ArrayList<>();
        for (int i = 0;
             i < random(1, Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + dirPath)).length);
             i++) {
            res.add(controlResultat.creerResultat(dirPath));
        }
        res = controlResultat.removeDuplicates(res);//Ajout des résultats à la recherche
        recherche.setResultats(res);
        return recherche;
    }

    //Recherche par Image
    public Recherche rechercherImage(Couleurs couleur) {
        ControlResultat controlResultat = new ControlResultat();
        Recherche recherche = FabriqueRecherche.creerRechercheImage(couleur);
        ArrayList<Resultat> res = new ArrayList<>();
        for (int i = 0;
             i < random(1, Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\TEST_RGB")).length);
             i++) {
            res.add(controlResultat.creerResultat("\\src\\ProjetFilRouge\\TEST_RGB\\"));
        }
        res = controlResultat.removeDuplicates(res);
        recherche.setResultats(res);
        return recherche;
    }

    //Recherche par Son
    public Recherche rechercherSon(String cheminExtrait) {
        ControlResultat controlResultat = new ControlResultat();
        Recherche recherche = FabriqueRecherche.creerRechercheAudio(cheminExtrait);
        ArrayList<Resultat> res = new ArrayList<>();
        for (int i = 0;
             i < random(1, Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\TEST_SON")).length);
             i++) {
            res.add(controlResultat.creerResultat("\\src\\ProjetFilRouge\\TEST_SON\\"));
        }
        res = controlResultat.removeDuplicates(res);
        recherche.setResultats(res);
        return recherche;
    }


}
