package ProjetFilRouge.modele;

import java.util.Arrays;

public enum Type_Fichier {
    //Types de fichiers possibles et leurs extensions
    TEXTE(new String[]{"xml"}),
    IMAGE(new String[]{"jpg", "bmp", "txt"}),
    AUDIO(new String[]{"bin", "wav", "txt"});

    private final String[] extensions;

    Type_Fichier(String[] extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }
    //Fonction qui retourne le type de fichier à partir de son extension
    public static Type_Fichier getTypeFromExtension(String extension) {
        for (Type_Fichier type : Type_Fichier.values()) {
            if (Arrays.asList(type.getExtensions()).contains(extension)) {
                return type;
            }
        }
        return null;
    }
}
