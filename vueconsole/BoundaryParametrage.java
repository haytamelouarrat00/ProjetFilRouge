package vueconsole;

import controleur.ControlParametrage;
import modele.Operation;

public class BoundaryParametrage {
	private BoundaryChoixOperation boundaryChoixOperation;
	private ControlParametrage controlParametrage;
	
	public void choixParametre(Operation operation) {
		operation = boundaryChoixOperation.choixOperation();
		switch (operation) {
		case MODIFICATION_MODE:
			System.out.println("Choisissez le mode de recherche : ");
			
			break;
		case GESTION_MOTEUR_RECHERCHE:
			System.out.println("Sélectionner les moteurs à activer/désactiver : ");
			break;
		case PARAMETRER_INDEXATION:
			System.out.println("Choisissez le moteur à régler : ");
			System.out.println("Réglez vos paramètres");
			break;
		}
	}
}
