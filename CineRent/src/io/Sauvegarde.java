package io;

import location.Administrateur;
import location.Artiste;
import location.Film;
import location.Genre;
import java.io.*;

/**
 * Classe responsable de la sauvegarde et du chargement des données des films et
 * artistes dans un format lisible. Cette classe permet de persister les données
 * d'un administrateur.
 */
public class Sauvegarde implements InterSauvegarde {
  
  private Administrateur administrateur;
  
  /**
   * Constructeur de la classe Sauvegarde.
   *
   * @param administrateur L'instance d'administrateur dont les données seront
   *        sauvegardées ou chargées.
   */
  public Sauvegarde(Administrateur administrateur) {
    this.administrateur = administrateur;
  }
  
  /**
   * Sauvegarde les données des artistes et films de l'administrateur dans un
   * fichier texte.
   *
   * @param nomFichier Nom du fichier dans lequel sauvegarder les données.
   * @throws IOException En cas d'erreur d'écriture dans le fichier.
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(nomFichier))) {
      // Sauvegarder les artistes
      writer.write("# Artistes\n");
      for (Artiste artiste : administrateur.ensembleActeurs()) {
        writer.write(artiste.getNom() + "," + artiste.getPrenom() + ","
            + artiste.getNationalite() + "," + artiste.estActeur() + "\n");
      }
      
      // Sauvegarder les films
      writer.write("# Films\n");
      for (Film film : administrateur.ensembleFilms()) {
        writer.write(film.getTitre() + "," + film.getAnnee() + ","
            + film.getAgeMinimum() + "," + film.getRealisateur().getNom() + ","
            + film.getRealisateur().getPrenom() + "," + film.isEnLocation()
            + "\n");
        
        // Sauvegarder les genres associés au film
        if (!film.getGenres().isEmpty()) {
          writer
              .write("genres:"
                  + String.join(",",
                      film.getGenres().stream().map(Enum::name).toList())
                  + "\n");
        }
        
        // Sauvegarder les acteurs associés au film
        for (Artiste acteur : film.getActeurs()) {
          writer.write(
              "acteur:" + acteur.getNom() + "," + acteur.getPrenom() + "\n");
        }
      }
    }
  }
  
  /**
   * Charge les données des artistes et films depuis un fichier texte. Les
   * données sont utilisées pour remplir l'instance de l'administrateur.
   *
   * @param nomFichier Nom du fichier à partir duquel charger les données.
   * @throws IOException En cas d'erreur de lecture du fichier.
   */
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    try (BufferedReader reader =
        new BufferedReader(new FileReader(nomFichier))) {
      String ligne;
      administrateur = new Administrateur();
      Film dernierFilm = null;
      
      while ((ligne = reader.readLine()) != null) {
        if (ligne.startsWith("#") || ligne.isBlank()) {
          continue; // Ignorer les lignes de titre ou vides
        }
        
        if (ligne.startsWith("genres:")) {
          // Charger les genres d'un film
          if (dernierFilm != null) {
            String[] genres = ligne.substring(7).split(",");
            for (String genreStr : genres) {
              try {
                // Convertir la chaîne en enum Genre
                Genre genre = Genre.valueOf(genreStr.trim());
                dernierFilm.addGenre(genre);
              } catch (IllegalArgumentException e) {
                System.err
                    .println("Genre invalide ignoré : " + genreStr.trim());
              }
            }
          }
        } else if (ligne.startsWith("acteur:")) {
          // Ajouter un acteur à un film
          String[] data = ligne.substring(7).split(",");
          if (dernierFilm != null && data.length == 2) {
            Artiste acteur = administrateur.getArtiste(data[0], data[1]);
            if (acteur != null) {
              administrateur.ajouterActeurs(dernierFilm, acteur);
            }
          }
        } else if (ligne.contains(",")) {
          // Ajouter un artiste ou un film
          String[] data = ligne.split(",");
          if (data.length == 4) {
            // Ajouter un artiste avec le type acteur/réalisateur
            String nom = data[0];
            String prenom = data[1];
            String nationalite = data[2];
            boolean estActeur = Boolean.parseBoolean(data[3]);
            
            administrateur.creerArtiste(nom, prenom, nationalite);
            if (!estActeur) {
              administrateur
                  .setRealisateur(administrateur.getArtiste(nom, prenom));
            }
          } else if (data.length == 6) {
            // Ajouter un film
            String titre = data[0];
            int annee = Integer.parseInt(data[1]);
            int ageMinimum = Integer.parseInt(data[2]);
            String nomRealisateur = data[3];
            String prenomRealisateur = data[4];
            boolean enLocation = Boolean.parseBoolean(data[5]);
            
            Artiste realisateur =
                administrateur.getArtiste(nomRealisateur, prenomRealisateur);
            if (realisateur != null) {
              dernierFilm = administrateur.creerFilm(titre, realisateur, annee,
                  ageMinimum);
              if (dernierFilm != null && enLocation) {
                administrateur.ouvrirLocation(dernierFilm);
              }
            }
          }
        }
      }
    }
  }
  
  /**
   * Récupère l'instance d'administrateur contenant les données chargées ou
   * modifiées.
   *
   * @return L'instance d'administrateur.
   */
  public Administrateur getAdministrateur() {
    return administrateur;
  }
}
