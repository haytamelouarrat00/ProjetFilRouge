package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.Moteurs;

import java.util.ArrayList;

public class ControlMoteurs {
    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public static ArrayList<Moteurs> randomMoteurs(){
        for(Moteurs moteur : Moteurs.getMoteurs()){
            System.out.println(moteur);
        }
    }
}
