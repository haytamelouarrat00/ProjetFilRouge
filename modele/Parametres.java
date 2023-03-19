package ProjetFilRouge.modele;


//Parametres de base du programme
public class Parametres {
    private static int nbMoteurs = 10;
    private static Mode mode = Mode.OUVERT;

    public static void setNbMoteurs(int nbMoteurs) {
        Parametres.nbMoteurs = nbMoteurs;
    }

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        Parametres.mode = mode;
    }

    public static int getNbMoteurs() {
        return nbMoteurs;
    }
}
