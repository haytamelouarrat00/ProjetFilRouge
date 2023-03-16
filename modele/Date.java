package ProjetFilRouge.modele;

public class Date {
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;
    public Date(int jour, int mois, int annee, int heure, int minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }
    public String toString(){
        return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute;
    }
}
