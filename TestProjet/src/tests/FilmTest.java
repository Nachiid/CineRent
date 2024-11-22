package tests;

import static org.junit.jupiter.api.Assertions.*;

import location.Artiste;
import location.Film;
import location.Genre;
import location.Evaluation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests JUnit de la classe {@link location.Film Film}.
 *
 * @author 
 * @see location.Film
 */
class FilmTest {

    private Film filmBasique;
    private Film filmComplet;
    private Artiste realisateur;
    private Set<Artiste> acteurs;
    private Set<Genre> genres;

    /**
     * Prépare deux instances de films pour les tests.
     */
    @BeforeEach
    void setUp() throws Exception {
        realisateur = new Artiste("Nachid", "Ayman", "maroccainne", false);

        acteurs = new HashSet<>();
        acteurs.add(new Artiste("James", "Bond", "maroccainne", true));
        acteurs.add(new Artiste("Allen", "Karen", "maroccainne", true));

        genres = new HashSet<>();
        genres.add(Genre.Action);
        genres.add(Genre.Drame);

        filmBasique = new Film("Film Basique", 2003, realisateur, new HashSet<>(), new HashSet<>(), 18);
        filmComplet = new Film("Film Complet", 1964, realisateur, acteurs, genres, 12);
    }

    /**
     * Nettoyage après les tests.
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Vérifie que les paramètres des constructeurs sont correctement gérés pour le film complet.
     */
    @Test
    void testConstructeur() {
        assertEquals("Film Complet", filmComplet.getTitre());
        assertEquals(1964, filmComplet.getAnnee());
        assertEquals(realisateur, filmComplet.getRealisateur());
        assertEquals(acteurs, filmComplet.getActeurs());
        assertEquals(genres, filmComplet.getGenres());
        assertEquals(12, filmComplet.getAgeMinimum());
        assertEquals('f', filmComplet.getEtat());
    }

    /**
     * Test le setter de l'etat
     */
    @Test
    void testSetEtat() {
        filmBasique.setEtat('o');
        assertEquals('o', filmBasique.getEtat());

        filmBasique.setEtat('f');
        assertEquals('f', filmBasique.getEtat());
    }

    /**
     * Vérifie qu'on peut ajouter un acteur à un film.
     */
    @Test
    void testAddActeur() {
        Artiste nActeur = new Artiste("Freeman", "Morgan", "Américaine", true);
        filmBasique.addActeur(nActeur);
        assertTrue(filmBasique.getActeurs().contains(nActeur));
    }
    
    /**
     * Vérifie qu'un film sans acteurs ou genres est initialisé sans acteur ni genres comme attendu.
     */
    @Test
    void testFilmBasique() {
        assertTrue(filmBasique.getActeurs().isEmpty());
        assertTrue(filmBasique.getGenres().isEmpty());
    }

    /**
     * Vérifie qu'on peut ajouter un genre à un film.
     */
    @Test
    void testAddGenre() {
    	filmBasique.addGenre(Genre.Animation);
        filmBasique.addGenre(Genre.Biopic);
        assertTrue(filmBasique.getGenres().contains(Genre.Biopic));
    }

    /**
     * Vérifie la validation des évaluations pour un film.
     */
    @Test
    void testEvaluationFilm() {
        Evaluation evaluation = new Evaluation("Amine", 5, "Cool");
        
        assertEquals("Amine", evaluation.getPseudoUtilisateur());
        assertEquals(5, evaluation.getNote());
        assertEquals("Cool", evaluation.getCommentaire());

        // Test de setNote
        evaluation.setNote(4);
        assertEquals(4, evaluation.getNote());

        try {
            evaluation.setNote(6);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("La note doit être entre 0 et 5"));
        }
    }
}
