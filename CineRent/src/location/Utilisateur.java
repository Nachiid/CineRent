package location;

import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Classe représentant un utilisateur du système de location de films. Un
 * utilisateur peut s'inscrire, se connecter, se déconnecter, louer des films,
 * évaluer des films, etc. Cette classe gère également les informations
 * personnelles de l'utilisateur et ses évaluations.
 */
public final class Utilisateur implements InterUtilisateur, Serializable {
  
  /**
   * Identifiant de version pour la sérialisation de la classe.
   */
  private static final long serialVersionUID = 851370939719466580L;
  /**
   * Le pseudo unique de l'utilisateur.
   */
  private final String pseudo;
  
  /**
   * Le pseudo unique de l'utilisateur connecté.
   */
  private String utilisateurConnecte = null;
  
  /**
   * Les informations personnelles de l'utilisateur.
   */
  private final InformationPersonnelle infoperso;
  
  /**
   * Le mot de passe de l'utilisateur (peut être modifié uniquement lors de la
   * connexion ou de l'inscription).
   */
  private final String motDePasse;
  
  
  
  private Set<Film> films; // Films ouvert a la location
  
  private Set<Artiste> artiste; // Films actuellement loués
  
  
  
  private Reservation reservation;
  
  // Attribut pour stocker l'instance de GestionUtilisateur
  private GestionUtilisateur gestionUtilisateur;
  
  
  /**
   * Constructeur de la classe Utilisateur.
   *
   * @param pseudo le pseudo unique de l'utilisateur
   * @param motDePasse le mot de passe de l'utilisateur
   * @param infoperso les informations personnelles de l'utilisateur
   */
  public Utilisateur(String pseudo, String motDePasse,
      InformationPersonnelle infoperso) {
    this.pseudo = Objects.requireNonNull(pseudo, "Le pseudo ne peut être null");
    this.motDePasse =
        Objects.requireNonNull(motDePasse, "Le mot de passe ne peut être null");
    this.infoperso = Objects.requireNonNull(infoperso,
        "Les informations personnelles ne peuvent être null");
    
    this.films = new HashSet<>(); // Charger tous les films
    this.artiste = new HashSet<>();
    this.gestionUtilisateur = new GestionUtilisateur();
    this.reservation = new Reservation();
  }
  
  
  private void verifierConnexion() throws NonConnecteException {
    if (utilisateurConnecte == null) {
      throw NonConnecteException.utilisateurNonConnecte();
    }
  }
  
  /**
   * Retourne le mot de passe de l'utilisateur.
   *
   * @return Le mot de passe.
   */
  public String getPseudo() {
    return this.pseudo;
  }
  
  /**
   * Retourne le mot de passe de l'utilisateur.
   *
   * @return Le mot de passe.
   */
  public String getMdp() {
    return this.motDePasse;
  }
  
  
  /**
   * Récupère les informations personnelles de l'utilisateur.
   *
   * @return Les informations personnelles.
   * @throws NonConnecteException
   */
  public InformationPersonnelle getInfo() {
    return infoperso;
  }
  
  /**
   * Récupère la réservation associée à l'utilisateur.
   *
   * @return La réservation.
   * @throws NonConnecteException
   */
  public Reservation getReservation() throws NonConnecteException {
    verifierConnexion();
    return reservation;
  }
  
  /**
   * Définit l'utilisateur connecté.
   *
   * @param reservation La nouvelle réservation.
   */
  public void setUtilisateurConnecte(String utilisateurConnecte) {
    this.utilisateurConnecte = utilisateurConnecte;
  }
  
  public String getUtilisateurConnecte() throws NonConnecteException {
    return utilisateurConnecte;
  }
  
  
  /**
   * Définit la réservation associée à l'utilisateur.
   *
   * @param reservation La nouvelle réservation.
   * @throws NonConnecteException
   */
  public void setReservation(Reservation reservation)
      throws NonConnecteException {
    verifierConnexion();
    this.reservation = reservation;
  }
  
  
  @Override
  // Ajouter ou modifier une évaluation d'un film
  public void ajouterEvaluation(Film film, Evaluation eval)
      throws LocationException, NonConnecteException {
    verifierConnexion();
    // Appel de la méthode ajouterOuModifierEvaluation pour gérer l'ajout ou la
    // modification
    reservation.ajouterOuModifierEvaluation(film, eval);
  }
  
  /**
   * Modifie l'évaluation que l'utilisateur connecté avait déjà déposée sur un
   * film. Ne peut se faire que si l'utilisateur avait déjà évalué le film.
   *
   * @param film le film dont l'utilisateur modifie l'évaluation
   * @param eval la nouvelle évaluation qui remplace la précédente
   * @throws NonConnecteException si aucun utilisateur n'était connecté
   * @throws LocationException en cas d'erreur pour modifier l'évaluation,
   *         l'exception contiendra un message précisant le problème
   */
  public void modifierEvaluation(Film film, Evaluation eval)
      throws NonConnecteException, LocationException {
    // Vérification de la connexion de l'utilisateur
    if (utilisateurConnecte == null) {
      throw NonConnecteException.utilisateurNonConnecte();
    }
    
    // Appeler la méthode dans Reservation pour ajouter ou modifier l'évaluation
    reservation.ajouterOuModifierEvaluation(film, eval);
  }
  
  
  
  /**
   * Inscrit un nouvel utilisateur dans le système.
   *
   * @param pseudo le pseudo unique de l'utilisateur
   * @param motDePasse le mot de passe de l'utilisateur
   * @param info les informations personnelles de l'utilisateur
   * @return un code décrivant le résultat :
   *         <ul>
   *         <li>0 : Inscription réussie</li>
   *         <li>1 : Pseudo déjà utilisé</li>
   *         <li>2 : Pseudo ou mot de passe vide</li>
   *         <li>3 : Informations personnelles invalides</li>
   *         </ul>
   */
  
  
  @Override
  public int inscription(String pseudo, String motDePasse,
      InformationPersonnelle info) {
    return gestionUtilisateur.inscription(pseudo, motDePasse, info);
  }
  
  /**
   * Connecte un utilisateur existant.
   *
   * @param pseudo le pseudo de l'utilisateur
   * @param motDePasse le mot de passe de l'utilisateur
   * @return true si la connexion est réussie, false sinon
   */
  @Override
  public boolean connexion(String pseudo, String motDePasse) {
    // Vérifier si l'utilisateur existe et si le mot de passe est correct
    Utilisateur utilisateur = gestionUtilisateur.connexion(pseudo, motDePasse);
    if (utilisateur != null) {
      // Si la connexion réussit, réinitialiser l'état de connexion
      setUtilisateurConnecte(pseudo);
      return true;
    }
    return false; // Échec de la connexion
  }
  
  
  
  /**
   * Déconnecte l'utilisateur actuellement connecté.
   *
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  @Override
  public void deconnexion() throws NonConnecteException {
    if (utilisateurConnecte == null) {
      throw NonConnecteException.utilisateurNonConnecte();
    }
    utilisateurConnecte = null; // Déconnexion réussie
  }
  
  
  
  @Override
  /**
   * Retourne la liste des films actuellement loués par l'utilisateur.
   *
   * @return un ensemble de films loués
   */
  public Set<Film> filmsEnLocation() throws NonConnecteException {
    verifierConnexion();
    return reservation.getFilm();
  }
  
  /**
   * Permet à un utilisateur de louer un film, s'il est connecté et n'a pas
   * atteint la limite de films à louer.
   *
   * @param film le film à louer
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException si l'utilisateur a atteint la limite de location,
   *         si l'âge est insuffisant, ou s'il a déjà ce film en location
   */
  @Override
  public void louerFilm(Film film)
      throws NonConnecteException, LocationException {
    // Vérification de connexion
    verifierConnexion();
    
    // Vérification de l'âge minimum requis pour le film
    if (film.getAgeMinimum() != 0
        && film.getAgeMinimum() > infoperso.getAge()) {
      throw LocationException.ageInsuffisant();
    }
    
    
    // Vérifier si le film est louable (disponibilité)
    if (!estLouable(film)) {
      throw LocationException.filmNonDisponible();
    }
    
    // Appel de la méthode dans Reservation pour gérer la location du film
    reservation.louerFilm(film);
  }
  
  
  
  /**
   * Information sur le fait qu'un film est ouvert à la location.
   *
   * @param film le film dont on veut vérifier la possibilité de location
   * @return <code>true</code> si le film est ouvert à la location,
   *         <code>false</code> sinon
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  @Override
  public boolean estLouable(Film film) throws NonConnecteException {
    verifierConnexion();
    return film.isEnLocation();
  }
  
  
  
  /**
   * Permet à un utilisateur de rendre un film qu'il a loué.
   *
   * @param film le film à rendre
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException si le film n'est pas dans la liste des films
   *         loués
   */
  @Override
  public void finLocationFilm(Film film)
      throws NonConnecteException, LocationException {
    // Vérifier si l'utilisateur est connecté
    verifierConnexion();
    reservation.retournerFilm(film);
  }
  
  
  
  /**
   * Renvoie l'ensemble des films.
   *
   * @return l'ensemble des films ou <code>null</code> si aucun film n'existe
   */
  @Override
  public Set<Film> ensembleFilms() {
    // Filtrer uniquement les films ouverts à la location
    return films;
  }
  
  
  
  /**
   * Renvoie l'ensemble des acteurs.
   *
   * @return l'ensemble des acteurs ou <code>null</code> si aucun acteur
   *         n'existe
   */
  @Override
  public Set<Artiste> ensembleActeurs() {
    // Filtrer uniquement les artistes qui sont des acteurs
    return artiste.stream().filter(Artiste::estActeur)
        .collect(Collectors.toSet());
  }
  
  /**
   * Renvoie l'ensemble des réalisateurs.
   *
   * @return l'ensemble des réalisateurs ou <code>null</code> si aucun
   *         réalisateur n'existe
   */
  @Override
  public Set<Artiste> ensembleRealisateurs() {
    // Filtrer uniquement les artistes qui sont des acteurs
    return artiste.stream().filter(Artiste::estRealisateur)
        .collect(Collectors.toSet());
  }
  
  /**
   * Cherche un acteur à partir de son nom et son prénom.
   *
   * @param nom le nom de l'acteur
   * @param prenom le prénom de l'acteur
   * @return l'acteur s'il a été trouvé ou <code>null</code> sinon
   */
  @Override
  public Artiste getActeur(String nom, String prenom) {
    return ensembleActeurs().stream()
        .filter(a -> a.getNom().equalsIgnoreCase(nom)
            && a.getPrenom().equalsIgnoreCase(prenom))
        .findFirst().orElse(null);
  }
  
  /**
   * Cherche un réalisateur à partir de son nom et son prénom.
   *
   * @param nom le nom du réalisateur
   * @param prenom le prénom du réalisateur
   * @return le réalisateur s'il a été trouvé ou <code>null</code> sinon
   */
  @Override
  public Artiste getRealisateur(String nom, String prenom) {
    return ensembleRealisateurs().stream()
        .filter(r -> r.getNom().equalsIgnoreCase(nom)
            && r.getPrenom().equalsIgnoreCase(prenom))
        .findFirst().orElse(null);
  }
  
  /**
   * Cherche un film à partir de son titre et uniquement parmi les films en
   * location.
   *
   * @param titre le titre du film
   * @return le film s'il a été trouvé ou <code>null</code> sinon
   */
  @Override
  public Film getFilm(String titre) {
    if (titre == null || titre.trim().isEmpty()) {
      return null; // Vérifier si le titre est vide ou null
    }
    
    return films.stream().filter(Film::isEnLocation) // Filtrer les films
                                                     // ouverts à la location
        .filter(f -> f.getTitre().equalsIgnoreCase(titre)) // Comparer les
                                                           // titres uniquement
        .findFirst().orElse(null); // Retourner null si aucun film correspondant
                                   // n'est trouvé
  }
  
  
  
  /**
   * Renvoie les films en location réalisés par un réalisateur donné.
   *
   * @param realisateur le réalisateur du film
   * @return l'ensemble des films en location réalisés par cet auteur
   */
  @Override
  public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
    if (realisateur == null)
      return null;
    return ensembleFilms().stream()
        .filter(f -> f.getRealisateur().equals(realisateur))
        .collect(Collectors.toSet());
  }
  
  /**
   * Renvoie les films en location réalisés par un réalisateur en spécifiant son
   * nom et prénom.
   *
   * @param nom le nom du réalisateur
   * @param prenom le prénom du réalisateur
   * @return l'ensemble des films en location réalisés par ce réalisateur
   */
  @Override
  public Set<Film> ensembleFilmsRealisateur(String nom, String prenom) {
    Artiste realisateur = getRealisateur(nom, prenom);
    return realisateur == null ? null : ensembleFilmsRealisateur(realisateur);
  }
  
  /**
   * Renvoie les films en location dans lesquels un acteur donné joue.
   *
   * @param acteur l'acteur du film
   * @return l'ensemble des films en location dans lesquels cet acteur joue
   */
  @Override
  public Set<Film> ensembleFilmsActeur(Artiste acteur) {
    if (acteur == null)
      return null;
    
    return ensembleFilms().stream().filter(f -> f.getActeurs().contains(acteur))
        .collect(Collectors.toSet());
  }
  
  /**
   * Renvoie les films en location dans lesquels un acteur joue, à partir de son
   * nom et prénom.
   *
   * @param nom le nom de l'acteur
   * @param prenom le prénom de l'acteur
   * @return l'ensemble des films en location dans lesquels cet acteur joue
   */
  @Override
  public Set<Film> ensembleFilmsActeur(String nom, String prenom) {
    Artiste acteur = getActeur(nom, prenom);
    return acteur == null ? null : ensembleFilmsActeur(acteur);
  }
  
  /**
   * Renvoie les films en location d'un genre donné.
   *
   * @param genre le genre du film
   * @return l'ensemble des films en location correspondant au genre
   */
  @Override
  public Set<Film> ensembleFilmsGenre(Genre genre) {
    if (genre == null) {
      return null;
    }
    return ensembleFilms().stream().filter(f -> f.getGenres().contains(genre)) // Filtre
                                                                               // les
                                                                               // films
                                                                               // qui
                                                                               // contiennent
                                                                               // le
                                                                               // genre
        .collect(Collectors.toSet());
  }
  
  /**
   * Renvoie les films en location correspondant à un genre donné sous forme de
   * chaîne de caractères.
   *
   * @param genre le genre sous forme de chaîne
   * @return l'ensemble des films en location correspondant au genre
   */
  @Override
  public Set<Film> ensembleFilmsGenre(String genre) {
    if (genre == null || genre.trim().isEmpty()) { // Vérifie si la chaîne est
                                                   // vide ou nulle
      return null;
    }
    
    // Formater la chaîne avec la première lettre en majuscule et le reste en
    // minuscule
    String formattedGenre =
        genre.substring(0, 1).toUpperCase() + genre.substring(1).toLowerCase();
    
    // Vérifier si le genre existe dans l'énumération
    for (Genre g : Genre.values()) {
      if (g.name().equalsIgnoreCase(formattedGenre)) {
        // Vérifie l'égalité sans tenir compte de la casse
        return ensembleFilmsGenre(g); // Appelle la méthode sur l'énumération
                                      // trouvée
      }
    }
    
    return null; // Retourne null si aucun genre ne correspond
  }
  
  
  
  /**
   * Renvoie les évaluations des films en location pour un film donné.
   *
   * @param film le film pour lequel obtenir les évaluations
   * @return l'ensemble des évaluations pour ce film
   */
  @Override
  public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
    return reservation.ensembleEvaluationsFilm(film);
  }
  
  /**
   * Renvoie les évaluations des films en location pour un film donné à partir
   * de son titre.
   *
   * @param titre le titre du film
   * @return l'ensemble des évaluations pour ce film
   */
  @Override
  public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
    Film film = getFilm(titre);
    return film == null ? null : ensembleEvaluationsFilm(film);
  }
  
  @Override
  public double evaluationMoyenne(Film film) {
    if (film == null)
      return -2; // Si le film est null, retourner -2
      
    // Récupérer l'ensemble des évaluations du film
    Set<Evaluation> evals = ensembleEvaluationsFilm(film);
    
    // Calculer la moyenne des évaluations
    double moyenne =
        evals.stream().mapToInt(Evaluation::getNote).average().orElse(-1);
    
    return moyenne;
  }
  
  @Override
  public double evaluationMoyenne(String titre) {
    // Vérification si le titre est vide
    if (titre == null || titre.trim().isEmpty()) {
      return -2; // Retourner -2 si le titre est vide
    }
    
    // Récupérer le film en utilisant son titre
    Film film = getFilm(titre);
    
    // Vérifier si le film n'existe pas
    if (film == null) {
      return -2; // Retourner -2 si le film n'existe pas
    }
    
    // Si le film existe, calculer la moyenne des évaluations
    return evaluationMoyenne(film);
  }
}
