package ProjetFilRouge.modele;

import java.util.ArrayList;

public class FabriqueRecherche {
    public static RechercheFichier creerRecherche(String path) {//fichier
        return new RechercheFichier(path);
    }
    public static RechercheMotCle creerRecherche(String requete, String[] inclusion, String[] exclusion) {//mot clé
        return new RechercheMotCle(requete, inclusion, exclusion, FabriqueDate.creerDate(), Parametres.mode);
    }
}
