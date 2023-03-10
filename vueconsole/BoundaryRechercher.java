package ProjetFilRouge.vueconsole;
import ProjetFilRouge.controleur.ControlRechercher;
import modele.Type_Fichier;

public class BoundaryRechercher {
    ControlRechercher controlRechercher;
    public BoundaryRechercher(ControlRechercher controlRechercher){
        this.controlRechercher = controlRechercher;
    }
    public void effectuerRechercheMC(){
        System.out.println("Entrez votre recherche: ");
        String recherche = Clavier.entrerClavierString();
        controlRechercher.filtrerRequete(recherche);
    }
    public Type_Fichier effectuerRechercheFichier(){
        System.out.println("Type de fichier à rechercher: ");
        for (Type_Fichier type_fichier : Type_Fichier.values()) {
            System.out.println(type_fichier.ordinal() + " - " + type_fichier);
        }
        int choix = Clavier.entrerClavierInt();
        return Type_Fichier.values()[choix];
    }
}
