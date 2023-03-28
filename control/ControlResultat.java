//Haytam El Ouarrat
package ProjetFilRouge.control;

import ProjetFilRouge.modele.*;

import java.util.ArrayList;
import java.util.Objects;

public class ControlResultat {
    public int random(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("min > max");
        if (min == max)
            return min;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //creation d'un résultat aléatoire
    public Resultat creerResultat(String dirPath) {
        //TODO: Avoid repeating moteurs with the same name
        //TODO Fix .txt extension erreur
        Resultat res = FabriqueResultat.creerResultat(Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + dirPath))[random(0, Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + dirPath)).length - 1)]);
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
