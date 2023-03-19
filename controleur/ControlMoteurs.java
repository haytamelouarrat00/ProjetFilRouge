package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.FabriqueMoteur;
import ProjetFilRouge.modele.Moteurs;

import java.util.ArrayList;

public class ControlMoteurs {
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public static void randomMoteurs(int nbMoteurs){
        ArrayList<Moteurs> moteurs = new ArrayList<Moteurs>();
        for(int i = 0; i < nbMoteurs; i++){
            moteurs.add(FabriqueMoteur.creerMoteur("moteur" + random(1, nbMoteurs),random(1,20),(float) random(1, 150)/10, random(2,3), (int)Math.pow(2, random(9, 14)), random(1, 100)));
        }
        Moteurs.setMoteurs(moteurs);
    }
}
