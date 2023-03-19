package ProjetFilRouge.controleur;

import ProjetFilRouge.modele.Moteurs;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlGestionMoteurRecherche {
	
	public static void gererMoteurs() {
	    ArrayList<Moteurs> moteurs = Moteurs.getMoteurs();
	    ArrayList<Moteurs> moteursActifs = new ArrayList<Moteurs>();
	    ArrayList<Moteurs> moteursInactifs = new ArrayList<Moteurs>();

	    // Séparer les noms des moteurs en tableaux de chaînes de caractères
	    String[] moteursInclus = inclusStr.split(",");
	    String[] moteursExclus = excluStr.split(",");

	    for (Moteurs moteur : moteurs) {
	        boolean inclus = false;
	        boolean exclu = false;

	        // Vérifier si le nom du moteur est inclus ou exclu
	        for (String nom : moteursInclus) {
	            if (moteur.getNom().equals(nom.trim())) {
	                inclus = true;
	                break;
	            }
	        }
	        for (String nom : moteursExclus) {
	            if (moteur.getNom().equals(nom.trim())) {
	                exclu = true;
	                break;
	            }
	        }

	        // Ajouter le moteur dans la liste correspondante
	        if (inclus && !exclu) {
	            moteursActifs.add(moteur);
	        } else {
	            moteursInactifs.add(moteur);
	        }
	    }

	    // Mettre à jour les listes de moteurs actifs et inactifs dans la classe Moteurs
	    Moteurs.setMoteursActifs(moteursActifs);
	    Moteurs.setMoteursInactifs(moteursInactifs);
	}

}