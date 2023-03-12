package ProjetFilRouge.modele;

import java.lang.reflect.Array;

public class RechercheMotCle extends Recherche{
    private String requete;
    private String[] inclusion;
    private String[] exclusion;

    public RechercheMotCle(String requete, String[] inclusion, String[] exclusion) {
        this.requete = requete;
        this.inclusion = inclusion;
        this.exclusion = exclusion;
    }

    public String getRequete() {
        return requete;
    }

    public String[] getInclusion() {
        return inclusion;
    }

    public String[] getExclusion() {
        return exclusion;
    }
}
