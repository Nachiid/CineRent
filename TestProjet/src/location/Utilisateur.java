package location;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
=======


import java.util.HashSet;

import java.util.HashMap;

import java.util.Map;

>>>>>>> bc8cb16cb78161edd690d3a76182a09a2a5ebf55
import java.util.Set;



/**

 * Classe représentant un utilisateur du système de location de films.

 * Un utilisateur peut s'inscrire, se connecter, se déconnecter, et louer des films.

 * Cette classe gère également les informations personnelles de l'utilisateur et ses évaluations.

 */

<<<<<<< HEAD

public class Utilisateur {
	private String pseudo;
	private String nom; 
	private String prenom;
	private int age;
	private String adresse;
	private String motDePasse;
	private Set<Film> filmsEnLocation;
	
	 static final int nbrMAXRes= 3;
	  int nbrRes=0;

	
	public Utilisateur(String pseudo, String nom, String prenom, int age, String adresse, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.filmsEnLocation = new HashSet<>();
	}
	
	public String getPseudo() {
		return pseudo;
	}
=======
public class Utilisateur implements InterUtilisateur {



    /**

     * Le pseudo unique de l'utilisateur.

     */

    private String pseudo;
>>>>>>> bc8cb16cb78161edd690d3a76182a09a2a5ebf55



    /**

     * Les informations personnelles de l'utilisateur.

     */

    private InformationPersonnelle infoperso;



    /**

     * Le mot de passe de l'utilisateur.

     */

    private String motDePasse;



    /**

     * La liste des films actuellement loués par l'utilisateur.

     */

    private Set<Film> filmsEnLocation;



    /**

     * Les évaluations laissées par l'utilisateur sur des films.

     */

    private Set<Evaluation> evaluations;



    /**

     * Les utilisateurs inscrits dans le système, identifiés par leur pseudo.

     */

    private static Map<String, Utilisateur> utilisateursInscrits = new HashMap<>();



    /**

     * Le pseudo de l'utilisateur actuellement connecté.

     */

    private static String utilisateurConnecte = null;



    /**

     * Le nombre maximum de films pouvant être loués en même temps.

     */

    private static final int MAX_LOC = 3;



    /**

     * Le nombre de films actuellement loués par l'utilisateur.

     */

    private int nbrLocEnCours = 0;



    /**

     * Constructeur de la classe Utilisateur.

     *

     * @param pseudo      le pseudo unique de l'utilisateur

     * @param motDePasse  le mot de passe de l'utilisateur

     * @param infoperso   les informations personnelles de l'utilisateur

     */

    public Utilisateur(String pseudo, String motDePasse, InformationPersonnelle infoperso) {

        this.pseudo = pseudo;

        this.motDePasse = motDePasse;

        this.filmsEnLocation = new HashSet<>();

        this.infoperso = infoperso;

        this.evaluations = new HashSet<>();

    }



    /**

     * Retourne le pseudo de l'utilisateur.

     *

     * @return le pseudo de l'utilisateur

     */

    public String getPseudo() {

        return pseudo;

    }



    /**

     * Modifie le pseudo de l'utilisateur.

     *

     * @param pseudo le nouveau pseudo

     */

    public void setPseudo(String pseudo) {

        this.pseudo = pseudo;

    }



    /**

     * Retourne le mot de passe de l'utilisateur.

     *

     * @return le mot de passe de l'utilisateur

     */

    public String getMotDePasse() {

        return motDePasse;

    }



<<<<<<< HEAD
	public void reserverFilm(Film film) throws Exception {
	    /* Vérifie si l'utilisateur est connecté*/
	    if (this.pseudo == null || this.pseudo.isEmpty()) {
	        throw new Exception("Utilisateur non connecté.");
	    }

	    // Vérifie l'âge de l'utilisateur par rapport à l'âge requis pour le film
	    if (this.age < film.getAgeMinimum()) {
	        throw new LocationException("Âge insuffisant pour louer ce film.");
	    }

	    // Vérifie si l'utilisateur a atteint le nombre maximum de films en location
	    if (this.filmsEnLocation.size() >= nbrMAXRes) {
	        throw new LocationException("Nombre maximum de films en location atteint.");
	    }

	    // Vérifie si le film est disponible à la location
	    if (!film.isOuvertALocation()) {
	        throw new LocationException("Le film '" + film.getTitre() + "' n'est pas disponible à la location.");
	    }

	    // Ajoute le film à la liste des films en location
	    this.filmsEnLocation.add(film);

	    // Indique que le film est loué
	    film.setOuvertALocation(false);

	    System.out.println("Le film '" + film.getTitre() + "' a été loué avec succès par " + this.pseudo);
	}
	
	public void annulerReservation(Film film) throws LocationException {
	    // Vérifie si le film est dans la liste des films en location
	    if (!this.filmsEnLocation.contains(film)) {
	        throw new LocationException("Le film '" + film.getTitre() + "' n'est pas dans votre liste de films en location.");
	    }

	    // Supprime le film de la liste des films en location
	    this.filmsEnLocation.remove(film);

	    // Marque le film comme disponible à nouveau
	    film.setOuvertALocation(true);

	    System.out.println("La location du film '" + film.getTitre() + "' a été annulée avec succès par " + this.pseudo);
	}

	

	public List<Film> getFilmsReserves() throws Exception {
	    // Vérifie si l'utilisateur est connecté
	    if (this.pseudo == null || this.pseudo.isEmpty()) {
	        throw new Exception("Aucun utilisateur n'est actuellement connecté.");
	    }

	    // Retourne une liste contenant les films en location
	    return new ArrayList<>(this.filmsEnLocation);
	}
	
}





=======
    /**

     * Modifie le mot de passe de l'utilisateur.

     *

     * @param motDePasse le nouveau mot de passe

     */

    public void setMotDePasse(String motDePasse) {

        this.motDePasse = motDePasse;

    }



    /**

     * Retourne la liste des films actuellement loués par l'utilisateur.

     *

     * @return un ensemble de films loués

     */

    public Set<Film> getFilmsEnLocation() {

        return filmsEnLocation;

    }



    /**

     * Modifie la liste des films actuellement loués par l'utilisateur.

     *

     * @param filmsEnLocation le nouvel ensemble de films loués

     */

    public void setFilmsEnLocation(Set<Film> filmsEnLocation) {

        this.filmsEnLocation = filmsEnLocation;

    }



    /**

     * Inscrit un nouvel utilisateur dans le système.

     *

     * @param pseudo     le pseudo unique de l'utilisateur

     * @param motDePasse le mot de passe de l'utilisateur

     * @param info       les informations personnelles de l'utilisateur

     * @return un code décrivant le résultat :

     *         <ul>

     *             <li>0 : Inscription réussie</li>

     *             <li>1 : Pseudo déjà utilisé</li>

     *             <li>2 : Pseudo ou mot de passe vide</li>

     *             <li>3 : Informations personnelles invalides</li>

     *         </ul>

     */

    public int inscription(String pseudo, String motDePasse, InformationPersonnelle info) {

        if (pseudo == null || pseudo.isEmpty() || motDePasse == null || motDePasse.isEmpty()) {

            return 2; // Pseudo ou mot de passe vide

        }

        if (info == null || !info.estValide()) { // Hypothèse : méthode estValide() vérifie la validité des infos

            return 3; // Informations personnelles invalides

        }

        if (utilisateursInscrits.containsKey(pseudo)) {

            return 1; // Pseudo déjà utilisé

        }



        // Ajout de l'utilisateur

        Utilisateur newUser = new Utilisateur(pseudo, motDePasse, info);

        utilisateursInscrits.put(pseudo, newUser);

        return 0; // Inscription réussie

    }



    /**

     * Connecte un utilisateur existant.

     *

     * @param pseudo     le pseudo de l'utilisateur

     * @param motDePasse le mot de passe de l'utilisateur

     * @return true si la connexion est réussie, false sinon

     */

    public boolean connexion(String pseudo, String motDePasse) {

        if (utilisateursInscrits.containsKey(pseudo)) {

            Utilisateur user = utilisateursInscrits.get(pseudo);

            if (user.motDePasse.equals(motDePasse)) {

                utilisateurConnecte = pseudo; // Enregistre l'utilisateur connecté

                return true; // Connexion réussie

            }

        }

        return false; // Échec de la connexion

    }



    /**

     * Déconnecte l'utilisateur actuellement connecté.

     *

     * @throws NonConnecteException si aucun utilisateur n'est connecté

     */

    public void deconnexion() throws NonConnecteException {

        if (utilisateurConnecte == null) {

            throw new NonConnecteException("Aucun utilisateur n'est actuellement connecté.");

        }

        utilisateurConnecte = null;

        System.out.println("Vous êtes déconnecté.");

    }

}
>>>>>>> bc8cb16cb78161edd690d3a76182a09a2a5ebf55
