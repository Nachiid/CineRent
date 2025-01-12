package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Tests JUnit pour la classe {@link location.Evaluation Evaluation}.
 */
class TestEvaluation {
  
  private Evaluation evaluation;
  private Film film;
  private Artiste realisateur;
  private Artiste acteur1;
  
  /**
   * Initialisation avant chaque test.
   */
  @BeforeEach
  void setUp() {
    realisateur = new Artiste("Nolan", "Christopher", "Britannique");
    acteur1 = new Artiste("DiCaprio", "Leonardo", "Américain");
    List<Artiste> acteurs = Arrays.asList(acteur1);
    Set<Genre> genres =
        new HashSet<>(Arrays.asList(Genre.Action, Genre.ScienceFiction));
    film = new Film("Inception", 2010, realisateur, acteurs, genres, 13);
    
    evaluation = new Evaluation("JohnDoe", film, 4, "Très bon film.");
  }
  
  /**
   * Teste la création d'une évaluation.
   */
  @Test
  void testCreationEvaluation() {
    assertEquals("JohnDoe", evaluation.getPseudoUtilisateur());
    assertEquals(film, evaluation.getFilm());
    assertEquals(4, evaluation.getNote());
    assertEquals("Très bon film.", evaluation.getCommentaire());
  }
  
  /**
   * Teste la modification de la note.
   */
  @Test
  void testSetNote() {
    evaluation.setNote(5);
    assertEquals(5, evaluation.getNote());
    
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> evaluation.setNote(6));
    assertEquals("La note doit être entre 0 et 5.", exception.getMessage());
    
    exception = assertThrows(IllegalArgumentException.class,
        () -> evaluation.setNote(-1));
    assertEquals("La note doit être entre 0 et 5.", exception.getMessage());
  }
  
  /**
   * Teste la modification du commentaire.
   */
  @Test
  void testSetCommentaire() {
    evaluation.setCommentaire("Excellent film !");
    assertEquals("Excellent film !", evaluation.getCommentaire());
  }
  
  /**
   * Teste la méthode modifierEvaluation().
   */
  @Test
  void testModifierEvaluation() {
    evaluation.modifierEvaluation(3, "Film moyen.");
    assertEquals(3, evaluation.getNote());
    assertEquals("Film moyen.", evaluation.getCommentaire());
  }
  
  /**
   * Teste la méthode equals().
   */
  @Test
  void testEquals() {
    Evaluation autreEvaluation = new Evaluation("JohnDoe", film, 3, "Correct.");
    assertEquals(evaluation, autreEvaluation);
    
    Evaluation diffEvaluation = new Evaluation("JaneDoe", film, 4, "Bien.");
    assertNotEquals(evaluation, diffEvaluation);
  }
  
  /**
   * Teste la méthode hashCode().
   */
  @Test
  void testHashCode() {
    Evaluation autreEvaluation = new Evaluation("JohnDoe", film, 3, "Correct.");
    assertEquals(evaluation.hashCode(), autreEvaluation.hashCode());
  }
  
  /**
   * Teste la méthode toString().
   */
  @Test
  void testToString() {
    assertEquals("JohnDoe : 4/5 - Très bon film.", evaluation.toString());
    evaluation.setCommentaire(null);
    assertEquals("JohnDoe : 4/5 - Pas de commentaire", evaluation.toString());
  }
}
