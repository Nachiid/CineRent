package location;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant les fonctionnalités d'un administrateur
 * pour la gestion des artistes et des films.
 */
public class Administrateur implements InterAdministration , Serializable {
  private static final long serialVersionUID = 3879426052370515290L;

  private GestionArtistes gestionArtistes;
  private GestionFilms gestionFilms;
  private GestionLocationsADM gestionLocations;
  private Film dernierFilm;

  /**
     * Constructeur par défaut.
     * Initialise les gestionnaires d'artistes, de films et de locations.
     */
  public Administrateur() {
    this.gestionArtistes = new GestionArtistes();
    this.gestionFilms = new GestionFilms();
    this.gestionLocations = new GestionLocationsADM(gestionFilms);
  }

  @Override
    public Artiste creerArtiste(String nom, String prenom, String nationalite) {
    return gestionArtistes.creerArtiste(nom, prenom, nationalite);
  }
  
  public void setAffiche(Film film, String chemin) {
    gestionFilms.setAffiche( film, chemin);
  }
  
  public String getAffiche(Film film) {
    return gestionFilms.getAffiche(film);
 }

  @Override
    public boolean supprimerArtiste(Artiste artiste) {
    // Vérifie si l'artiste est utilisé dans un film (acteur ou réalisateur)
    Set<Film> filmsCommeRealisateur = ensembleFilmsRealisateur(artiste);
    Set<Film> filmsCommeActeur = ensembleFilmsActeur(artiste);
    if ((filmsCommeRealisateur != null && !filmsCommeRealisateur.isEmpty())
                || (filmsCommeActeur != null && !filmsCommeActeur.isEmpty())) {
      return false;
    }
    return gestionArtistes.supprimerArtiste(artiste.getNom(), artiste.getPrenom());
  }

  @Override
    public Film creerFilm(String titre, Artiste realisateur, int annee, int ageLimite) {
    return dernierFilm = gestionFilms.creerFilm(titre, realisateur, annee, ageLimite);
  }

  public Film getDernierFilm() {
    return dernierFilm;
  }
  @Override
    public boolean ajouterActeurs(Film film, Artiste... acteurs) {
    return gestionFilms.ajouterActeurs(film, acteurs);
  }


    public boolean setRealisateur(Artiste acteurs) {
  return gestionArtistes.setRealisateur(acteurs);
}

  @Override
  public boolean ajouterGenres(Film film, Genre... genres) {
    if (film == null || genres == null) {
      return false;
    }
    boolean added = false;
    for (Genre genre : genres) {
      added = gestionFilms.ajouterGenres(film, genre);
    }
    return added;
  }

  @Override
  public boolean ajouterAffiche(Film film, String file) throws IOException {
    if (film == null || file == null || file.isEmpty()) {
      return false;
    }
    film.setAffiche(file);
    return true;
  }

  @Override
  public boolean supprimerFilm(Film film) {
    if (film == null) {
      return false;
    }
    return gestionFilms.supprimerFilm(film.getTitre());
  }

  @Override
  public Set<Film> ensembleFilms() {
    // Récupérer toutes les valeurs (les films) de la Map et les mettre dans un Set
    return new HashSet<>(gestionFilms.getAllFilms().values());
  }

  @Override
  public Set<Artiste> ensembleActeurs() {
      Set<Artiste> acteurs = new HashSet<>();
      for (Artiste artiste : gestionArtistes.getArtistes().values()) {
          if (artiste.estActeur()) {
              acteurs.add(artiste);
          }
      }
      return acteurs;
  }
  @Override
  public Set<Artiste> ensembleRealisateurs() {
      Set<Artiste> realisateurs = new HashSet<>();
      for (Artiste artiste : gestionArtistes.getArtistes().values()) {
          if (!artiste.estActeur()) { // Si l'artiste n'est pas un acteur, il est réalisateur
              realisateurs.add(artiste);
          }
      }
      return realisateurs;
  }

  @Override
  public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
    if (realisateur == null) {
      return null;
    }
    Set<Film> films = new HashSet<>();
    for (Film film : ensembleFilms()) {
      if (film.getRealisateur().equals(realisateur)) {
        films.add(film);
      }
    }
    return films;
  }

  @Override
  public Set<Film> ensembleFilmsActeur(Artiste acteur) {
    if (acteur == null) {
      return null;
    }
    Set<Film> films = new HashSet<>();
    for (Film film : ensembleFilms()) {
      if (film.getActeurs().contains(acteur)) {
        films.add(film);
      }
    }
    return films;
  }

  @Override
  public Artiste getArtiste(String nom, String prenom) {
    return gestionArtistes.getArtiste(nom, prenom);
  }

  @Override
  public Film getFilm(String titre) {
    return gestionFilms.getFilm(titre);
  }

  @Override
  public boolean ouvrirLocation(Film film) {
    return gestionLocations.ouvrirLocation(film);
  }

  @Override
  public boolean fermerLocation(Film film) {
    return gestionLocations.fermerLocation(film);
  }

}
