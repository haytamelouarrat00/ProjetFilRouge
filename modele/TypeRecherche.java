package ProjetFilRouge.modele;

//Type de recherche
public enum TypeRecherche {
    RECHERCHE_MOT_CLE,
    RECHERCHE_FICHIER,
    RECHERCHE_IMAGE,
    RECHERCHE_AUDIO;

    public String toString() {
        return switch (this) {
            case RECHERCHE_MOT_CLE -> "RechercheMotClé";
            case RECHERCHE_FICHIER -> "RechercheFichier";
            case RECHERCHE_IMAGE -> "RechercheImage";
            default -> "RechercheAudio";
        };
    }
    public TypeRecherche getTypeRechercheFromName(String name){
        return switch (name) {
            case "RechercheMotClé" -> RECHERCHE_MOT_CLE;
            case "RechercheFichier" -> RECHERCHE_FICHIER;
            case "RechercheImage" -> RECHERCHE_IMAGE;
            default -> RECHERCHE_AUDIO;
        };
    }
}
