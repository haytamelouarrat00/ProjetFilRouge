package modele;

public class Date {
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;

    public String toString(){
        return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute;
    }
}
