package controleur;

import java.util.Scanner;
import modele.Mode;
import modele.Moteurs;

public class ControlModificationMode {
	private Moteurs moteur;
	private Scanner scanner;
	
	public ControlModificationMode(Moteurs moteur) {
		this.moteur = moteur;
		this.scanner = new Scanner(System.in);
	}

    public Mode choixMode() {
        System.out.println("Choisissez un mode de recherche :");
        System.out.println("1 - Mode fermé");
        System.out.println("2 - Mode ouvert");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                return Mode.FERME;
            case 2:
                return Mode.OUVERT;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                return choixMode();
        }
	}
}
