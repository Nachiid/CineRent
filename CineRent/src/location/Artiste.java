package location;

import java.io.Serializable;

/**
 * Représente un artiste (acteur ou réalisateur) avec ses informations
 * personnelles. Les artistes peuvent être soit des acteurs, soit des
 * réalisateurs.
 */
public class Artiste implements Serializable {
  
  private static final long serialVersionUID = 1297776707400850399L;
  
  // Attributs de l'artiste
  private String nom; // Le nom de l'artiste
  private String prenom; // Le prénom de l'artiste
  private String nationalite; // La nationalité de l'artiste
  private boolean estActeur; // Indique si l'artiste est un acteur (true) ou un
  // réalisateur (false)
  
  /**
   * Constructeur pour initialiser un artiste avec ses informations
   * personnelles. Par défaut, un artiste est considéré comme un acteur.
   *
   * @param nom Le nom de l'artiste.
   * @param prenom Le prénom de l'artiste.
   * @param nationalite La nationalité de l'artiste.
   * @throws IllegalArgumentException Si un des paramètres est null ou vide.
   */
  public Artiste(String nom, String prenom, String nationalite) {
    if (nom == null || nom.isEmpty()) {
      throw new IllegalArgumentException("Le nom ne peut pas être vide.");
    }
    if (prenom == null || prenom.isEmpty()) {
      throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
    }
    if (nationalite == null || nationalite.isEmpty()) {
      throw new IllegalArgumentException(
          "La nationalité ne peut pas être vide.");
    }
    this.nom = nom;
    this.prenom = prenom;
    this.nationalite = nationalite;
    this.estActeur = true; // Par défaut, un artiste est un acteur.
  }
  
  /**
   * Récupère le nom de l'artiste.
   *
   * @return Le nom de l'artiste.
   */
  public String getNom() {
    return nom;
  }
  
  /**
   * Modifie le nom de l'artiste.
   *
   * @param nom Le nouveau nom à définir.
   * @throws IllegalArgumentException Si le nom est vide ou null.
   */
  public void setNom(String nom) {
    if (nom == null || nom.isEmpty()) {
      throw new IllegalArgumentException("Le nom ne peut pas être vide.");
    }
    this.nom = nom;
  }
  
  /**
   * Récupère le prénom de l'artiste.
   *
   * @return Le prénom de l'artiste.
   */
  public String getPrenom() {
    return prenom;
  }
  
  /**
   * Modifie le prénom de l'artiste.
   *
   * @param prenom Le nouveau prénom à définir.
   * @throws IllegalArgumentException Si le prénom est vide ou null.
   */
  public void setPrenom(String prenom) {
    if (prenom == null || prenom.isEmpty()) {
      throw new IllegalArgumentException("Le prénom ne peut pas être vide.");
    }
    this.prenom = prenom;
  }
  
  /**
   * Vérifie si l'artiste est un réalisateur.
   *
   * @return {@code true} si l'artiste est un réalisateur, sinon {@code false}.
   */
  public boolean estRealisateur() {
    return !estActeur; // Un artiste est réalisateur s'il n'est pas acteur.
  }
  
  /**
   * Vérifie si l'artiste est un acteur.
   *
   * @return {@code true} si l'artiste est un acteur, sinon {@code false}.
   */
  public boolean estActeur() {
    return estActeur;
  }
  
  /**
   * Modifie le rôle de l'artiste (acteur ou réalisateur).
   *
   * @param estActeur {@code true} pour définir l'artiste comme acteur,
   *        {@code false} pour réalisateur.
   */
  public void setEstActeur(boolean estActeur) {
    this.estActeur = estActeur;
  }
  
  /**
   * Récupère la nationalité de l'artiste.
   *
   * @return La nationalité de l'artiste.
   */
  public String getNationalite() {
    return nationalite;
  }
  
  /**
   * Retourne une représentation sous forme de chaîne de caractères de
   * l'artiste. La chaîne inclut le nom complet de l'artiste et son rôle (Acteur
   * ou Réalisateur).
   *
   * @return Une chaîne descriptive de l'artiste.
   */
  @Override
  public String toString() {
    return prenom + " " + nom + (estActeur ? " (Acteur)" : " (Réalisateur)");
  }
}
