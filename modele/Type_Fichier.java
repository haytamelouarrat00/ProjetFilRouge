package modele;

import java.util.Arrays;

public enum Type_Fichier {
    TEXTE(new String[]{"xml", "txt"}),
    IMAGE(new String[]{"jpg", "bmp", "txt"}),
    AUDIO(new String[]{"bin", "wav", "txt"});

    private final String[] extensions;

    Type_Fichier(String[] extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }
}
