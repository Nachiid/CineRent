package location;

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

	
	
	

}


