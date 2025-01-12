package location;

/**
 * Exception levée lors de problèmes liés à la location de films par un
 * utilisateur. Cette classe fournit des méthodes statiques pour créer des
 * instances spécifiques à différents scénarios d'erreur liés à la location.
 */
public class LocationException extends Exception {
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -3365565475174636290L;
  
  /**
   * Constructeur générique pour une exception avec un message personnalisé.
   *
   * @param message Le message décrivant l'exception.
   */
  public LocationException(String message) {
    super(message);
  }
  
  /**
   * Exception indiquant que l'utilisateur a atteint la limite maximale de films
   * loués.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException limiteAtteinte() {
    return new LocationException(
        "Vous avez atteint la limite maximale de films loués.");
  }
  
  /**
   * Exception indiquant que l'utilisateur n'a pas l'âge requis pour louer un
   * film.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException ageInsuffisant() {
    return new LocationException(
        "Vous n'avez pas l'âge suffisant pour louer ce film.");
  }
  
  /**
   * Exception indiquant que le film est déjà loué.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException filmDejaLoue() {
    return new LocationException("Ce film est déjà loué.");
  }
  
  /**
   * Exception indiquant que l'utilisateur tente de terminer la location d'un
   * film qu'il n'a pas loué.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException filmNonLoue() {
    return new LocationException(
        "Ce film n'est pas actuellement en location par l'utilisateur.");
  }
  
  /**
   * Exception indiquant que le film n'est pas disponible à la location.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException filmNonDisponible() {
    return new LocationException(
        "Ce film n'est pas disponible pour la location.");
  }
  
  /**
   * Exception indiquant que l'utilisateur tente d'ajouter une évaluation pour
   * un film qu'il n'a pas loué.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException evaluationNonAutorisee() {
    return new LocationException(
        "Vous ne pouvez pas ajouter une évaluation pour un film que vous n'avez pas loué.");
  }
  
  /**
   * Exception indiquant que l'utilisateur a déjà déposé une évaluation pour ce
   * film.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException evaluationDejaDeposee() {
    return new LocationException(
        "Vous avez déjà déposé une évaluation pour ce film.");
  }
  
  /**
   * Exception indiquant que l'utilisateur tente de modifier une évaluation
   * inexistante.
   *
   * @return Une instance de {@link LocationException}.
   */
  public static LocationException evaluationInexistante() {
    return new LocationException(
        "Vous ne pouvez modifier une évaluation qui n'existe pas.");
  }
}
