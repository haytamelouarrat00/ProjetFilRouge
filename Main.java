package ProjetFilRouge;

import ProjetFilRouge.control.ControlMoteurs;
import ProjetFilRouge.modele.Operation;
import ProjetFilRouge.modele.Parametres;
import ProjetFilRouge.modele.Profil;

import ProjetFilRouge.vueconsole.BoundaryChoixProfil;
import ProjetFilRouge.vueconsole.BoundaryOperations;

public class Main {
    public static void main(String[] args) {
        BoundaryChoixProfil boundaryChoixProfils = new BoundaryChoixProfil();
        BoundaryOperations boundaryOperations = new BoundaryOperations();
        ControlMoteurs controlMoteurs = new ControlMoteurs();
        controlMoteurs.randomMoteurs(Parametres.getNbMoteurs());
        controlMoteurs.randomActifs(Parametres.getNbMoteurs()/2);
        while (true) {
            Profil profil = boundaryChoixProfils.choixProfil();
            Operation operation = boundaryOperations.choixOperation(profil);
            boundaryOperations.effectuerOperation(operation, profil);
        }
    }
}
