package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un film avec des métadonnées associées telles que le titre,
 * l'année de sortie, le réalisateur, les acteurs, les genres, et les
 * restrictions d'âge. Cette classe gère également la disponibilité du film en
 * location et son affiche.
 */
public class Film implements Serializable {
  private static final long serialVersionUID = 123456789L;
  
  // ==== Attributs ====
  private String titre; // Titre du film
  private String affiche; // Affiche du film (URL ou nom du fichier)
  private int annee; // Année de sortie du film
  private int ageMinimum; // Âge minimum recommandé pour voir le film
  private boolean enlocation; // Statut de la location (true si en location)
  private Artiste realisateur; // Réalisateur du film
  private Set<Artiste> acteurs; // Ensemble des acteurs participant au film
  private Set<Genre> genres; // Ensemble des genres associés au film
  
  /**
   * Constructeur de la classe Film.
   *
   * @param titre le titre du film (ne peut pas être null ou vide)
   * @param annee l'année de sortie du film
   * @param realisateur le réalisateur du film (ne peut pas être null)
   * @param acteurs une liste des acteurs impliqués dans le film
   * @param genres un ensemble de genres associés au film
   * @param ageMinimum l'âge minimum recommandé pour voir le film
   */
  public Film(String titre, int annee, Artiste realisateur,
      List<Artiste> acteurs, Set<Genre> genres, int ageMinimum) {
    this.titre = titre; // Initialisation du titre
    this.annee = annee; // Initialisation de l'année
    this.realisateur = realisateur; // Initialisation du réalisateur
    this.acteurs = (acteurs != null) ? new HashSet<>(acteurs) : new HashSet<>();
    this.genres = (genres != null) ? genres : new HashSet<>();
    this.ageMinimum = ageMinimum; // Initialisation de l'âge minimum
    this.enlocation = false; // Par défaut, le film n'est pas en location
  }
  
  // ==== Getters ====
  
  /**
   * Récupère le titre du film.
   *
   * @return le titre du film
   */
  public String getTitre() {
    return titre;
  }
  
  /**
   * Récupère l'affiche du film (URL ou nom du fichier).
   *
   * @return l'affiche du film
   */
  public String getAffiche() {
    return affiche;
  }
  
  /**
   * Récupère l'année de sortie du film.
   *
   * @return l'année du film
   */
  public int getAnnee() {
    return annee;
  }
  
  /**
   * Vérifie si le film est actuellement en location.
   *
   * @return vrai si le film est en location, sinon faux
   */
  public boolean isEnLocation() {
    return enlocation;
  }
  
  /**
   * Récupère l'âge minimum recommandé pour voir le film.
   *
   * @return l'âge minimum recommandé
   */
  public int getAgeMinimum() {
    return ageMinimum;
  }
  
  /**
   * Récupère le réalisateur du film.
   *
   * @return le réalisateur
   */
  public Artiste getRealisateur() {
    return realisateur;
  }
  
  /**
   * Récupère l'ensemble des acteurs participant au film.
   *
   * @return l'ensemble des acteurs
   */
  public Set<Artiste> getActeurs() {
    return acteurs;
  }
  
  /**
   * Récupère l'ensemble des genres associés au film.
   *
   * @return l'ensemble des genres
   */
  public Set<Genre> getGenres() {
    return genres;
  }
  
  // ==== Setters ====
  
  /**
   * Modifie l'affiche du film.
   *
   * @param affiche L'affiche du film (URL ou nom du fichier)
   */
  public void setAffiche(String affiche) {
    this.affiche = affiche;
  }
  
  /**
   * Modifie l'âge minimum recommandé pour voir le film.
   *
   * @param age L'âge minimum à définir
   */
  public void setAgeMinimum(int age) {
    if (age > 0) { // Validation de l'âge
      this.ageMinimum = age;
    }
  }
  
  /**
   * Modifie l'état de location du film.
   *
   * @param etat true si le film est en location, false sinon
   */
  public void changerLocation(boolean etat) {
    enlocation = etat;
  }
  
  // ==== Méthodes pour ajouter des données ====
  
  /**
   * Ajoute un acteur au film.
   *
   * @param acteur L'acteur à ajouter
   * @return true si l'ajout a réussi, false sinon
   */
  public boolean addActeur(Artiste acteur) {
    if (acteur != null) { // Validation de l'acteur
      return acteurs.add(acteur);
    }
    return false;
  }
  
  /**
   * Ajoute un genre au film.
   *
   * @param genre Le genre à ajouter
   * @return true si l'ajout a réussi, false sinon
   */
  public boolean addGenre(Genre genre) {
    return genres.add(genre);
  }
  
  // ==== Méthodes redéfinies ====
  
  /**
   * Compare deux films pour vérifier s'ils sont égaux. Deux films sont égaux
   * s'ils ont le même titre.
   *
   * @param obj L'objet à comparer
   * @return true si les deux films sont égaux, sinon false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true; // Vérification d'identité
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false; // Vérification de null ou de type
    }
    Film film = (Film) obj;
    return Objects.equals(titre, film.titre); // Vérification du titre
  }
  
  /**
   * Calcule le hashcode basé sur le titre du film.
   *
   * @return le hashcode du film
   */
  @Override
  public int hashCode() {
    return Objects.hash(titre);
  }
  
  /**
   * Retourne une représentation textuelle du film. Inclut le titre, l'année, le
   * réalisateur, les genres et les acteurs.
   *
   * @return une chaîne représentant le film
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Titre: ").append(titre).append(", Année: ").append(annee)
        .append(", Réalisateur: ").append(realisateur).append(", Genres: ")
        .append(genres).append(", Acteurs: [");
    acteurs.forEach(acteur -> sb.append(acteur).append(", "));
    if (!acteurs.isEmpty()) {
      sb.setLength(sb.length() - 2); // Supprime la dernière virgule
    }
    sb.append("]");
    return sb.toString();
  }
}
