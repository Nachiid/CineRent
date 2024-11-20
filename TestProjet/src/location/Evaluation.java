package location;

// A COMPLETERR 

public class Evaluation {
    private String pseudoUtilisateur;
    private int note;
    private String commentaire; 
    
    public Evaluation(String pseudoUtilisateur, int note, String commentaire) {
        this.pseudoUtilisateur = pseudoUtilisateur;
        this.note = note;
        this.commentaire = commentaire;
    }

    // Getters and Setters
    public String getPseudoUtilisateur() { return pseudoUtilisateur; }
    public void setPseudoUtilisateur(String pseudoUtilisateur) { this.pseudoUtilisateur = pseudoUtilisateur; }
    
    public int getNote() { return note; }
    
    public void setNote(int note) {
        if (note >= 0 && note <= 5) {
            this.note = note;
        } else {
            throw new IllegalArgumentException("La note doit être entre 0 et 5.");
        }
    }
    
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    @Override
    public String toString() {
        return pseudoUtilisateur + " : " + note + "/5 - " + (commentaire != null ? commentaire : "Pas de commentaire");
    }
}
