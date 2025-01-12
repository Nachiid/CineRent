package location;

import java.io.Serializable;

/**
 * Classe de gestion des locations pour les films administrés. Fournit des
 * fonctionnalités pour ouvrir ou fermer la location de films.
 */
public class GestionLocationsADM implements Serializable {
  
  private static final long serialVersionUID = 1885021504747705541L;
  
  // Instance de GestionFilms utilisée pour accéder et gérer les films.
  @SuppressWarnings("unused")
  private GestionFilms gestionFilms;
  
  /**
   * Constructeur de la classe GestionLocationsADM. Permet d'initialiser la
   * gestion des locations avec une instance existante de GestionFilms.
   *
   * @param gestionFilms Instance de GestionFilms à utiliser pour accéder aux
   *        films.
   */
  public GestionLocationsADM(GestionFilms gestionFilms) {
    this.gestionFilms = gestionFilms;
  }
  
  /**
   * Ouvre la location d'un film. Change l'état du film pour qu'il soit
   * disponible à la location.
   *
   * @param film Le film à ouvrir à la location.
   * @return <code>true</code> si l'opération réussit, sinon <code>false</code>
   *         si le film est null.
   */
  public boolean ouvrirLocation(Film film) {
    if (film == null) {
      return false; // Impossible d'ouvrir la location si le film est null
    }
    film.changerLocation(true); // Définit l'état de location à true
    return true;
  }
  
  /**
   * Ferme la location d'un film. Change l'état du film pour qu'il ne soit plus
   * disponible à la location.
   *
   * @param film Le film dont il faut fermer la location.
   * @return <code>true</code> si l'opération réussit, sinon <code>false</code>
   *         si le film est null.
   */
  public boolean fermerLocation(Film film) {
    if (film == null) {
      return false; // Impossible de fermer la location si le film est null
    }
    film.changerLocation(false); // Définit l'état de location à false
    return true;
  }
}
