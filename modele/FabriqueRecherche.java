package ProjetFilRouge.modele;

import java.util.ArrayList;

public class FabriqueRecherche {
    public static RechercheFichier creerRecherche(String path, ArrayList<Moteurs> moteurs) {//fichier
        return new RechercheFichier(path);
    }
    public static RechercheMotCle creerRecherche(String requete, String[] inclusion, String[] exclusion, Mode mode) {//mot clé
        return new RechercheMotCle(requete, inclusion, exclusion, FabriqueDate.creerDate(), mode);
    }
}
