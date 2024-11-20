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
    private char etat;//'o' pour ouvert et 'F' pour fermé 
    private Artiste realisateur;
    private Set<Artiste> acteurs;
    private Set<Genre> genres; 
 
    // Constructeur
    public Film(String titre, int annee, Artiste realisateur, Set<Artiste> acteurs, Set<Genre> genres, int ageMinimum) {
        this.titre = titre;
        this.annee = annee;
        this.realisateur = realisateur;
        this.acteurs = new HashSet<>(acteurs);
        this.genres = new HashSet<>(genres);
        this.ageMinimum = ageMinimum;
        this.etat='f';
    }

    // Getters and Setters
    public String getTitre() {  return titre; }

    public int getAnnee() {  return annee; }
    
    public char getEtat() {  return etat; }
    
    public void setEtat(char etat) {
        this.etat = etat;
    }

    public Artiste getRealisateur() { return realisateur;  }

    public Set<Artiste> getActeurs() {  return acteurs;  }

    public Set<Genre> getGenres() {  return genres; }

    public int getAgeMinimum() {  return ageMinimum; }


    // Ajouter un acteur 
    public void addActeur(Artiste acteur) {  acteurs.add(acteur); }

    // Ajouter un genre  
    public void addGenre(Genre genre) {  genres.add(genre); }



    @Override
    public String toString() {
        return "Titre: " + titre + ", Année: " + annee + ", Réalisateur: " + realisateur.getNom() +
               ", Genres: " + genres + ", Acteurs: " + acteurs;
    }

}

