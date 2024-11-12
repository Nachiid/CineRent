package location;

// A COMPLETER

public class Artiste {
	 private String nom;
	 private String prenom;
	 private String role; 
	 
	 public Artiste(String nom, String prenom, String role) {
	        this.nom = nom;
	        this.prenom = prenom;
	        this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	     
	 
	@Override
    public String toString() {
        return prenom + " " + nom + " (" + role + ")";
	}
	 
	 
}
