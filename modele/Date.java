package ProjetFilRouge.modele;

public class Date {
    // Attributs
    int jour;
    int mois;
    int annee;
    int heure;
    int minute;

    // Constructeur
    public Date(int jour, int mois, int annee, int heure, int minute){
        // Initialise les attributs avec les valeurs fournies
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }

    // Renvoie une représentation sous forme de chaîne de caractères de la date
    public String toString(){
        // Ajoute un 0 devant le jour/mois/heure/minute si nécessaire pour une présentation correcte
        if (jour < 10) {
            jour = Integer.parseInt("0" + jour);
        }
        if (mois < 10) {
            mois = Integer.parseInt("0" + mois);
        }
        if (heure < 10) {
            heure = Integer.parseInt("0" + heure);
        }
        if (minute < 10) {
            minute = Integer.parseInt("0" + minute);
        }
        //TODO: formattage plus approprié pour les dates
        return jour + "/" + mois + "/" + annee + " " + heure + ":" + minute;
    }

    // Compare cette date avec une autre date
    public int compareTo(Date dateD) {
        if (this.annee > dateD.annee) {
            return 1;
        } else if (this.annee < dateD.annee) {
            return -1;
        } else {
            if (this.mois > dateD.mois) {
                return 1;
            } else if (this.mois < dateD.mois) {
                return -1;
            } else {
                if (this.jour > dateD.jour) {
                    return 1;
                } else if (this.jour < dateD.jour) {
                    return -1;
                } else {
                    if (this.heure > dateD.heure) {
                        return 1;
                    } else if (this.heure < dateD.heure) {
                        return -1;
                    } else {
                        return Integer.compare(this.minute, dateD.minute);
                    }
                }
            }
        }
    }
}
