package location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// A COMPLETER
/**
 * Inscription d'un utilisateur. Le pseudo choisi ne doit pas déjà exister
 * parmi les utilisateurs déjà inscrits.
 *
 * @param pseudo le pseudo (unique) de l'utilisateur
 * @param mdp le mot de passe de l'utilisateur (ne pas doit pas être vide ou
 *        <code>null</code>)
 * @param info les informations personnelles sur l'utilisateur
 * @return un code précisant le résultat de l'inscription : 0 si l'inscription
 *         s'est bien déroulée, 1 si le pseudo était déjà utilisé, 2 si le
 *         pseudo ou le mot de passe était vide, 3 si les informations
 *         personnelles ne sont pas bien précisées
 */


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


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public Set<Film> getFilmsEnLocation() {
		return filmsEnLocation;
	}


	public void setFilmsEnLocation(Set<Film> filmsEnLocation) {
		this.filmsEnLocation = filmsEnLocation;
	}  

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





