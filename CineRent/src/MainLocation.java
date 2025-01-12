import location.*;
import io.Sauvegarde;
import java.io.IOException;
import java.util.Set;

/**
 * Classe principale pour tester les fonctionnalités du système de location de
 * films. Elle inclut des opérations administratives (gestion des artistes,
 * films, et sauvegarde) ainsi que des interactions utilisateur (inscription,
 * connexion, location, et évaluation).
 */
public class MainLocation {
  
  public static void main(String[] args) {
    try {
      // ********** PARTIE ADMINISTRATEUR **********
      System.out.println("=== Partie Administrateur ===");
      
      // Création de l'administrateur
      Administrateur admin = new Administrateur();
      
      // Gestion des artistes
      System.out.println("--- Création des artistes ---");
      Artiste realisateur =
          admin.creerArtiste("Nolan", "Christopher", "Britannique");
      Artiste acteur1 = admin.creerArtiste("DiCaprio", "Leonardo", "Américain");
      Artiste acteur2 = admin.creerArtiste("Hardy", "Tom", "Britannique");
      
      System.out.println(
          "Artistes créés : " + realisateur + ", " + acteur1 + ", " + acteur2);
      
      // Gestion des films
      System.out.println("--- Création des films ---");
      Film film1 = admin.creerFilm("Inception", realisateur, 2010, 13);
      Film film2 = admin.creerFilm("Dunkirk", realisateur, 2017, 12);
      System.out.println(
          "Films créés : " + film1.getTitre() + ", " + film2.getTitre());
      
      // Ajout d'acteurs et genres
      admin.ajouterActeurs(film1, acteur1, acteur2);
      admin.ajouterGenres(film1, Genre.Action, Genre.ScienceFiction);
      admin.ajouterGenres(film2, Genre.Drame, Genre.Historique);
      
      // Gestion des locations
      admin.ouvrirLocation(film1);
      admin.fermerLocation(film2);
      
      System.out
          .println("Film 1 ouvert à la location, Film 2 fermé à la location.");
      
      // Sauvegarde des données
      Sauvegarde sauvegarde = new Sauvegarde(admin);
      sauvegarde.sauvegarderDonnees("donnees.sav");
      System.out.println("Données sauvegardées avec succès.");
      
      // ********** PARTIE UTILISATEUR **********
      System.out.println("\n=== Partie Utilisateur ===");
      
      // Gestion des utilisateurs
      GestionUtilisateur gestionUtilisateur = GestionUtilisateur.getInstance();
      
      // Informations personnelles
      InformationPersonnelle info1 =
          new InformationPersonnelle("Doe", "John", "123 Rue Principale", 25);
      InformationPersonnelle info2 =
          new InformationPersonnelle("Smith", "Jane", "456 Avenue", 18);
      
      // Inscription
      int res1 =
          gestionUtilisateur.inscription("john_doe", "password123", info1);
      int res2 =
          gestionUtilisateur.inscription("jane_smith", "password456", info2);
      
      System.out
          .println("Inscription John: " + (res1 == 0 ? "Réussie" : "Échec"));
      System.out
          .println("Inscription Jane: " + (res2 == 0 ? "Réussie" : "Échec"));
      
      // Connexion des utilisateurs
      Utilisateur utilisateur1 =
          gestionUtilisateur.connexion("john_doe", "password123");
      Utilisateur utilisateur2 =
          gestionUtilisateur.connexion("jane_smith", "password456");
      
      if (utilisateur1 != null) {
        System.out.println("John connecté.");
      }
      if (utilisateur2 != null) {
        System.out.println("Jane connectée.");
      }
      
      // Ajouter des films pour utilisateur
      utilisateur1.ensembleFilms().add(film1);
      utilisateur1.ensembleFilms().add(film2);
      
      // Location d'un film
      utilisateur1.louerFilm(film1);
      System.out.println("Film loué par John: " + film1.getTitre());
      
      // Ajout et modification d'évaluations
      Evaluation evaluation1 =
          new Evaluation("john_doe", film1, 5, "Excellent film !");
      utilisateur1.ajouterEvaluation(film1, evaluation1);
      System.out
          .println("Evaluation ajoutée pour le film: " + film1.getTitre());
      
      Evaluation evaluation2 =
          new Evaluation("john_doe", film1, 4, "Très bon film !");
      utilisateur1.modifierEvaluation(film1, evaluation2);
      System.out
          .println("Evaluation modifiée pour le film: " + film1.getTitre());
      
      // Moyenne des évaluations
      double moyenne = utilisateur1.evaluationMoyenne(film1);
      System.out.println(
          "Moyenne des évaluations pour " + film1.getTitre() + " : " + moyenne);
      
      // Recherche par genre et acteur
      Set<Film> filmsAction = utilisateur1.ensembleFilmsGenre(Genre.Action);
      System.out.println("Films d'action disponibles: ");
      filmsAction.forEach(f -> System.out.println(f.getTitre()));
      
      Set<Film> filmsDiCaprio = utilisateur1.ensembleFilmsActeur(acteur1);
      System.out.println("Films avec Leonardo DiCaprio:");
      filmsDiCaprio.forEach(f -> System.out.println(f.getTitre()));
      
      // Fin de location
      utilisateur1.finLocationFilm(film1);
      System.out.println("Location terminée pour: " + film1.getTitre());
      
      // Déconnexion
      utilisateur1.deconnexion();
      System.out.println("John déconnecté.");
      
      // ********** Chargement des données **********
      System.out.println("\n=== Chargement des données ===");
      sauvegarde.chargerDonnees("donnees.sav");
      
      // Vérifier les données après chargement
      System.out.println("Films après chargement : " + admin.ensembleFilms());
      System.out
          .println("Artistes après chargement : " + admin.ensembleActeurs());
      
    } catch (NonConnecteException | LocationException e) {
      System.err.println("Erreur utilisateur : " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Erreur de sauvegarde/chargement : " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Erreur : " + e.getMessage());
    }
  }
}
