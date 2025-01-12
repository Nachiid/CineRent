package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.Artiste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit pour la classe {@link location.Artiste Artiste}.
 */
class TestArtiste {
  
  private Artiste artiste;
  
  /**
   * Initialise un artiste pour les tests.
   *
   * @throws Exception si une erreur survient pendant l'initialisation
   */
  @BeforeEach
  void setUp() throws Exception {
    artiste = new Artiste("Doe", "John", "Américaine");
  }
  
  /**
   * Teste la méthode getNom().
   */
  @Test
  void testGetNom() {
    assertEquals("Doe", artiste.getNom());
  }
  
  /**
   * Teste la méthode setNom().
   */
  @Test
  void testSetNom() {
    artiste.setNom("Smith");
    assertEquals("Smith", artiste.getNom());
    
    // Vérifie l'exception si nom est null
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> artiste.setNom(null));
    assertEquals("Le nom ne peut pas être vide.", exception.getMessage());
    
    // Vérifie l'exception si nom est vide
    exception =
        assertThrows(IllegalArgumentException.class, () -> artiste.setNom(""));
    assertEquals("Le nom ne peut pas être vide.", exception.getMessage());
  }
  
  /**
   * Teste la méthode getPrenom().
   */
  @Test
  void testGetPrenom() {
    assertEquals("John", artiste.getPrenom());
  }
  
  /**
   * Teste la méthode setPrenom().
   */
  @Test
  void testSetPrenom() {
    artiste.setPrenom("James");
    assertEquals("James", artiste.getPrenom());
    
    // Vérifie l'exception si prénom est null
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> artiste.setPrenom(null));
    assertEquals("Le prénom ne peut pas être vide.", exception.getMessage());
    
    // Vérifie l'exception si prénom est vide
    exception = assertThrows(IllegalArgumentException.class,
        () -> artiste.setPrenom(""));
    assertEquals("Le prénom ne peut pas être vide.", exception.getMessage());
  }
  
  /**
   * Teste la méthode estActeur().
   */
  @Test
  void testEstActeur() {
    assertTrue(artiste.estActeur());
    artiste.setEstActeur(false);
    assertFalse(artiste.estActeur());
  }
  
  /**
   * Teste la méthode estRealisateur().
   */
  @Test
  void testEstRealisateur() {
    assertFalse(artiste.estRealisateur());
    artiste.setEstActeur(false);
    assertTrue(artiste.estRealisateur());
  }
  
  /**
   * Teste la méthode getNationalite().
   */
  @Test
  void testGetNationalite() {
    assertEquals("Américaine", artiste.getNationalite());
  }
  
  /**
   * Teste la méthode toString().
   */
  @Test
  void testToString() {
    assertEquals("John Doe(Acteur)", artiste.toString());
    artiste.setEstActeur(false);
    assertEquals("John Doe (Realisateur)", artiste.toString());
  }
}
