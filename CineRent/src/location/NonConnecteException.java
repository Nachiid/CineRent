package location;

/**
 * Exception levée lorsqu'il y a un problème lié à l'état de connexion d'un
 * utilisateur. Cette classe fournit des méthodes statiques pour signaler
 * différentes situations d'erreur, comme l'absence de connexion, les tentatives
 * d'opérations nécessitant une connexion ou la déconnexion d'un utilisateur non
 * connecté.
 */
public class NonConnecteException extends Exception {
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -2876441299971092712L;
  
  /**
   * Constructeur générique pour une exception avec un message personnalisé.
   *
   * @param message Le message décrivant l'exception.
   */
  public NonConnecteException(String message) {
    super(message);
  }
  
  /**
   * Exception indiquant qu'aucun utilisateur n'est actuellement connecté.
   *
   * @return Une instance de {@link NonConnecteException}.
   */
  public static NonConnecteException utilisateurNonConnecte() {
    return new NonConnecteException("Aucun utilisateur n'est connecté.");
  }
  
  /**
   * Exception indiquant qu'il n'y a aucun utilisateur à déconnecter.
   *
   * @return Une instance de {@link NonConnecteException}.
   */
  public static NonConnecteException aucunUtilisateuraDeconnecter() {
    return new NonConnecteException(
        "Impossible de déconnecter : Aucun utilisateur n'est actuellement connecté.");
  }
  
  /**
   * Exception indiquant qu'une connexion est requise pour louer un film.
   *
   * @return Une instance de {@link NonConnecteException}.
   */
  public static NonConnecteException connexionRequisePourLouer() {
    return new NonConnecteException(
        "Vous devez être connecté pour louer un film.");
  }
  
  /**
   * Exception indiquant qu'une connexion est requise pour accéder aux films en
   * location.
   *
   * @return Une instance de {@link NonConnecteException}.
   */
  public static NonConnecteException connexionRequisePourFilmsEnLocation() {
    return new NonConnecteException(
        "Vous devez être connecté pour consulter vos films en location.");
  }
  
  /**
   * Exception indiquant qu'une connexion est requise pour évaluer un film.
   *
   * @return Une instance de {@link NonConnecteException}.
   */
  public static NonConnecteException connexionRequisePourEvaluer() {
    return new NonConnecteException(
        "Vous devez être connecté pour évaluer un film.");
  }
}
