package location;

import java.io.Serializable;
import java.util.Objects;

/**
 * Représente une évaluation d'un film par un utilisateur. Chaque évaluation est
 * caractérisée par le pseudo de l'utilisateur, le film évalué, une note
 * attribuée (entre 0 et 5), et un commentaire optionnel.
 */
public class Evaluation implements Serializable {
  
  private static final long serialVersionUID = 1297776707400850399L;
  
  private String pseudoUtilisateur; // Le pseudo de l'utilisateur ayant fait
  // l'évaluation
  private Film film; // Le film évalué
  private int note; // La note donnée au film (doit être entre 0 et 5)
  private String commentaire; // Le commentaire associé à l'évaluation
  
  /**
   * Constructeur pour créer une évaluation d'un film.
   *
   * @param pseudoUtilisateur Le pseudo de l'utilisateur ayant évalué le film.
   * @param film Le film évalué.
   * @param note La note attribuée au film (entre 0 et 5).
   * @param commentaire Un commentaire associé à l'évaluation.
   * @throws IllegalArgumentException Si la note n'est pas entre 0 et 5.
   */
  public Evaluation(String pseudoUtilisateur, Film film, int note,
      String commentaire) {
    this.pseudoUtilisateur = pseudoUtilisateur;
    setNote(note); // Utilise le setter pour valider la note
    this.commentaire = commentaire;
    this.film = film;
  }
  
  // ==== Getters et Setters ====
  
  /**
   * Récupère le pseudo de l'utilisateur ayant fait l'évaluation.
   *
   * @return Le pseudo de l'utilisateur.
   */
  public String getPseudoUtilisateur() {
    return pseudoUtilisateur;
  }
  
  /**
   * Modifie le pseudo de l'utilisateur.
   *
   * @param pseudoUtilisateur Le nouveau pseudo de l'utilisateur.
   */
  public void setPseudoUtilisateur(String pseudoUtilisateur) {
    this.pseudoUtilisateur = pseudoUtilisateur;
  }
  
  /**
   * Récupère la note attribuée au film.
   *
   * @return La note de l'évaluation (entre 0 et 5).
   */
  public int getNote() {
    return note;
  }
  
  /**
   * Modifie la note de l'évaluation. La note doit être comprise entre 0 et 5.
   *
   * @param note La nouvelle note à attribuer à l'évaluation.
   * @throws IllegalArgumentException Si la note n'est pas entre 0 et 5.
   */
  public void setNote(int note) {
    if (note < 0 || note > 5) {
      throw new IllegalArgumentException("La note doit être entre 0 et 5.");
    }
    this.note = note;
  }
  
  /**
   * Récupère le film évalué.
   *
   * @return Le film associé à cette évaluation.
   */
  public Film getFilm() {
    return film;
  }
  
  /**
   * Récupère le commentaire associé à l'évaluation.
   *
   * @return Le commentaire de l'utilisateur sur le film.
   */
  public String getCommentaire() {
    return commentaire;
  }
  
  /**
   * Modifie le commentaire associé à l'évaluation.
   *
   * @param commentaire Le nouveau commentaire.
   */
  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }
  
  /**
   * Modifie à la fois la note et le commentaire de l'évaluation.
   *
   * @param note La nouvelle note à attribuer.
   * @param commentaire Le nouveau commentaire.
   * @throws IllegalArgumentException Si la note n'est pas entre 0 et 5.
   */
  public void modifierEvaluation(int note, String commentaire) {
    setNote(note);
    this.commentaire = commentaire;
  }
  
  // ==== Méthodes redéfinies ====
  
  /**
   * Compare deux évaluations pour vérifier leur égalité. Deux évaluations sont
   * considérées égales si elles concernent le même utilisateur (identifié par
   * son pseudo) et le même film.
   *
   * @param obj L'objet à comparer.
   * @return {@code true} si les deux évaluations sont égales, sinon
   *         {@code false}.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true; // Les objets sont identiques
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false; // L'objet comparé est null ou d'une classe différente
    }
    Evaluation other = (Evaluation) obj;
    return Objects.equals(this.pseudoUtilisateur, other.pseudoUtilisateur)
        && Objects.equals(this.film, other.film);
  }
  
  /**
   * Calcule le code de hachage de l'évaluation. Le code est basé sur le pseudo
   * de l'utilisateur et le film évalué.
   *
   * @return Le code de hachage de l'évaluation.
   */
  @Override
  public int hashCode() {
    return Objects.hash(pseudoUtilisateur, film);
  }
  
  /**
   * Retourne une représentation sous forme de chaîne de caractères de
   * l'évaluation. La chaîne inclut le pseudo, la note, et le commentaire. Si
   * aucun commentaire n'est présent, la mention "Pas de commentaire" est
   * affichée.
   *
   * @return Une chaîne descriptive de l'évaluation.
   */
  @Override
  public String toString() {
    return pseudoUtilisateur + " : " + note + "/5 - "
        + (commentaire != null ? commentaire : "Pas de commentaire");
  }
}
