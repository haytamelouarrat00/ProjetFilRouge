package ProjetFilRouge.modele;


//Parametres de base du programme
public class Parametres {
    private static int nbMoteurs = 10;
    private static Mode mode = Mode.OUVERT;
    private static String pswd = "hoho";

    public static String getPswd() {
        return pswd;
    }

    public static void setPswd(String pswd) {
        Parametres.pswd = pswd;
    }

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
