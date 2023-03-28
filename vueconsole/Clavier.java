package ProjetFilRouge.vueconsole;

import java.util.Scanner;

public class Clavier {
    private static final Scanner scanner = new Scanner(System.in);

    public static int entrerClavierInt() {
        return scanner.nextInt();
    }

    public static String entrerClavierString() {
        return scanner.next();
    }
    public static String entrerClavierLigne(){
        String s = scanner.next();
        s += scanner.nextLine();
        return s;
    }

}
