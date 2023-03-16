package vueconsole;

import java.util.Scanner;
import controleur.ControlGestionMoteurRecherche;

public class BoundaryGestionMoteursRecherche {
	private ControlGestionMoteurRecherche controlGestionMoteurRecherche;
	
	public static void gererMoteurs() {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Veuillez entrer les noms des moteurs à inclure :");
	    String inclusStr = scanner.nextLine();
	    System.out.println("Veuillez entrer les noms des moteurs à exclure :");
	    String excluStr = scanner.nextLine();
	    controlGestionMoteurRecherche.gererMoteurs();
	}
}
