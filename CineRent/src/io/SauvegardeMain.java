package io;

import location.Administrateur;
import location.Film;

import java.io.IOException;

public class SauvegardeMain {
  
  public static void main(String[] args) {
    // Création de l'administrateur avec des données d'exemple
    Administrateur administrateur = new Administrateur();
    administrateur.creerArtiste("Nolan", "Christopher", "Britannique");
    administrateur.creerArtiste("Spielberg", "Steven", "Américain");
    administrateur.creerArtiste("Nachid", "ayman", "Américain");
    administrateur.creerArtiste("realisateur", "Laila", "Américain");
    administrateur
        .setRealisateur(administrateur.getArtiste("Laila", "realisateur"));
    
    Film inception = administrateur.creerFilm("Inception",
        administrateur.getArtiste("Nolan", "Christopher"), 2010, 13);
    administrateur.ajouterActeurs(inception,
        administrateur.getArtiste("Nolan", "Christopher"));
    
    
    // Instance de sauvegarde
    Sauvegarde sauvegarde = new Sauvegarde(administrateur);
    
    // Nom du fichier de sauvegarde
    String nomFichier = "donnees_test.txt";
    
    try {
      // Sauvegarder les données
      System.out.println("Sauvegarde des données...");
      sauvegarde.sauvegarderDonnees(nomFichier);
      
      // Charger les données
      System.out.println("Chargement des données...");
      sauvegarde.chargerDonnees(nomFichier);
      
      // Afficher les données chargées
      Administrateur administrateurCharge = sauvegarde.getAdministrateur();
      
      System.out.println("\n=== Données chargées ===");
      System.out.println("Films :");
      administrateurCharge.ensembleFilms()
          .forEach(f -> System.out.println("- " + f.getTitre() + " ("
              + f.getAnnee() + "), Réalisé par: " + f.getRealisateur().getNom()
              + " " + f.getRealisateur().getPrenom()));
      
      System.out.println("\nArtistes :");
      administrateurCharge.ensembleActeurs()
          .forEach(a -> System.out.println("- " + a.getNom() + " "
              + a.getPrenom() + ", Nationalité: " + a.getNationalite()));
      
    } catch (IOException e) {
      System.err.println(
          "Erreur lors de la sauvegarde ou du chargement : " + e.getMessage());
    }
  }
}
