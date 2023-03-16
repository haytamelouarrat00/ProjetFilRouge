package controleur;

import modele.Profil;

public class ControlChoixProfil {
	public Profil choixProfil(int choix) {
		if (choix==1) {
			return Profil.UTILISATEUR;
		}
		else {
			return Profil.ADMIN;
		}
	}
}
