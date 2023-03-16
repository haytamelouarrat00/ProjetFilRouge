package modele;

public class ProfilUtilisateur {
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
    private int nombreConnexions;
    private boolean connecte;
    
    public ProfilUtilisateur(String nom, String prenom, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.login = prenom + "." + nom;
        this.nombreConnexions = 0;
    }

    public int getNombreConnexions() {
        return this.nombreConnexions;
    }

    public void setNombreConnexions(int nombreConnexions) {
        this.nombreConnexions = nombreConnexions;
    }
    
	
	public boolean verifierCorrespondanceProfil(String login, String mdp) {
		return (this.login.equals(login) && this.mdp.equals(mdp));
	}
	
	public boolean isConnecte() {
		return connecte;
	}
	
	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
	
	public String toString() {
		return "Nom:" + nom + ", Prenom:" + prenom + ", Login :" + login + ", Mot de passe :" + mdp + ", Connexion:" + connecte;
	}
}
