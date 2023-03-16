package ProjetFilRouge.vueconsole;
import ProjetFilRouge.controleur.ControlRechercher;
import ProjetFilRouge.controleur.ControlResultats;
import ProjetFilRouge.modele.*;

import java.util.Objects;

public class BoundaryRechercher {
    ControlRechercher controlRechercher;
    ControlResultats controlResultats;
    public BoundaryRechercher(ControlRechercher controlRechercher){
        this.controlRechercher = controlRechercher;
    }
    public void effectuerRechercheMC(){
        System.out.println("1- Entrez votre recherche: ");
        System.out.println("2- Retour ");
        String recherche = Clavier.entrerClavierString();
        controlRechercher.RechercherMC(recherche);
    }
    public Recherche effectuerRechercheFichier(){
        System.out.println("1- Entrer le chemin du fichier");
        System.out.println("2- Choisir parmi les fichiers disponibles");
        int choix = Clavier.entrerClavierInt();
        if (choix == 1) {
            System.out.println("Entrer le chemin du fichier: ");
            String chemin = Clavier.entrerClavierString();
            return controlRechercher.RechercheFichier(chemin);
        }
        System.out.println("1-Textes");
        System.out.println("2-Images Blanc et Noir");
        System.out.println("3-Images Couleur");
        System.out.println("4-Sons");
        int choix2 = Clavier.entrerClavierInt();
        String dirPath = switch (choix2) {
            case 1 -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8\\";
            case 2 -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_NB\\";
            case 3 -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_RGB\\";
            default -> "C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\TEST_SON\\";
        };
        int counter = 1;
        for (String path : Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath))) {
            System.out.println(counter++ + "\t-\t" + path);
        }
        System.out.println("Entrer le numéro du fichier: ");
        int choix3 = Clavier.entrerClavierInt();
        return controlRechercher.RechercheFichier(Objects.requireNonNull(ControlResultats.getAllFilesInDirectory(dirPath))[choix3 - 1]);
    }
    public void choixResultats(Recherche recherche){
        System.out.println("Choisissez un résultat: ");
        int choix = Clavier.entrerClavierInt();
        ControlResultats.ouvrirFichier(choix, recherche);
    }

    public static void main(String[] args) {
        ControlRechercher controlRechercher = new ControlRechercher();
        BoundaryRechercher boundaryRechercher = new BoundaryRechercher(controlRechercher);
        boundaryRechercher.choixResultats(boundaryRechercher.effectuerRechercheFichier());
    }
}
