package ProjetFilRouge.modele;

import java.util.Arrays;

//Type de fichier et leurs extensions
public enum TypeFichier {
    //Types de fichiers possibles et leurs extensions
    TEXTE(new String[]{"xml", "txt", "html", "docx", "pdf"}),
    IMAGE(new String[]{"jpg", "bmp", "txt", "png", "gif", "tif", "tiff", "ico", "jpeg"}),
    AUDIO(new String[]{"bin", "wav", "txt", "mp3", "ogg", "wma", "flac", "aac", "m4a"});

    private final String[] extensions;

    TypeFichier(String[] extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }
    //Fonction qui retourne le type de fichier à partir de son extension
    public static TypeFichier getTypeFromExtension(String extension) {
        for (TypeFichier type : TypeFichier.values()) {
            if (Arrays.asList(type.getExtensions()).contains(extension)) {

                return type;
            }
        }
        return null;
    }
    public static String getRepertoireResultatFromExtension(String ext){
        TypeFichier type = getTypeFromExtension(ext);
        if (type == null) return null;
        else return switch (type) {
            case TEXTE -> "\\src\\ProjetFilRouge\\Textes_UTF8\\";
            case IMAGE -> ((ext.equals("jpg") || ext.equals("txt"))) ? "\\src\\ProjetFilRouge\\TEST_RGB\\" : "\\src\\ProjetFilRouge\\TEST_NB\\";
            case AUDIO -> "\\src\\ProjetFilRouge\\TEST_SON\\";
        };
    }
}
