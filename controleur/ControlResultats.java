package ProjetFilRouge.controleur;

import java.io.File;
import java.util.Arrays;

public class ControlResultats {

    public String[] getAllFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                fileNames[i] = files[i].getName();
            }
            return fileNames;
        }
        return null;
    }

    public static void main(String[] args) {
        ControlResultats controlResultats = new ControlResultats();
        String[] files = controlResultats.getAllFilesInDirectory("C:\\Users\\eohay\\Documents\\PFR\\src\\ProjetFilRouge\\Textes_UTF8");
        for(String file : files) {
            System.out.println(file);
        }
    }
}
