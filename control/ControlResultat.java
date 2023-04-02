//Haytam El Ouarrat
package ProjetFilRouge.control;

import ProjetFilRouge.modele.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlResultat {
    public int random(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("min > max");
        if (min == max)
            return min;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public String[] getCheminsResultats(Recherche recherche) {
        String[] chemins = new String[recherche.getResultats().size()];
        for (int i = 0; i < recherche.getResultats().size(); i++) {
            chemins[i] = recherche.getResultats().get(i).getCheminResultat();
        }
        return chemins;
    }

    //creation d'un résultat aléatoire
    public Resultat creerResultat(String dirPath) {
        //TODO: Avoid repeating moteurs with the same name

        List<String> filteredFiles = new ArrayList<>();
        if (Objects.equals(dirPath, "\\src\\ProjetFilRouge\\TEST_RGB")) {
            for (String str : Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\TEST_RGB\\"))) {
                if (ControlFichier.getFileExtension(str).equals("jpg")) {
                    filteredFiles.add(str);
                }
            }
        } else if (Objects.equals(dirPath, "\\src\\ProjetFilRouge\\TEST_NB")) {
            for (String str : Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\TEST_NB\\"))) {
                if (ControlFichier.getFileExtension(str).equals("bmp")) {
                    filteredFiles.add(str);
                }
            }
        } else if (Objects.equals(dirPath, "\\src\\ProjetFilRouge\\TEST_SON")){
            for (String str : Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\TEST_SON\\"))) {
                if (ControlFichier.getFileExtension(str).equals("wav")) {
                    filteredFiles.add(str);
                }
            }

        }else {
            System.out.println("No files found");
        }
        Resultat res = FabriqueResultat.creerResultat(Objects.requireNonNull(filteredFiles.get(random(0, filteredFiles.size() - 1))));
        ArrayList<Moteur> moteurs = new ArrayList<>();
        for (int i = 0; i < Parametres.getNbMoteurs(); i++) {
            if (Moteur.moteursActifs.size() != 0) {
                moteurs.add(Moteur.moteursActifs.get(random(0, Moteur.moteursActifs.size() - 1)));
            } else {
                throw new IllegalStateException("No moteurs actifs");
            }
        }
        res.setMoteurs(moteurs);
        return res;
    }

    public Moteur getHighestValueMoteur(Resultat res, Recherche recherche) {
        Moteur moteur = res.getMoteurs().get(0);
        if (recherche instanceof RechercheMotCle) {
            for (Moteur m : res.getMoteurs()) {
                if (m.getMotsClesMin() > moteur.getMotsClesMin())
                    moteur = m;
            }
        } else if (recherche instanceof RechercheFichier || recherche instanceof RechercheAudio) {
            for (Moteur m : res.getMoteurs()) {
                if (m.getSeuilSimMin() > moteur.getSeuilSimMin())
                    moteur = m;
            }
        } else if (recherche instanceof RechercheImage) {
            for (Moteur m : res.getMoteurs()) {
                if (m.getSeuilSimCouleur() > moteur.getSeuilSimCouleur())
                    moteur = m;
            }
        }
        return moteur;

    }


}
