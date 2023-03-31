package ProjetFilRouge.vueconsole;

import ProjetFilRouge.control.*;
import ProjetFilRouge.modele.*;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class BoundaryOperations {
    ControlRecherche controlRecherche = new ControlRecherche();
    ControlHistorique controlHistorique = new ControlHistorique();
    ControlMoteurs controlMoteurs = new ControlMoteurs();

    public Operation choixOperation(Profil profil) {
        System.out.println("Choisissez votre opération :");
        switch (profil) {
            case ADMINISTRATEUR -> {
                System.out.println("1. Paramétrer l'indexation");
                System.out.println("2. Gestion des moteurs");
                System.out.println("3. Changer le mode de recherche");
                System.out.println("4. Retour");
                int choixOperation = Clavier.entrerClavierInt();
                switch (choixOperation) {
                    case 1 -> {
                        return Operation.PARAMETRES;
                    }
                    case 2 -> {
                        return Operation.MOTEURS;
                    }
                    case 3 -> {
                        return Operation.MODE;
                    }
                    case 4 -> {
                        return Operation.RETOUR;
                    }
                    default -> {
                        System.out.println("Choix incorrect");
                        choixOperation(profil);
                    }
                }
            }
            case UTILISATEUR -> {
                System.out.println("1. Rechercher");
                System.out.println("2. Historique");
                System.out.println("3. Retour");
                int choixOperation = Clavier.entrerClavierInt();
                switch (choixOperation) {
                    case 1 -> {
                        return Operation.RECHERCHE;
                    }
                    case 2 -> {
                        return Operation.HISTORIQUE;
                    }
                    case 3 -> {
                        return Operation.RETOUR;
                    }
                    default -> {
                        System.out.println("Choix incorrect");
                        choixOperation(profil);
                    }
                }
            }
        }
        return null;
    }

    public TypeRecherche choixRecherche() {
        System.out.println("Recherche:");
        System.out.println("1. Recherche par mot clé[Recherche TEXTE]");
        System.out.println("2. Recherche par couleur[Recherche IMAGE]");
        System.out.println("3. Recherche par extrait[Recherche AUDIO]");
        System.out.println("4. Recherche par fichier");
        System.out.println("5. Retour");
        int choixRecherche = Clavier.entrerClavierInt();
        switch (choixRecherche) {
            case 1 -> {
                return TypeRecherche.RECHERCHE_MOT_CLE;
            }
            case 2 -> {
                return TypeRecherche.RECHERCHE_IMAGE;
            }
            case 3 -> {
                return TypeRecherche.RECHERCHE_AUDIO;
            }
            case 4 -> {
                return TypeRecherche.RECHERCHE_FICHIER;
            }
            case 5 -> {
                //TODO: Retour
                return null;
            }
            default -> {
                System.out.println("Choix incorrect");
                choixRecherche();
            }
        }
        return null;
    }

    public Recherche rechercher(ControlRecherche controlRecherche, TypeRecherche typeRecherche) {
        switch (typeRecherche) {
            case RECHERCHE_MOT_CLE -> {
                System.out.println("Recherche par mot clé");
                System.out.println("Entrez la requete :");
                String motCle = Clavier.entrerClavierLigne();
                return controlRecherche.rechercherMotCle(motCle);
            }
            case RECHERCHE_IMAGE -> {
                System.out.println("Recherche par couleur");
                for (Couleurs couleur : Couleurs.values()) {
                    System.out.println(couleur.ordinal() + 1 + ". " + couleur);
                }
                System.out.println("Entrez la couleur :");
                int couleur = Clavier.entrerClavierInt();
                return controlRecherche.rechercherImage(Couleurs.values()[couleur-1]);
            }
            case RECHERCHE_AUDIO -> {
                System.out.println("Recherche par extrait");
                System.out.println("Entrez le chemin de l'extrait :");
                String extrait = Clavier.entrerClavierString();
                return controlRecherche.rechercherSon(extrait);
            }
            case RECHERCHE_FICHIER -> {
                System.out.println("Recherche par fichier");
                System.out.println("1. Choisir parmi les fichiers disponibles");
                System.out.println("2. Entrez le chemin du fichier :");
                int choix = Clavier.entrerClavierInt();
                String fichier;
                switch (choix) {
                    case 1 -> {
                        System.out.println("1. Textes");
                        System.out.println("2. Images Noir et Blanc");
                        System.out.println("3. Images Couleurs");
                        System.out.println("4. Sons");
                        int choixType = Clavier.entrerClavierInt();
                        String dirPath = switch (choixType) {
                            case 1 -> "\\src\\ProjetFilRouge\\Textes_UTF8";
                            case 2 -> "\\src\\ProjetFilRouge\\TEST_NB";
                            case 3 -> "\\src\\ProjetFilRouge\\TEST_RGB";
                            case 4 -> "\\src\\ProjetFilRouge\\TEST_SON";
                            //TODO: gestion erreur
                            default -> throw new IllegalStateException("Unexpected value: " + choixType);
                        };
                        int counter = 1;
                        for (String fichierRepertoire : Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + dirPath))) {
                            System.out.println(counter++ + ". " + fichierRepertoire);
                        }
                        System.out.println("Entrez le numéro du fichier :");
                        int choixFichier = Clavier.entrerClavierInt();
                        fichier = Objects.requireNonNull(ControlFichier.getFichiersDansRepertoire(ControlFichier.getCheminRelative() + dirPath))[choixFichier];
                    }
                    case 2 -> {
                        System.out.println("Entrez le chemin du fichier :");
                        fichier = Clavier.entrerClavierString();
                    }
                    //TODO: gestion erreur
                    default -> {
                        throw new IllegalStateException("Choix incorrect: " + choix);
                    }
                }
                return controlRecherche.rechercherFichier(fichier);
            }
        }
        return null;
    }

    public void afficherResultats(Recherche recherche) {
        System.out.println("Résultats de la recherche :");
        int counter = 1;
        for (Resultat resultat : recherche.getResultats()) {
            System.out.println(counter++ + ". " + resultat.getCheminResultat());
        }
    }

    public void ouvrirResultat(Recherche recherche, int choix) {
        ControlFichier.ouvrirFichier(choix, recherche);
    }

    //CONSULTER HISTORIQUE
    public void consulterHistorique() {
        try {
            //constructor of file class having file as argument
            File file = new File(ControlFichier.getCheminRelative() + "\\src\\ProjetFilRouge\\historique.txt");
            if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())         //checks file exists or not
                desktop.open(file);    //opens the specified file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void effacerHistorique() {
        ControlDate controlDate = new ControlDate();
        System.out.println("Suppression de l'historique:");
        System.out.println("1. Effacer les recherches dans une intervalle de temps");
        System.out.println("2. Effacer les recherches d'un type");
        System.out.println("3. Effacer les recherches d'un mode");
        System.out.println("4. Effacer toutes les recherches");
        System.out.println("5. Retour");
        int choix = Clavier.entrerClavierInt();
        switch (choix) {
            case 1 -> {
                System.out.println("Entrez la date de début jj/mm/aaaa hh:mm :");
                String dateDebut = Clavier.entrerClavierLigne();
                System.out.println("Si vous souhaitez effacer les recherches depuis cette début, entrez 0 sinon entrez une date de fin jj/mm/aaaa hh:mm :");
                String dateFin = Clavier.entrerClavierLigne();
                if (dateFin.equals("0")) {
                    controlHistorique.effacerHistorique(controlDate.dateDepuisString(dateDebut), null);
                    return;
                }
                System.out.println("souhaitez-vous effacer les recherches après cette date? (O/N)");
                String choixFin = Clavier.entrerClavierLigne();
                if (choixFin.equals("O") || choixFin.equals("o")) {
                    controlHistorique.effacerHistorique(null, controlDate.dateDepuisString(dateFin));
                    return;
                }
                controlHistorique.effacerHistorique(controlDate.dateDepuisString(dateDebut), controlDate.dateDepuisString(dateFin));
            }
            case 2 -> {
                System.out.println("Entrez le type de recherche à effacer:");
                System.out.println("1. Texte");
                System.out.println("2. Image");
                System.out.println("3. Son");
                System.out.println("4. Fichier");
                int choixType = Clavier.entrerClavierInt();
                TypeRecherche typeRecherche = switch (choixType) {
                    case 1 -> TypeRecherche.RECHERCHE_MOT_CLE;
                    case 2 -> TypeRecherche.RECHERCHE_IMAGE;
                    case 3 -> TypeRecherche.RECHERCHE_AUDIO;
                    case 4 -> TypeRecherche.RECHERCHE_FICHIER;
                    default -> throw new IllegalStateException("Unexpected value: " + choixType);
                };
                controlHistorique.effacerHistorique(typeRecherche);
            }
            case 3 -> {
                System.out.println("Entrez le mode de recherche à effacer:");
                System.out.println("1. Ouvert");
                System.out.println("2. Fermé");
                int choixMode = Clavier.entrerClavierInt();
                if (choixMode == 1) {
                    controlHistorique.effacerHistorique(Mode.OUVERT);
                } else if (choixMode == 2) {
                    controlHistorique.effacerHistorique(Mode.FERME);
                } else {
                    System.out.println("Choix incorrect");
                }
            }
            case 4 -> {
                controlHistorique.effacerHistorique();
                controlHistorique.creerFichierHistorique("historique.txt");
            }
            //TODO: retour
            case 5 -> {
                return;
            }
            default -> {
                System.out.println("Choix incorrect");
                effacerHistorique();
            }
        }
    }

    public void filtrerHistorique() {
        ControlDate controlDate = new ControlDate();
        System.out.println("Filtrer l'historique:");
        System.out.println("1. Filtrer les recherches dans une intervalle de temps");
        System.out.println("2. Filtrer les recherches d'un type");
        System.out.println("3. Filtrer les recherches d'un mode");
        System.out.println("4. Retour");
        int choix = Clavier.entrerClavierInt();
        switch (choix) {
            case 1 -> {
                System.out.println("Entrez la date de début jj/mm/aaaa hh:mm :");
                String dateDebut = Clavier.entrerClavierLigne();
                System.out.println("Si vous souhaitez filtrer les recherches depuis cette début, entrez 0 sinon entrez une date de fin jj/mm/aaaa hh:mm :");
                String dateFin = Clavier.entrerClavierLigne();
                if (dateFin.equals("0")) {
                    controlHistorique.filtrerHistorique(controlDate.dateDepuisString(dateDebut), null);
                    return;
                }
                System.out.println("souhaitez-vous filtrer les recherches après cette date? (O/N)");
                String choixFin = Clavier.entrerClavierLigne();
                if (choixFin.equals("O") || choixFin.equals("o")) {
                    controlHistorique.filtrerHistorique(null, controlDate.dateDepuisString(dateFin));
                    return;
                }
                controlHistorique.filtrerHistorique(controlDate.dateDepuisString(dateDebut), controlDate.dateDepuisString(dateFin));
            }
            case 2 -> {
                System.out.println("Entrez le type de recherche à filtrer:");
                System.out.println("1. Texte");
                System.out.println("2. Image");
                System.out.println("3. Son");
                System.out.println("4. Fichier");
                int choixType = Clavier.entrerClavierInt();
                TypeRecherche typeRecherche = switch (choixType) {
                    case 1 -> TypeRecherche.RECHERCHE_MOT_CLE;
                    case 2 -> TypeRecherche.RECHERCHE_IMAGE;
                    case 3 -> TypeRecherche.RECHERCHE_AUDIO;
                    case 4 -> TypeRecherche.RECHERCHE_FICHIER;
                    default -> throw new IllegalStateException("Unexpected value: " + choixType);
                };
                controlHistorique.filtrerHistorique(typeRecherche);
            }
            case 3 -> {
                System.out.println("Entrez le mode de recherche à filtrer:");
                System.out.println("1. Ouvert");
                System.out.println("2. Fermé");
                int choixMode = Clavier.entrerClavierInt();
                if (choixMode == 1) {
                    controlHistorique.filtrerHistorique(Mode.OUVERT);
                } else if (choixMode == 2) {
                    controlHistorique.filtrerHistorique(Mode.FERME);
                } else {
                    System.out.println("Choix incorrect");
                }
            }
            //TODO: retour
            case 4 -> {
                return;
            }
            default -> {
                System.out.println("Choix incorrect");
                filtrerHistorique();
            }

        }
    }

    //MOTEURS
    public void gestionMoteurs() {
        ControlMoteurs controlMoteurs = new ControlMoteurs();
        System.out.println("Gestion des moteurs de recherche:\n");
        System.out.println("Les moteurs de recherche disponibles sont:\n");
        for (Moteur moteur : Moteur.moteurs) {
            System.out.println(moteur.getNom());
        }
        System.out.println("Les moteurs de recherche actifs sont:\n");
        for (Moteur moteur : Moteur.moteursActifs) {
            System.out.println(moteur.getNom());
        }
        System.out.println("Affichier les détails d'un moteur de recherche (O/N)?");
        String choix = Clavier.entrerClavierString();
        if (choix.equals("O") || choix.equals("o")) {
            System.out.println("Entrez le nom du moteur de recherche:");
            String nom = Clavier.entrerClavierLigne();
            Moteur m = controlMoteurs.getMoteurbyNom(nom);
            if (Objects.nonNull(m)) {
                System.out.println(m.toString());
            } else {
                System.out.println(nom+" introuvable");
            }
        }
        while (true) {
            System.out.println("Sélectionner un moteur de recherche à activer? Si oui, entrez son nom, sinon entrez 0");
            String choixMoteur = Clavier.entrerClavierLigne();
            if (choixMoteur.equals("0")) {
                break;
            } else {
                Moteur m = controlMoteurs.getMoteurbyNom(choixMoteur);
                if(Objects.nonNull(m)) {
                    controlMoteurs.setActifMoteur(m);
                } else {
                    System.out.println(choixMoteur+" introuvable");
                }
            }
        }
        while (true) {
            if (Moteur.moteursActifs.size() > 1) {
                System.out.println("Sélectionner un moteur de recherche à désactiver? Si oui, entrez son nom, sinon entrez 0");
                String choixMoteur = Clavier.entrerClavierLigne();
                if (choixMoteur.equals("0")) {
                    break;
                } else {
                    Moteur m = controlMoteurs.getMoteurbyNom(choixMoteur);
                    if(Objects.nonNull(m)) {
                        controlMoteurs.setInactifMoteur(m);
                    } else {
                        System.out.println(choixMoteur+" introuvable");
                    }
                }
            }
        }
    }

    public void parametrageIndexation() {
        System.out.println("Paramétrage de l'indexation:");

        System.out.println("1. Paramétrer tous les moteurs");
        System.out.println("2. Paramétrer un moteur");
        System.out.println("3. Retour");
        int choix = Clavier.entrerClavierInt();

        System.out.println("[Recherche par mot clé]");
        System.out.println("1. Nombre de mots clés commun minimum");
        System.out.println("[Recherche par fichicer]");
        System.out.println("2. Seuil de similarité minimum");
        System.out.println("[Recherche par image]");
        System.out.println("3. Bits de quantification");
        System.out.println("4. Seuil similarité couleur");
        System.out.println("[Recherche par extrait audio]");
        System.out.println("5. Taille des Fenetres");
        System.out.println("6. Nombre de fenetres");
        System.out.println("7. Retour");
        switch (Clavier.entrerClavierInt()) {
            case 1 -> {
                System.out.println("Entrez le nombre de mots clés communs minimum:");
                int nbMotsClesCommuns = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setMotsClesMin(nbMotsClesCommuns);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setMotsClesMin(nbMotsClesCommuns);
                }

            }
            case 2 -> {
                System.out.println("Entrez le seuil de similarité minimum:");
                int seuilSimilarite = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setSeuilSimMin(seuilSimilarite);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setSeuilSimMin(seuilSimilarite);
                }

            }
            case 3 -> {
                System.out.println("Entrez le nombre de bits de quantification:");
                int nbBitsQuantification = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setBitsQuantif(nbBitsQuantification);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setBitsQuantif(nbBitsQuantification);
                }

            }
            case 4 -> {
                System.out.println("Entrez le seuil de similarité couleur:");
                int seuilSimilariteCouleur = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setSeuilSimCouleur(seuilSimilariteCouleur);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setSeuilSimCouleur(seuilSimilariteCouleur);
                }

            }
            case 5 -> {
                System.out.println("Entrez la taille des fenetres:");
                int tailleFenetres = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setTailleFenetre(tailleFenetres);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setTailleFenetre(tailleFenetres);
                }

            }
            case 6 -> {
                System.out.println("Entrez le nombre de fenetres:");
                int nbFenetres = Clavier.entrerClavierInt();
                if (choix == 1) {
                    for (Moteur m : Moteur.moteurs) {
                        m.setNbFenetres(nbFenetres);
                    }
                } else if (choix == 2) {
                    System.out.println("Entrer le nom du moteur à indexer:");
                    String m = Clavier.entrerClavierString();
                    Moteur moteur = controlMoteurs.getMoteurbyNom(m);
                    if (Objects.nonNull(moteur)) moteur.setNbFenetres(nbFenetres);
                }

            }
            case 7 -> {
                return;
            }
            default -> {
                System.out.println("Choix incorrect");
                parametrageIndexation();
            }
        }

    }

    public void changerMode() {
        System.out.println("Changer le mode de la recherche:");
        System.out.println("1. Recherche Ouverte");
        System.out.println("2. Recherche Fermée");
        System.out.println("3. Retour");
        int choix = Clavier.entrerClavierInt();
        Parametres.setMode((choix == 1) ? Mode.OUVERT : Mode.FERME);
    }

    public void effectuerOperation(Operation operation, Profil profil){
        switch(operation){
            case PARAMETRES->{
                parametrageIndexation();

            }
            case MODE -> {
                changerMode();

            }
            case MOTEURS -> {
                gestionMoteurs();

            }
            case RECHERCHE -> {
                Recherche rech = rechercher(this.controlRecherche, choixRecherche());
                afficherResultats(rech);
                while (true) {
                    System.out.println("Souhaitez vous consulter un résultat? Si oui, entrez son numéro, sinon entrez 0");
                    int choix = Clavier.entrerClavierInt();
                    if (choix != 0) {
                        ouvrirResultat(rech, choix);
                    } else {
                        break;
                    }
                }
                controlHistorique.ajouterRecherche(rech);
            }
            case HISTORIQUE -> {
                System.out.println("Historique:");
                System.out.println("1. Afficher l'historique");
                System.out.println("2. Supprimer l'historique");
                System.out.println("3. Filtrer l'historique");
                System.out.println("4. Retour");
                switch (Clavier.entrerClavierInt()){
                    case 1-> {
                        consulterHistorique();
                    }
                    case 2-> {
                        effacerHistorique();
                    }
                    case 3-> {
                        filtrerHistorique();
                    }
                    //TODO: retour
                    case 4-> {
                        return;
                    }
                }

            }
            //TODO: retour
            case RETOUR -> {
                break;
            }
        }
    }
}
