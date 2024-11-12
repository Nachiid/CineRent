package location;

// A COMPLETER

public class Film {
	private String titre;
	private int annee;
	private Artiste realisateur;
	private Set<Artiste> acteurs;
	private Set<Genre> genres;
	private int ageMinimum;
    
	 public Film(String titre, int annee, Artiste realisateur, int ageMinimum) {
	        this.titre = titre;
	        this.annee = annee;
	        this.realisateur = realisateur;
	        this.ageMinimum = ageMinimum;
	        this.acteurs = new ArrayList<>();
	        this.genres = new ArrayList<>();
	        }
    

}
 