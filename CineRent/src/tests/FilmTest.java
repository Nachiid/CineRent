package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Tests JUnit pour la classe {@link location.Film Film}.
 */
class TestFilm {
  
  private Film film;
  private Artiste realisateur;
  private Artiste acteur1;
  private Artiste acteur2;
  
  /**
   * Initialisation avant chaque test.
   */
  @BeforeEach
  void setUp() {
    realisateur = new Artiste("Nolan", "Christopher", "Britannique");
    acteur1 = new Artiste("DiCaprio", "Leonardo", "Américain");
    acteur2 = new Artiste("Page", "Elliot", "Canadien");
    List<Artiste> acteurs = Arrays.asList(acteur1, acteur2);
    Set<Genre> genres =
        new HashSet<>(Arrays.asList(Genre.Action, Genre.ScienceFiction));
    film = new Film("Inception", 2010, realisateur, acteurs, genres, 13);
  }
  
  /**
   * Teste la création d'un film.
   */
  @Test
  void testCreationFilm() {
    assertEquals("Inception", film.getTitre());
    assertEquals(2010, film.getAnnee());
    assertEquals(realisateur, film.getRealisateur());
    assertEquals(2, film.getActeurs().size());
    assertEquals(2, film.getGenres().size());
    assertEquals(13, film.getAgeMinimum());
  }
  
  /**
   * Teste la gestion des genres.
   */
  @Test
  void testGestionGenres() {
    film.addGenre(Genre.Drame);
    assertTrue(film.getGenres().contains(Genre.Drame));
  }
  
  /**
   * Teste la gestion des acteurs.
   */
  @Test
  void testGestionActeurs() {
    Artiste nouvelActeur = new Artiste("Hardy", "Tom", "Britannique");
    film.addActeur(nouvelActeur);
    assertTrue(film.getActeurs().contains(nouvelActeur));
  }
  
  /**
   * Teste l'ajout d'une affiche.
   */
  @Test
  void testAffiche() {
    film.setAffiche("inception.jpg");
    assertEquals("inception.jpg", film.getAffiche());
  }
  
  /**
   * Teste la modification de l'âge minimum.
   */
  @Test
  void testAgeMinimum() {
    film.setAgeMinimum(16);
    assertEquals(16, film.getAgeMinimum());
  }
  
  /**
   * Teste la gestion de la location.
   */
  @Test
  void testLocation() {
    assertFalse(film.isEnLocation());
    film.changerLocation(true);
    assertTrue(film.isEnLocation());
  }
  
  /**
   * Teste l'égalité entre deux films.
   */
  @Test
  void testEquals() {
    Film autreFilm = new Film("Inception", 2010, realisateur, new ArrayList<>(),
        new HashSet<>(), 13);
    assertEquals(film, autreFilm);
    
    Film filmDifferent = new Film("Tenet", 2020, realisateur, new ArrayList<>(),
        new HashSet<>(), 13);
    assertNotEquals(film, filmDifferent);
  }
  
  /**
   * Teste la méthode toString().
   */
  @Test
  void testToString() {
    String expected =
        "Titre: Inception, Année: 2010, Réalisateur: Christopher Nolan(Acteur), Genres: [ACTION, SCIENCE_FICTION], Acteurs: [Leonardo DiCaprio(Acteur), Elliot Page(Acteur)]";
    assertTrue(film.toString().contains("Inception"));
    assertTrue(film.toString().contains("Christopher Nolan"));
    assertTrue(film.toString().contains("Leonardo DiCaprio"));
  }
}
