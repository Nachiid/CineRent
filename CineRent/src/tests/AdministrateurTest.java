package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Tests JUnit pour la classe {@link location.Administrateur Administrateur}.
 */
class TestAdministrateur {
  
  private Administrateur admin;
  private Artiste artiste;
  private Film film;
  
  /**
   * Initialisation des objets avant chaque test.
   */
  @BeforeEach
  void setUp() {
    admin = new Administrateur();
    artiste = admin.creerArtiste("Nolan", "Christopher", "Britannique");
    admin.setRealisateur(artiste);
    film = admin.creerFilm("Inception", artiste, 2010, 13);
  }
  
  /**
   * Teste la création d'un artiste.
   */
  @Test
  void testCreerArtiste() {
    assertNotNull(artiste);
    assertEquals("Nolan", artiste.getNom());
    assertEquals("Christopher", artiste.getPrenom());
    assertEquals("Britannique", artiste.getNationalite());
  }
  
  /**
   * Teste la suppression d'un artiste non utilisé.
   */
  @Test
  void testSupprimerArtiste() {
    Artiste artisteTemp = admin.creerArtiste("Smith", "Will", "Américain");
    assertTrue(admin.supprimerArtiste(artisteTemp));
  }
  
  /**
   * Teste la suppression d'un artiste utilisé dans un film.
   */
  @Test
  void testSupprimerArtisteUtilise() {
    assertFalse(admin.supprimerArtiste(artiste));
  }
  
  /**
   * Teste la création d'un film.
   */
  @Test
  void testCreerFilm() {
    assertNotNull(film);
    assertEquals("Inception", film.getTitre());
    assertEquals(2010, film.getAnnee());
  }
  
  /**
   * Teste l'ajout d'acteurs à un film.
   */
  @Test
  void testAjouterActeurs() {
    Artiste acteur = admin.creerArtiste("DiCaprio", "Leonardo", "Américain");
    assertTrue(admin.ajouterActeurs(film, acteur));
  }
  
  /**
   * Teste l'ajout de genres à un film.
   */
  @Test
  void testAjouterGenres() {
    assertTrue(admin.ajouterGenres(film, Genre.Action, Genre.ScienceFiction));
  }
  
  /**
   * Teste l'ajout d'une affiche à un film.
   */
  @Test
  void testAjouterAffiche() throws IOException {
    admin.ajouterAffiche(film, "affiche.jpg");
    assertEquals("affiche.jpg", film.getAffiche());
  }
  
  /**
   * Teste la suppression d'un film.
   */
  @Test
  void testSupprimerFilm() {
    assertTrue(admin.supprimerFilm(film));
  }
  
  /**
   * Teste la récupération de la liste des films.
   */
  @Test
  void testEnsembleFilms() {
    Set<Film> films = admin.ensembleFilms();
    assertFalse(films.isEmpty());
    assertTrue(films.contains(film));
  }
  
  /**
   * Teste la récupération de la liste des acteurs.
   */
  @Test
  void testEnsembleActeurs() {
    assertFalse(admin.ensembleActeurs().isEmpty());
  }
  
  /**
   * Teste la récupération de la liste des réalisateurs.
   */
  @Test
  void testEnsembleRealisateurs() {
    assertFalse(admin.ensembleRealisateurs().isEmpty());
  }
  
  /**
   * Teste la récupération des films d'un réalisateur.
   */
  @Test
  void testEnsembleFilmsRealisateur() {
    Set<Film> films = admin.ensembleFilmsRealisateur(artiste);
    assertFalse(films.isEmpty());
    assertTrue(films.contains(film));
  }
  
  /**
   * Teste la récupération des films d'un acteur.
   */
  @Test
  void testEnsembleFilmsActeur() {
    Artiste acteur = admin.creerArtiste("DiCaprio", "Leonardo", "Américain");
    admin.ajouterActeurs(film, acteur);
    Set<Film> films = admin.ensembleFilmsActeur(acteur);
    assertFalse(films.isEmpty());
  }
  
  /**
   * Teste l'ouverture et la fermeture de la location d'un film.
   */
  @Test
  void testOuvrirFermerLocation() {
    assertTrue(admin.ouvrirLocation(film));
    assertTrue(admin.fermerLocation(film));
  }
  
  /**
   * Teste la récupération d'un artiste.
   */
  @Test
  void testGetArtiste() {
    Artiste found = admin.getArtiste("Nolan", "Christopher");
    assertNotNull(found);
    assertEquals(artiste, found);
  }
  
  /**
   * Teste la récupération d'un film.
   */
  @Test
  void testGetFilm() {
    Film found = admin.getFilm("Inception");
    assertNotNull(found);
    assertEquals(film, found);
  }
  
  /**
   * Teste la méthode setAffiche et getAffiche.
   */
  @Test
  void testSetGetAffiche() {
    admin.setAffiche(film, "././image/film1.jpg");
    assertEquals("././image/film1.jpg", admin.getAffiche(film));
  }
  
  /**
   * Teste la méthode setRealisateur.
   */
  @Test
  void testSetRealisateur() {
    Artiste acteur = admin.creerArtiste("Hardy", "Tom", "Britannique");
    assertTrue(admin.setRealisateur(acteur));
    assertFalse(acteur.estActeur());
  }
  
  /**
   * Teste l'ajout d'un genre avec un film null.
   */
  @Test
  void testAjouterGenresFilmNull() {
    assertFalse(admin.ajouterGenres(null, Genre.Action));
  }
  
  /**
   * Teste la suppression d'un film inexistant.
   */
  @Test
  void testSupprimerFilmInexistant() {
    Film fakeFilm = new Film("Fake", 2022, artiste, null, null, 12);
    assertFalse(admin.supprimerFilm(fakeFilm));
  }
}
