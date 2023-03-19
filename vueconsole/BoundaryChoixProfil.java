package ProjetFilRouge.vueconsole;

import ProjetFilRouge.modele.Profil;

public class BoundaryChoixProfil {

    public Profil choixProfil() {
        System.out.println("Choisissez votre profil :");
        System.out.println("1. Administrateur");
        System.out.println("2. Utilisateur");
        System.out.println("3. Quitter");
        int choixProfil = Clavier.entrerClavierInt();
        switch (choixProfil) {
            case 1 -> {
                return Profil.ADMINISTRATEUR;
            }
            case 2 -> {
                return Profil.UTILISATEUR;
            }
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Choix incorrect");
                return choixProfil();
            }
        }
        return null;
    }
}
