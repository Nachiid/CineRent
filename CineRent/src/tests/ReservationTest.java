package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Tests JUnit pour la classe {@link location.Reservation Reservation}.
 */
class TestReservation {
  
  private Reservation reservation;
  private Film film1;
  private Film film2;
  private Film film3;
  private Film film4;
  private Artiste realisateur;
  private Artiste acteur;
  
  /**
   * Initialisation avant chaque test.
   */
  @BeforeEach
  void setUp() {
    reservation = new Reservation();
    
    realisateur = new Artiste("Nolan", "Christopher", "Britannique");
    acteur = new Artiste("DiCaprio", "Leonardo", "Américain");
    List<Artiste> acteurs = Arrays.asList(acteur);
    Set<Genre> genres = new HashSet<>(Arrays.asList(Genre.Action));
    
    film1 = new Film("Inception", 2010, realisateur, acteurs, genres, 13);
    film2 = new Film("Tenet", 2020, realisateur, acteurs, genres, 13);
    film3 = new Film("Dunkirk", 2017, realisateur, acteurs, genres, 13);
    film4 = new Film("Interstellar", 2014, realisateur, acteurs, genres, 13);
  }
  
  /**
   * Teste la location de films.
   */
  @Test
  void testLouerFilm() throws LocationException {
    reservation.louerFilm(film1);
    reservation.louerFilm(film2);
    reservation.louerFilm(film3);
    
    assertEquals(3, reservation.getFilmsEnLocation().size());
    assertTrue(reservation.getFilmsEnLocation().contains(film1));
    assertTrue(reservation.getFilmsEnLocation().contains(film2));
    assertTrue(reservation.getFilmsEnLocation().contains(film3));
  }
  
  /**
   * Teste la limite de location.
   */
  @Test
  void testLouerFilmLimite() {
    assertThrows(LocationException.class, () -> {
      reservation.louerFilm(film1);
      reservation.louerFilm(film2);
      reservation.louerFilm(film3);
      reservation.louerFilm(film4);
    });
  }
  
  /**
   * Teste le retour d'un film.
   */
  @Test
  void testRetournerFilm() throws LocationException {
    reservation.louerFilm(film1);
    reservation.retournerFilm(film1);
    assertFalse(reservation.getFilmsEnLocation().contains(film1));
  }
  
  /**
   * Teste le retour d'un film non loué.
   */
  @Test
  void testRetournerFilmNonLoue() {
    assertThrows(LocationException.class,
        () -> reservation.retournerFilm(film1));
  }
  
  /**
   * Teste l'ajout d'une évaluation.
   */
  @Test
  void testAjouterEvaluation() throws LocationException {
    reservation.louerFilm(film1);
    Evaluation evaluation =
        new Evaluation("JohnDoe", film1, 5, "Excellent film");
    reservation.ajouterOuModifierEvaluation(film1, evaluation);
    
    assertEquals(1, reservation.getEvaluations().size());
    assertTrue(reservation.getEvaluations().contains(evaluation));
  }
  
  /**
   * Teste l'ajout d'une évaluation pour un film non loué.
   */
  @Test
  void testAjouterEvaluationNonLoue() {
    Evaluation evaluation =
        new Evaluation("JohnDoe", film1, 5, "Excellent film");
    assertThrows(LocationException.class,
        () -> reservation.ajouterOuModifierEvaluation(film1, evaluation));
  }
  
  /**
   * Teste la récupération des évaluations d'un film.
   */
  @Test
  void testEnsembleEvaluationsFilm() throws LocationException {
    reservation.louerFilm(film1);
    Evaluation evaluation =
        new Evaluation("JohnDoe", film1, 5, "Excellent film");
    reservation.ajouterOuModifierEvaluation(film1, evaluation);
    
    Set<Evaluation> evaluations = reservation.ensembleEvaluationsFilm(film1);
    assertEquals(1, evaluations.size());
    assertTrue(evaluations.contains(evaluation));
  }
  
  /**
   * Teste la récupération des évaluations d'un film par titre.
   */
  @Test
  void testEnsembleEvaluationsFilmParTitre() throws LocationException {
    reservation.louerFilm(film1);
    Evaluation evaluation =
        new Evaluation("JohnDoe", film1, 5, "Excellent film");
    reservation.ajouterOuModifierEvaluation(film1, evaluation);
    
    Set<Evaluation> evaluations =
        reservation.ensembleEvaluationsFilm("Inception"); // Test direct avec
                                                          // titre
    assertEquals(1, evaluations.size());
    assertTrue(evaluations.contains(evaluation));
  }
  
  /**
   * Teste la récupération des évaluations d'un film invalide .
   */
  @Test
  void testEnsembleEvaluationsFilmTitreInvalide() {
    // Passe directement un String et valide le comportement attendu
    Set<Evaluation> evaluations = reservation.ensembleEvaluationsFilm("");
    assertTrue(evaluations.isEmpty());
    
    evaluations = reservation.ensembleEvaluationsFilm((String) null);
    assertTrue(evaluations.isEmpty());
  }
  
  
}
