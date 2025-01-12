package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Tests JUnit pour la classe {@link location.Utilisateur Utilisateur}.
 */
class TestUtilisateur {
  
  private Utilisateur utilisateur;
  private InformationPersonnelle info;
  private Film film;
  private Artiste realisateur;
  private Artiste acteur;
  private Evaluation evaluation;
  
  /**
   * Préparation des données avant chaque test.
   */
  @BeforeEach
  void setUp() {
    info = new InformationPersonnelle("Doe", "John", "123 Rue Principale", 25);
    utilisateur = new Utilisateur("john_doe", "password123", info);
    
    realisateur = new Artiste("Nolan", "Christopher", "Britannique");
    acteur = new Artiste("DiCaprio", "Leonardo", "Américain");
    
    List<Artiste> acteurs = Collections.singletonList(acteur);
    Set<Genre> genres = new HashSet<>(Collections.singletonList(Genre.Action));
    film = new Film("Inception", 2010, realisateur, acteurs, genres, 13);
    
    film.changerLocation(true); // Ouvrir le film à la location
    utilisateur.ensembleFilms().add(film);
    
    evaluation = new Evaluation("john_doe", film, 5, "Excellent film !");
  }
  
  @Test
  void testInscription() {
    Utilisateur utilisateurTest = new Utilisateur("new_user", "pass123", info);
    int res = utilisateurTest.inscription("john_doe", "password", info);
    assertEquals(1, res,
        "L'inscription devrait échouer pour un pseudo déjà utilisé.");
  }
  
  @Test
  void testConnexion() throws NonConnecteException, LocationException {
    assertFalse(utilisateur.connexion("wrong_user", "wrong_pass"));
    assertTrue(utilisateur.connexion("john_doe", "password123"));
    utilisateur.louerFilm(film);
    GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
    gestionUtilisateur.inscription("john_doe", "password123", info);
    assertTrue(utilisateur.connexion("john_doe", "password123"),
        "Connexion de l'utilisateur devrait réussir !");
    assertFalse(utilisateur.connexion("wrong_user", "wrong_pass"),
        "Connexion avec des identifiants incorrects devrait échouer !");
    
    
  }
  
  @Test
  void testDeconnexion() throws NonConnecteException {
    utilisateur.connexion("john_doe", "password123");
    utilisateur.deconnexion();
    assertThrows(NonConnecteException.class, utilisateur::deconnexion);
  }
  
  @Test
  void testLocationFilm() throws NonConnecteException, LocationException {
    utilisateur.connexion("john_doe", "password123");
    utilisateur.louerFilm(film);
    assertEquals(1, utilisateur.filmsEnLocation().size());
    assertTrue(utilisateur.filmsEnLocation().contains(film));
  }
  
  @Test
  void testLocationFilmAgeInsuffisant() {
    InformationPersonnelle jeune =
        new InformationPersonnelle("Doe", "Jane", "456 Rue", 12);
    Utilisateur jeuneUtilisateur =
        new Utilisateur("young_user", "pass123", jeune);
    jeuneUtilisateur.inscription("young_user", "pass123", info);
    jeuneUtilisateur.connexion("young_user", "pass123");
    assertThrows(LocationException.class,
        () -> jeuneUtilisateur.louerFilm(film));
  }
  
  @Test
  void testEvaluationFilm() throws NonConnecteException, LocationException {
    utilisateur.connexion("john_doe", "password123");
    utilisateur.louerFilm(film);
    utilisateur.ajouterEvaluation(film, evaluation);
    assertEquals(1, utilisateur.ensembleEvaluationsFilm(film).size());
    assertEquals("Excellent film !", utilisateur.ensembleEvaluationsFilm(film)
        .iterator().next().getCommentaire());
  }
  
  @Test
  void testModifierEvaluation() throws NonConnecteException, LocationException {
    utilisateur.connexion("john_doe", "password123");
    utilisateur.louerFilm(film);
    utilisateur.ajouterEvaluation(film, evaluation);
    
    Evaluation nouvelleEval =
        new Evaluation("john_doe", film, 4, "Très bon film.");
    utilisateur.modifierEvaluation(film, nouvelleEval);
    
    assertEquals(4,
        utilisateur.ensembleEvaluationsFilm(film).iterator().next().getNote());
  }
  
  @Test
  void testRechercheFilmsGenre() throws NonConnecteException {
    utilisateur.connexion("john_doe", "password123");
    Set<Film> filmsAction = utilisateur.ensembleFilmsGenre(Genre.Action);
    assertEquals(1, filmsAction.size());
    assertTrue(filmsAction.contains(film));
  }
  
  @Test
  void testConnexion2() {
    GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
    gestionUtilisateur.inscription("john_doe", "password123", info);
    
    assertTrue(utilisateur.connexion("john_doe", "password123"),
        "Connexion de l'utilisateur devrait réussir !");
    assertFalse(utilisateur.connexion("wrong_user", "wrong_pass"),
        "Connexion avec des identifiants incorrects devrait échouer !");
  }
  
  @Test
  void testRechercheFilmsRealisateur() throws NonConnecteException {
    utilisateur.connexion("john_doe", "password123");
    Set<Film> filmsRealisateur =
        utilisateur.ensembleFilmsRealisateur(realisateur);
    assertEquals(1, filmsRealisateur.size());
    assertTrue(filmsRealisateur.contains(film));
  }
  
  @Test
  void testRechercheFilmsActeur() throws NonConnecteException {
    utilisateur.connexion("john_doe", "password123");
    Set<Film> filmsActeur = utilisateur.ensembleFilmsActeur(acteur);
    assertEquals(1, filmsActeur.size());
    assertTrue(filmsActeur.contains(film));
  }
  
  @Test
  void testEvaluationMoyenne() throws NonConnecteException, LocationException {
    assertTrue(utilisateur.connexion("john_doe", "password123"),
        "Connexion de l'utilisateur a échoué !");
    utilisateur.louerFilm(film); // Assurez-vous que le film est loué
    utilisateur.ajouterEvaluation(film, evaluation); // Ajout de l'évaluation
    double moyenne = utilisateur.evaluationMoyenne(film); // Calcul de la
                                                          // moyenne
    assertEquals(5.0, moyenne, 0.01,
        "La moyenne des évaluations est incorrecte.");
  }
  
  
  @Test
  void testEvaluationMoyenneInexistant() throws NonConnecteException {
    utilisateur.connexion("john_doe", "password123");
    double moyenne = utilisateur.evaluationMoyenne("Matrix");
    assertEquals(-2, moyenne);
  }
}
