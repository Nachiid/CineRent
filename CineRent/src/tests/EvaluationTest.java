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
 * Tests JUnit de la classe {@link location.Evaluation Evaluation}.
 *
 * @author NACHID AYMAN
 * @see location.Evaluation
 */
class EvaluationTest{

    private Evaluation evaluationValide;
    private Evaluation evaluationSansCommentaire;
    private Evaluation evaluationNoteInvalide;

    /**
      * Creation des evaluation pour les tests 
     */
    @BeforeEach
    void setUp() {
    	evaluationValide = new Evaluation("Aymen", 4, "Film excellent !");
        evaluationSansCommentaire = new Evaluation("laila", 3, null);
        evaluationNoteInvalide = new Evaluation("aziz", 6, "Film moyen");
    }

    /**
     * Vérifie que la note est bien définie dans l'évaluation valide.
     */
    @Test 
    void testGetNote() {
        assertEquals(4, evaluationValide.getNote());
    }

    
    
    
    
    
    
}
