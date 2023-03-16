package ProjetFilRouge.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FabriqueDate {
    public static Date creerDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        return new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear(), currentDateTime.getHour(), currentDateTime.getMinute());
    }
}
