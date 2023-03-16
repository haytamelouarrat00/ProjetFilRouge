package modele;

public class FabriqueProfil {
	public static void creerProfil(Profil profil, String nom, String prenom, String mdp) {
		switch (profil) {
		case ADMIN:
			ProfilAdmin admin = new ProfilAdmin(nom, prenom, mdp);
			break;
		case UTILISATEUR:
			ProfilUtilisateur utilisateur = new ProfilUtilisateur(nom, prenom, mdp);
			break;
		}
	}
}
