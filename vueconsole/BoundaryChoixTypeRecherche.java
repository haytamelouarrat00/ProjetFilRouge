package ProjetFilRouge.vueconsole;

public class BoundaryChoixTypeRecherche {

    public void afficherChoixTypeRecherche() {
        System.out.println("1 - Recherche par mot clé");
        System.out.println("2 - Recherche par fichier");
        System.out.println("Choisissez le type de recherche :");
        int choix = Clavier.entrerClavierInt();
    }
}
