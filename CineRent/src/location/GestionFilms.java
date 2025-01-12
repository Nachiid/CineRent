package location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Classe pour gérer les films. Fournit des fonctionnalités pour créer,
 * rechercher, ajouter des genres, des acteurs, et supprimer des films.
 */
public class GestionFilms implements Serializable {
  
  private static final long serialVersionUID = 2673884284150160031L;
  
  // Map pour stocker les films, identifiés par leur titre unique.
  private Map<String, Film> films;
  
  // Instance unique (Singleton)
  private static GestionFilms instance = null;
  
  /**
   * Constructeur par défaut. Initialise la collection de films sous forme de
   * HashMap.
   */
  public GestionFilms() {
    this.films = new HashMap<>();
  }
  
  /**
   * Méthode pour récupérer l'instance unique (Singleton) de GestionFilms. Si
   * l'instance n'existe pas encore, elle est créée.
   *
   * @return L'instance unique de GestionFilms.
   */
  public static GestionFilms getInstance() {
    if (instance == null) {
      instance = new GestionFilms();
    }
    return instance;
  }
  
  /**
   * Retourne tous les films sous forme de map. Fournit une copie de la map pour
   * éviter toute modification directe.
   *
   * @return Une copie de la map contenant tous les films.
   */
  public Map<String, Film> getAllFilms() {
    return new HashMap<>(films);
  }
  
  /**
   * Crée un nouveau film et l'ajoute à la collection. Valide les données
   * d'entrée pour s'assurer de leur conformité.
   *
   * @param titre Le titre du film.
   * @param realisateur Le réalisateur du film.
   * @param annee L'année de sortie du film (doit être >= 1895).
   * @param ageLimite L'âge minimum requis pour voir le film (doit être >= 0).
   * @return Le film créé, ou null si les données sont invalides ou si un film
   *         avec le même titre existe déjà.
   */
  public Film creerFilm(String titre, Artiste realisateur, int annee,
      int ageLimite) {
    String key = titre; // Utilise le titre comme clé unique
    
    // Vérifie si le film existe déjà
    if (films.containsKey(key)) {
      return null;
    }
    
    // Valide les données d'entrée
    if (ageLimite < 0 || annee < 1895 || titre == null || realisateur == null) {
      return null;
    }
    
    // Création du film
    Film film = new Film(titre, annee, realisateur, new ArrayList<>(), // Liste
        // vide
        // pour
        // les
        // acteurs
        new HashSet<>(), // Set vide pour les genres
        ageLimite // Limite d'âge
    );
    
    // Ajoute le film dans la collection
    films.put(key, film);
    return film;
  }
  
  /**
   * Recherche un film par son titre.
   *
   * @param titre Le titre du film à rechercher.
   * @return Le film correspondant au titre, ou null s'il n'existe pas.
   */
  public Film getFilm(String titre) {
    return films.get(titre);
  }
  
  /**
   * Définit l'affiche d'un film.
   *
   * @param film Le film pour lequel définir l'affiche.
   * @param chemin Le chemin ou l'URL de l'affiche.
   */
  public void setAffiche(Film film, String chemin) {
    if (film != null && chemin != null) {
      getFilm(film.getTitre()).setAffiche(chemin);
    }
  }
  
  /**
   * Récupère l'affiche d'un film.
   *
   * @param film Le film pour lequel récupérer l'affiche.
   * @return Le chemin ou l'URL de l'affiche du film.
   */
  public String getAffiche(Film film) {
    if (film != null) {
      return getFilm(film.getTitre()).getAffiche();
    }
    return null;
  }
  
  /**
   * Ajoute des genres à un film.
   *
   * @param film Le film auquel ajouter des genres.
   * @param genre Le genre à ajouter.
   * @return true si le genre a été ajouté avec succès, sinon false.
   */
  public boolean ajouterGenres(Film film, Genre genre) {
    if (film != null && genre != null) {
      return film.addGenre(genre);
    }
    return false;
  }
  
  /**
   * Ajoute des acteurs à un film.
   *
   * @param film Le film auquel ajouter des acteurs.
   * @param acteurs Les acteurs à ajouter.
   * @return true si au moins un acteur a été ajouté, sinon false.
   */
  public boolean ajouterActeurs(Film film, Artiste... acteurs) {
    if (film == null || acteurs == null || acteurs.length == 0) {
      return false;
    }
    
    boolean added = false;
    for (Artiste acteur : acteurs) {
      if (film.addActeur(acteur)) {
        added = true; // Indique qu'au moins un acteur a été ajouté
      }
    }
    return added;
  }
  
  /**
   * Supprime un film de la collection par son titre.
   *
   * @param titre Le titre du film à supprimer.
   * @return true si le film a été supprimé avec succès, sinon false.
   */
  public boolean supprimerFilm(String titre) {
    if (titre == null || !films.containsKey(titre)) {
      return false;
    }
    films.remove(titre);
    return true;
  }
}
