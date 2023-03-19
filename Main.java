package ProjetFilRouge;

import control.ControlMoteurs;
import modele.Operation;
import modele.Parametres;
import modele.Profil;
import vueconsole.BoundaryChoixProfil;
import vueconsole.BoundaryOperations;

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
