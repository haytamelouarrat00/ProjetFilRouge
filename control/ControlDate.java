package ProjetFilRouge.control;

import modele.Date;

public class ControlDate {
    //Lexture d'une date depuis une chaine de caractère
    public Date dateDepuisString(String date) {
        String[] dateSplit = date.split(" ");
        String[] dateSplit2 = dateSplit[0].split("/");
        String[] dateSplit3 = dateSplit[1].split(":");
        return new Date(Integer.parseInt(dateSplit2[0]), Integer.parseInt(dateSplit2[1]), Integer.parseInt(dateSplit2[2]), Integer.parseInt(dateSplit3[0]), Integer.parseInt(dateSplit3[1]));
    }
    //Savoir si une date est entre deux autres dates
    public boolean isEntre(Date date, Date date1, Date date2) {
        return (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0);
    }
    //Savoir si une date est après ou avant une autre date
    public boolean isApres(Date date, Date date1) {
        return (date.compareTo(date1) >= 0);
    }
    //Savoir si une date est après ou avant une autre date
    public boolean isAvant(Date date, Date date1) {
        return (date.compareTo(date1) <= 0);
    }
}
