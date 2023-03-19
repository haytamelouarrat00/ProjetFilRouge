package vueconsole;

import controleur.ControlChoixProfil;
import modele.Profil;

public class BoundaryChoixProfil {
	private ControlChoixProfil controlChoixProfil;
	
	public BoundaryChoixProfil(ControlChoixProfil controlChoixProfil){
		this.controlChoixProfil = controlChoixProfil;
	}
	
	public Profil choixProfil() {
		System.out.println("Veuillez choisir votre profil :");
		System.out.println("1. Utilisateur");
		System.out.println("2. Administrateur");
		int choix = Clavier.entrerClavierInt();
		return controlChoixProfil.choixProfil(choix);
	}
}
