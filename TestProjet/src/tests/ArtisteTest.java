package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.Artiste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests JUnit de la classe {@link location.Artiste Artiste}.
 *
 * @author NACHID AYMAN
 * @see location.Artiste
 */
class ArtisteTest {

    private Artiste acteur;
    private Artiste realisateur;

    /**
     * Méthode exécutée avant chaque test pour préparer les données nécessaires.
     */
    @BeforeEach
    void setUp() {
        // Création de l'acteur et du réalisateur pour les tests
        acteur = new Artiste("Depp", "Johnny", "Américain", true);
        realisateur = new Artiste("P", "Diddy", "Américain", false);
    } 

    /**
     * Vérifie que l'acteur est bien un acteur.
     */
    @Test
    void testEstActeur() {
        assertTrue(acteur.getEstActeur());
    }

    /**
     * Vérifie que le réalisateur n'est pas un acteur.
     */
    @Test
    void testEstRealisateur() {
        assertTrue(realisateur.estRealisateur());
    }

    /**
     * Vérifie le bon fonctionnement du constructeur et des getters.
     */
    @Test
    void testConstructeurEtGetters() {
        assertEquals("Depp", acteur.getNom());
        assertEquals("Johnny", acteur.getPrenom());
        assertEquals("Américain", acteur.getNationalite());
    }

    /**
     * Vérifie que la méthode toString fonctionne correctement.
     */
    @Test
    void testToString() {
        // Test de l'acteur
        String Personne = "Johnny Depp (Acteur)";
        assertEquals(Personne, acteur.toString());

        // Test du réalisateur
        Personne = "P Diddy (Realisateur)";
        assertEquals(Personne, realisateur.toString());
    }

    /**
     * Vérifie que la méthode setNom fonctionne correctement.
     */
    @Test
    void testSetNom() {
        acteur.setNom("Jeffrey");
        assertEquals("Jeffrey", acteur.getNom());
    }

    /**
     * Vérifie que la méthode setPrenom fonctionne correctement.
     */
    @Test
    void testSetPrenom() {
        acteur.setPrenom("Dahmer");
        assertEquals("Dahmer", acteur.getPrenom());
    }
}
