package ProjetFilRouge.modele;

public class RechercheFichier extends Recherche{
    private String cheminRecherche;
    public RechercheFichier(String cheminRecherche){
        this.cheminRecherche = cheminRecherche;
    }

    public String getCheminRecherche(){
        return cheminRecherche;
    }
}
