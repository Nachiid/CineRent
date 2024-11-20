package location;

import java.util.Set;

// A COMPLETER

public class Film {
	private String titre;
	private int annee;
	private Artiste realisateur;
	private Set<Artiste> acteurs;
	private Set<Genre> genres;
	private int ageMinimum;
	private Set<Evaluation> evaluations;
	private boolean isDisponible;
	
	
	public Film(String titre, int annee, Artiste realisateur, Set<Artiste> acteurs, Set<Genre> genres, int ageMinimum,
			Set<Evaluation> evaluations, boolean isDisponible) {
		super();
		this.titre = titre;
		this.annee = annee;
		this.realisateur = realisateur;
		this.acteurs = acteurs;
		this.genres = genres;
		this.ageMinimum = ageMinimum;
		this.evaluations = evaluations;
		this.isDisponible = isDisponible;
	}
	
	
	
	
}
