package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.Operation;

public class ControlChoixOperation {
	public Operation choixOperation(int choix) {
		Operation operation = Operation.PARAMETRER_INDEXATION;
		if (choix==1) {
			operation = Operation.MODIFICATION_MODE;
			return operation;
		}
		else if (choix==2) {
			operation = Operation.GESTION_MOTEUR_RECHERCHE;
			return operation;
		}
		else {
			return operation;
		}
	}
}
