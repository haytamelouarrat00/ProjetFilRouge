package ProjetFilRouge.modele;

public class FabriqueMoteur {
    public static Moteur creerMoteur(String nomMoteur, int nbMC, float seuilSimilarite, int bitsQuantification, float seuilSimCouleur, int tailleFenetre, int nbFenetres) {
        Moteur m = new Moteur(nomMoteur, nbMC, seuilSimilarite, bitsQuantification, seuilSimCouleur,tailleFenetre, nbFenetres);
        Moteur.moteurs.add(m);
        return m;
    }
}

