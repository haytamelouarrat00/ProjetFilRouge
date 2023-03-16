package modele;

public class ProfilAdmin {
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
    private boolean estSuperAdmin;

    public ProfilAdmin(String nom, String prenom, String mdp) {
    	this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.login = prenom + "." + nom;
        this.estSuperAdmin = false;
    }

    public boolean estSuperAdmin() {
        return this.estSuperAdmin;
    }

    public void setSuperAdmin(boolean estSuperAdmin) {
        this.estSuperAdmin = estSuperAdmin;
    }

    public boolean verifierCorrespondanceProfil(String login, String mdp) {
		return (this.login.equals(login) && this.mdp.equals(mdp));
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
		return "Nom:" + nom + ", Prenom:" + prenom + ", Login :" + login + ", Mot de passe :" + mdp ;
	}
}
