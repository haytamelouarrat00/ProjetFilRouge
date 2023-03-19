package controleur;

import modele.Moteurs;

public class ControlParametrage {
	private Moteurs moteur;
	
	public ControlParametrage(Moteurs moteur) {
		this.moteur = moteur;
	}
	
	public void setSeuilSimilarite(float seuilSimilarite){
		moteur.setSeuilSimilarite(seuilSimilarite);
	}
	public void setMotCleMin(int motCleMin) {
		moteur.setMotCleMin(motCleMin);
	}
	public void setBitsQuantification(int bitsQuantification) {
		moteur.setBitsQuantification(bitsQuantification);
	}
	public void setNbIntervalles(int nbIntervalles) {
		moteur.setNbIntervalles(nbIntervalles);
	}
	public void setTailleFenAnalyse(int tailleFenAnalyse) {
		moteur.setTailleFenAnalyse(tailleFenAnalyse);
	}
}
