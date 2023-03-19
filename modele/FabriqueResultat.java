package ProjetFilRouge.modele;

public class FabriqueResultat {
    public static Resultat creerResultat(String cheminResultat) {
        return new Resultat(cheminResultat);
    }
}
