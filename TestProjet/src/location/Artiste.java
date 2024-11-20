package location;

// A COMPLETER

public class Artiste {
	 private String nom;
	 private String prenom;
	 private boolean estActeur; // vrai si un acteur , faux si realisateur
	
	 //constructeur
		 public Artiste(String nom, String prenom, boolean estActeur) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.estActeur = estActeur;
		}
	//getter et setter du nom de l'artiste
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//getter et setter du prenom de l'artiste
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	//un getter pour voiir si l'artiste est un realisateur
	public boolean estRealisateur(){
        return !estActeur;
    }
	
	//getter et setter pour l'acteur 
	public boolean getEstActeur() {
		return estActeur;
	}
	public void setEstActeur(boolean estActeur) {
		this.estActeur = estActeur;
	}
	@Override
	public String toString() {
		return  prenom + " " + nom + (estActeur ? "(Acteur)" : " (Realisateur)");
	}

	 
	 
	 

	 
}
