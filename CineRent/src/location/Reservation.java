package location;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe `Reservation` pour gérer la location des films et les évaluations.
 * Cette classe permet de :
 * <ul>
 * <li>Gérer les films actuellement loués</li>
 * <li>Maintenir un historique des films précédemment loués</li>
 * <li>Ajouter ou modifier des évaluations pour les films</li>
 * </ul>
 */
public class Reservation implements Serializable {
  
  private static final long serialVersionUID = 1114306008546111716L;
  
  /**
   * Ensemble des films actuellement en location.
   */
  private Set<Film> filmsEnLocation;
  
  /**
   * Ensemble des films précédemment loués (historique).
   */
  private Set<Film> historiqueFilm;
  
  /**
   * Ensemble des évaluations des films.
   */
  private Set<Evaluation> evaluations;
  
  /**
   * Le nombre maximum de films pouvant être loués simultanément.
   */
  private static final int MAX_LOC = 3;
  
  /**
   * Constructeur par défaut de la classe `Reservation`. Initialise les
   * ensembles pour les films et les évaluations.
   */
  public Reservation() {
    this.filmsEnLocation = new HashSet<>();
    this.historiqueFilm = new HashSet<>();
    this.evaluations = new HashSet<>();
  }
  
  /**
   * Retourne l'ensemble des films actuellement en location.
   *
   * @return L'ensemble des films en location.
   */
  public Set<Film> getFilmsEnLocation() {
    return filmsEnLocation;
  }
  
  /**
   * Retourne l'ensemble des évaluations des films.
   *
   * @return L'ensemble des évaluations.
   */
  public Set<Evaluation> getEvaluations() {
    return evaluations;
  }
  
  /**
   * Loue un film s'il n'a pas dépassé la limite de location autorisée.
   *
   * @param film Le film à louer.
   * @throws LocationException Si la limite de location est atteinte.
   */
  public void louerFilm(Film film) throws LocationException {
    if (filmsEnLocation.size() >= MAX_LOC) {
      throw LocationException.limiteAtteinte();
    }
    filmsEnLocation.add(film); // Ajoute le film à la liste des films en
    // location
    historiqueFilm.add(film); // Ajoute le film à l'historique
  }
  
  /**
   * Retourne un film loué.
   *
   * @param film Le film à retourner.
   * @throws LocationException Si le film n'est pas dans la liste des films
   *         loués.
   */
  public void retournerFilm(Film film) throws LocationException {
    if (!filmsEnLocation.contains(film)) {
      throw LocationException.filmNonLoue();
    }
    filmsEnLocation.remove(film); // Supprime le film de la liste des films en
    // location
  }
  
  /**
   * Ajoute ou modifie une évaluation pour un film donné.
   *
   * @param film Le film à évaluer.
   * @param eval L'évaluation à ajouter ou modifier.
   * @throws LocationException Si le film n'est pas dans la liste des films
   *         loués ou dans l'historique.
   */
  public void ajouterOuModifierEvaluation(Film film, Evaluation eval)
      throws LocationException {
    if (!filmsEnLocation.contains(film) && !historiqueFilm.contains(film)) {
      throw LocationException.evaluationNonAutorisee();
    }
    evaluations.removeIf(e -> e.getFilm().equals(film)); // Supprime toute
    // évaluation existante
    // pour ce film
    evaluations.add(eval); // Ajoute la nouvelle évaluation
  }
  
  /**
   * Retourne l'ensemble des évaluations pour un film donné.
   *
   * @param film Le film pour lequel récupérer les évaluations.
   * @return Un ensemble des évaluations associées au film ou null si le film
   *         est null.
   */
  public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
    if (film == null) {
      return null;
    }
    return evaluations.stream().filter(e -> e.getFilm().equals(film))
        .collect(Collectors.toSet());
  }
  
  /**
   * Retourne l'ensemble des évaluations pour un film donné par son titre.
   *
   * @param titre Le titre du film.
   * @return Un ensemble des évaluations associées au film ou un ensemble vide
   *         si aucune correspondance.
   */
  public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
    if (titre == null || titre.trim().isEmpty()) {
      return Collections.emptySet(); // Retourne un ensemble vide pour un titre
      // invalide
    }
    return evaluations.stream()
        .filter(e -> e.getFilm().getTitre().equalsIgnoreCase(titre))
        .collect(Collectors.toSet());
  }
  
  /**
   * Retourne l'ensemble des films actuellement en location.
   *
   * @return L'ensemble des films loués.
   */
  Set<Film> getFilm() {
    return this.filmsEnLocation;
  }
}
