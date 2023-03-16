package vueconsole;

import controleur.ControlChoixOperation;
import modele.Operation;

public class BoundaryChoixOperation {
	private ControlChoixOperation controlChoixOperation;
	
	public Operation choixOperation() {
		System.out.println("Veuillez choisir votre opération :");
		System.out.println("1. Modifier le mode");
		System.out.println("2. Gestion des moteurs de recherche");
		System.out.println("3. Paramétrer l'indexation");
		System.out.println("Veuillez entrer 1, 2 ou 3:");
		int choix = Clavier.entrerClavierInt();
		return controlChoixOperation.choixOperation(choix);
	}
}
