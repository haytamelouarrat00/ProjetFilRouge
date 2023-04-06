package ProjetFilRouge.control;

import ProjetFilRouge.modele.Moteur;

public class ControlParametrage {
    private Moteur moteur;

    public ControlParametrage(Moteur moteur) {
        this.moteur = moteur;
    }

    public void setSeuilSimilarite(float seuilSimilarite){
        moteur.setSeuilSimMin(seuilSimilarite);
    }
    public void setMotCleMin(int motCleMin) {
        moteur.setMotsClesMin(motCleMin);
    }
    public void setBitsQuantification(int bitsQuantification) {
        moteur.setBitsQuantif(bitsQuantification);
    }
    public void setNbIntervalles(int nbIntervalles) {
        moteur.setNbFenetres(nbIntervalles);
    }
    public void setTailleFenAnalyse(int tailleFenAnalyse) {
        moteur.setTailleFenetre(tailleFenAnalyse);
    }
}
