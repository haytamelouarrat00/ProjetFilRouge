package controleur;

import java.util.Scanner;
import modele.Mode;
import modele.Moteurs;
import vueconsole.Clavier;

public class ControlModificationMode {
	private Moteurs moteur;
	private Scanner scanner;
	
	public ControlModificationMode(Moteurs moteur) {
		this.moteur = moteur;
		this.scanner = new Scanner(System.in);
	}

    public Mode choixMode(int choix) {
        switch (choix) {
            case 1:
                return Mode.FERME;
            case 2:
                return Mode.OUVERT;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                choix = Clavier.entrerClavierInt();
                return choixMode(choix);
        }
	}
}
