package ProjetFilRouge.modele;

public class FabriqueRecherche {
    public static Recherche creerRechercheFichier(String cheminRecherche, TypeFichier typeFichier) {
        return new RechercheFichier(cheminRecherche, typeFichier);
    }
    public static Recherche creerRechercheMC(String requete){
        return new RechercheMotCle(requete);
    }
    public static Recherche creerRechercheImage(Couleurs couleurDominante) {
        return new RechercheImage(couleurDominante);
    }
    public static Recherche creerRechercheAudio(String cheminExtrait) {
        return new RechercheAudio(cheminExtrait);
    }
}
