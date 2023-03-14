package ProjetFilRouge.modele;

import ProjetFilRouge.controleur.ControlRechercher;
import modele.Type_Fichier;

public class RechercheFichier extends Recherche {
    private String cheminRecherche;
    private modele.Type_Fichier typeFichier;

    public RechercheFichier(String cheminRecherche) {
        this.cheminRecherche = cheminRecherche;
    }

    public String getCheminRecherche() {
        return cheminRecherche;
    }

    public void setCheminRecherche(String cheminRecherche) {
        this.cheminRecherche = cheminRecherche;
    }

    public Type_Fichier getTypeFichier() {
        return Type_Fichier.getTypeFromExtension(ControlRechercher.getFileExtension(cheminRecherche));
    }

    public void setTypeFichier(Type_Fichier typeFichier) {
        this.typeFichier = typeFichier;
    }
}
