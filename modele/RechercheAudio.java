package ProjetFilRouge.modele;


public class RechercheAudio extends Recherche {
    //Attributs
    private String cheminExtrait;
    private TypeFichier typeFichier;
    public RechercheAudio(String cheminExtrait) {
        super(TypeFichier.AUDIO);
        this.cheminExtrait = cheminExtrait;
    }
    //getters et setters
    public String getCheminExtrait() {
        return cheminExtrait;
    }

    public void setCheminExtrait(String cheminExtrait) {
        this.cheminExtrait = cheminExtrait;
    }

    //toString
    public String toString() {
        return this.getDateRecherche().toString() + " RechercheAudio ["+this.getMode()+"][cheminExtrait=" + cheminExtrait + "]";
    }
}
