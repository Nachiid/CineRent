package location;

import java.util.Set;

// A COMPLETER

import java.util.HashSet;

/**
 * Represents a film with associated metadata.
 */
public class Film {
    private String titre;
    private int annee;
    private int ageMinimum;
    private Artiste realisateur;
    private Set<Artiste> acteurs;
    private Set<Genre> genres; 
    private boolean ouvertALocation;
    private Set<Evaluation> evaluations;

    // Constructeurr
    public Film(String titre, int annee, Artiste realisateur, Set<Artiste> acteurs, Set<Genre> genres, int ageMinimum) {
        this.titre = titre;
        this.annee = annee;
        this.realisateur = realisateur;
        this.acteurs = new HashSet<>(acteurs);
        this.genres = new HashSet<>(genres);
        this.ageMinimum = ageMinimum;
        this.ouvertALocation = false; 
        this.evaluations = new HashSet<>();
    }

    // Getters and Setters
    public String getTitre() {  return titre; }

    public int getAnnee() {    return annee; }

    public Artiste getRealisateur() {   return realisateur;  }

    public Set<Artiste> getActeurs() {    return acteurs;  }

    public Set<Genre> getGenres() {  return genres;  }

    public int getAgeMinimum() {  return ageMinimum; }

    public boolean isOuvertALocation() {  return ouvertALocation; }


    public void setOuvertALocation(boolean ouvertALocation) {    this.ouvertALocation = ouvertALocation;  }

    // Ajouter un acteur 
    public void addActeur(Artiste acteur) {  acteurs.add(acteur); }

    // Ajouter un genre  
    public void addGenre(Genre genre) {  genres.add(genre); }

    // Ajouter une eval  
    public void addEvaluation(Evaluation evaluation) {   evaluations.add(evaluation); }
 
    // getter pour avoir toutes les eval 
    public Set<Evaluation> getEvaluations() {     return evaluations; }
    // Calcule la moyenne des evals 
    public double moyenneEvaluations() {
        return evaluations.stream().mapToInt(Evaluation::getNote).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "Titre: " + titre + ", Année: " + annee + ", Réalisateur: " + realisateur.getNom() +
               ", Genres: " + genres + ", Acteurs: " + acteurs;
    }
}

