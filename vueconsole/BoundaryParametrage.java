package vueconsole;

import java.util.Scanner;

import controleur.ControlGestionMoteurRecherche;
import controleur.ControlModificationMode;
import controleur.ControlParametrage;
import modele.Operation;

public class BoundaryParametrage {
	private BoundaryChoixOperation boundaryChoixOperation;
	private ControlModificationMode controlModificationMode;
	private ControlGestionMoteurRecherche controlGestionMoteurRecherche;
	private ControlParametrage controlParametrage;
	
	public void choixParametre(Operation operation) {
		operation = boundaryChoixOperation.choixOperation();
		switch (operation) {
		case MODIFICATION_MODE:
			System.out.println("Choisissez le mode de recherche : ");
			System.out.println("1. Mode Ouvert ");
			System.out.println("2. Mode Fermé ");
			int choix = Clavier.entrerClavierInt();
			controlModificationMode.choixMode(choix);
			break;
		case GESTION_MOTEUR_RECHERCHE:
			System.out.println("Sélectionner les moteurs à activer/désactiver : ");
			Scanner scanner = new Scanner(System.in);
		    System.out.println("Veuillez entrer les noms des moteurs à inclure (séparés par des virgules) :");
		    String inclusStr = scanner.nextLine();
		    System.out.println("Veuillez entrer les noms des moteurs à exclure (séparés par des virgules) :");
		    String excluStr = scanner.nextLine();
		    controlGestionMoteurRecherche.gererMoteurs(inclusStr, excluStr);
			break;
		case PARAMETRER_INDEXATION:
			System.out.println("1. Modifier  le seuil de similarité (fichier text)");
			System.out.println("2. Modifier le nombre de mots minimum (fichier texte) ");
			System.out.println("3. Modifier le nombre de bits de quantification (fichier image)");
			System.out.println("4. Modifier nombre d'échantillon (fichier son)");
			System.out.println("5. Modifier la taille de l'intervalle (fichier son)");
			System.out.println("6. Précédent");
			int c = Clavier.entrerClavierInt();
			switch (c) {
			case 1: 
				System.out.println("Veuillez entrer la nouvelle valeur: ");
				int nomAcc = Clavier.entrerClavierInt();
				controlParametrage.setSeuilSimilarite(nomAcc);
				break;
			case 2:
				System.out.println("Veuillez entrer la nouvelle valeur: ");
				int nomMots = Clavier.entrerClavierInt();
				controlParametrage.setMotCleMin(nomMots);
			case 3:
				System.out.println("Veuillez entrer la nouvelle valeur: ");
				int nomBits = Clavier.entrerClavierInt();
				controlParametrage.setBitsQuantification(nomBits);
				break;
			case 4:
				System.out.println("Veuillez entrer la nouvelle valeur: ");
				int nomEch = Clavier.entrerClavierInt();
				controlParametrage.setTailleFenAnalyse(nomEch);
				break;
			case 5:
				System.out.println("Veuillez entrer la nouvelle valeur: ");
				int taille = Clavier.entrerClavierInt();
				controlParametrage.setNbIntervalles(taille);
			case 6:
				//retourner au menu précédent
			}
			break;
		}
	}
}
