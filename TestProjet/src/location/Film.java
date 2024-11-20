package location;

// A COMPLETER

public class Film {
	private String titre;
	private int annee;
	private Artiste realisateur;
	private Set<Artiste> acteurs;
	private Set<Genre> genres;
	private int ageMinimum;
	
	
	public Film(String titre, int annee, Artiste realisateur, Set<Artiste> acteurs, Set<Genre> genres, int ageMinimum) {
		super();
		this.titre = titre;
		this.annee = annee;
		this.realisateur = realisateur;
		this.acteurs = acteurs;
		this.genres = genres;
		this.ageMinimum = ageMinimum;
	}
    
	
}
