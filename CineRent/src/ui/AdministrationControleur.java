package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import io.Sauvegarde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import location.Administrateur;
import location.Artiste;
import location.Film;
import location.Genre;
import location.Utilisateur;

/**
 * Controleur JavaFX de la fenêtre d'administration.
 *
 * @author Eric Cariou
 *
 */
public class AdministrationControleur {
  
  private Administrateur administration = new Administrateur(); // Initialisation
  // de
  // l'administrateur
  
  
  @FXML
  private CheckBox checkBoxLocationFilm;
  
  @FXML
  private TextField entreeAffiche;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ChoiceBox<String> listeChoixAgeLimite;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ListView<String> listeTousGenres;
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    
    listeArtistes.getItems().clear();
    for (Artiste acteur : administration.ensembleActeurs()) {
      // Récupère les informations de chaque acteur
      String nom = acteur.getNom();
      String prenom = acteur.getPrenom();
      String nationalite = acteur.getNationalite();
      
      // Formate les informations et les ajoute à la liste
      listeArtistes.getItems().add(nom + " " + prenom + " - " + nationalite);
    }
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    
    listeArtistes.getItems().clear();
    for (Artiste acteur : administration.ensembleRealisateurs()) {
      // Récupère les informations de chaque réalisateur
      String nom = acteur.getNom();
      String prenom = acteur.getPrenom();
      String nationalite = acteur.getNationalite();
      
      // Formate les informations et les ajoute à la liste
      listeArtistes.getItems().add(nom + " " + prenom + " - " + nationalite);
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    
    listeFilms.getItems().clear();
    
    // Récupère l'élément sélectionné dans la liste des artistes
    String selection = listeArtistes.getSelectionModel().getSelectedItem();
    
    // Vérifie si un acteur est sélectionné
    if (selection == null || selection.isEmpty()) {
      listeFilms.getItems().add("Sélectionnez un acteur.");
      return;
    }
    // Sépare le prénom et le nom de l'acteur sélectionné
    String[] info = selection.split(" ");
    
    // Récupère les films associés à l'acteur via les méthodes de
    // l'administration
    Set<Film> films = administration
        .ensembleFilmsActeur(administration.getArtiste(info[1], info[0]));
    
    // Vérifie si des films ont été trouvés et les affiche
    if (films == null || films.isEmpty()) {
      listeFilms.getItems().add("Aucun film trouvé.");
    } else {
      films.forEach(film -> listeFilms.getItems()
          .add(film.getTitre() + " (" + film.getAnnee() + ")"));
    }
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    listeFilms.getItems().clear();
    
    // Récupère le texte entré dans le champ pour le nom et prénom
    String selection = entreeNomPrenomRealisateur.getText();
    
    // Vérifie si le champ est vide ou invalide
    if (selection == null || selection.trim().isEmpty()) {
      listeFilms.getItems()
          .add("Veuillez entrer un prénom et un nom pour le réalisateur.");
      return;
    }
    
    // Sépare le prénom et le nom de l'artiste
    String[] nomPrenom = selection.split(" ");
    if (nomPrenom.length < 2) {
      listeFilms.getItems()
          .add("Format du nom ou prénom invalide. Utilisez 'Prénom Nom'.");
      return;
    }
    
    String prenom = nomPrenom[0].trim();
    String nom = nomPrenom[1].trim();
    
    // Récupère le réalisateur à partir de l'administration
    Artiste realisateur = administration.getArtiste(nom, prenom);
    if (realisateur == null || !realisateur.estRealisateur()) {
      listeFilms.getItems().add("Aucun réalisateur correspondant trouvé.");
      return;
    }
    
    // Récupère les films associés au réalisateur
    Set<Film> films = administration.ensembleFilmsRealisateur(realisateur);
    
    // Affiche les films ou un message s'il n'y en a pas
    if (films == null || films.isEmpty()) {
      listeFilms.getItems().add("Aucun film trouvé pour ce réalisateur.");
    } else {
      films.forEach(film -> listeFilms.getItems()
          .add(film.getTitre() + " (" + film.getAnnee() + ")"));
    }
  }
  
  
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    listeFilms.getItems().clear();
    
    // Vérifie si un élément est sélectionné dans la liste des artistes
    String selection = listeArtistes.getSelectionModel().getSelectedItem();
    if (selection == null || selection.isEmpty()) {
      listeFilms.getItems().add("Sélectionnez un réalisateur.");
      return;
    }
    
    // Sépare le prénom, nom et nationalité de l'artiste sélectionné
    String[] info = selection.split(" - "); // Divise par le format utilisé dans
                                            // l'interface : "Nom Prénom -
                                            // Nationalité"
    if (info.length < 2) {
      listeFilms.getItems().add("Format de l'artiste sélectionné invalide.");
      return;
    }
    
    String[] nomPrenom = info[0].split(" ");
    if (nomPrenom.length < 2) {
      listeFilms.getItems().add("Format du nom ou prénom invalide.");
      return;
    }
    
    String nom = nomPrenom[1];
    String prenom = nomPrenom[0];
    
    // Récupère les films associés au réalisateur via l'administration
    Set<Film> films = administration
        .ensembleFilmsRealisateur(administration.getArtiste(prenom, nom));
    
    // Vérifie si des films ont été trouvés et les affiche
    if (films == null || films.isEmpty()) {
      listeFilms.getItems().add("Aucun film trouvé.");
    } else {
      films.forEach(film -> listeFilms.getItems()
          .add(film.getTitre() + " (" + film.getAnnee() + ")"));
    }
    
  }
  
  @FXML
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    listeArtistes.getItems().clear();
    
    // Récupère l'élément sélectionné dans la liste des films
    String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
    
    // Vérifie si un film a été sélectionné
    if (titreFilm == null || titreFilm.trim().isEmpty()) {
      listeArtistes.getItems().add("Sélectionnez un film dans la liste.");
      return;
    }
    String[] info = titreFilm.split(" - ");
    // Récupère le film correspondant à partir de son titre
    Film film = administration.getFilm(info[0]);
    if (film == null) {
      listeArtistes.getItems().add("Aucun film correspondant trouvé.");
      return;
    }
    
    // Récupère les acteurs du film
    Set<Artiste> acteurs = film.getActeurs();
    if (acteurs == null || acteurs.isEmpty()) {
      listeArtistes.getItems().add("Aucun acteur trouvé pour ce film.");
    } else {
      // Affiche les acteurs dans la liste
      acteurs.forEach(acteur -> listeArtistes.getItems()
          .add(acteur.getPrenom() + " " + acteur.getNom()));
    }
  }
  
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    
    listeArtistes.getItems().clear();
    
    // Récupère tous les artistes via les méthodes de gestion
    Set<Artiste> artistes = administration.ensembleActeurs();
    artistes.addAll(administration.ensembleRealisateurs());
    
    // Ajoute chaque artiste à la liste avec un format clair
    artistes.forEach(artiste -> listeArtistes.getItems().add(artiste.getNom()
        + " " + artiste.getPrenom() + " - " + artiste.getNationalite()));
    
    // Si aucun artiste n'est trouvé
    if (listeArtistes.getItems().isEmpty()) {
      listeArtistes.getItems().add("Aucun artiste trouvé.");
    }
    
  }
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    // Récupère le film sélectionné
    String filmSelection = listeFilms.getSelectionModel().getSelectedItem();
    if (filmSelection == null || filmSelection.isEmpty()) {
      listeGenresFilm.getItems().add("Sélectionnez un film.");
      return;
    }
    
    String[] film = filmSelection.split(" ");
    
    // Récupère l'acteur sélectionné
    String acteurSelection =
        listeArtistes.getSelectionModel().getSelectedItem();
    if (acteurSelection == null || acteurSelection.isEmpty()) {
      listeGenresFilm.getItems().add("Sélectionnez un acteur.");
      return;
    }
    
    // Récupère le film et l'acteur depuis les données
    String[] acteurInfo = acteurSelection.split(" ");
    Artiste acteur = administration.getArtiste(acteurInfo[0], acteurInfo[1]); // Nom,
                                                                              // Prénom
    Film films = administration.getFilm(film[0]);
    
    if (acteur == null) {
      listeGenresFilm.getItems().add("Film introuvable.");
      return;
    }
    if (films == null) {
      listeGenresFilm.getItems().add("Film introuvable.");
      return;
    }
    // Ajoute l'acteur au film
    boolean success = administration.ajouterActeurs(films, acteur);
    
    // Affiche un message selon le résultat
    if (success) {
      listeGenresFilm.getItems().add("Acteur ajouté au film avec succès.");
    } else {
      listeGenresFilm.getItems().add("Échec de l'ajout de l'acteur.");
    }
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    listeGenresFilm.getItems().clear();
    String filmSelection = listeFilms.getSelectionModel().getSelectedItem();
    if (filmSelection == null || filmSelection.isEmpty()) {
      listeGenresFilm.getItems().add("Sélectionnez un film.");
      return;
    }
    
    String[] film = filmSelection.split(" ");
    String genreInput = listeTousGenres.getSelectionModel().getSelectedItem();
    
    if (filmSelection == null || genreInput == null) {
      listeGenresFilm.getItems()
          .add(filmSelection == null ? "Sélectionnez un film."
              : "Sélectionnez un genre.");
      return;
    }
    
    Film films = administration.getFilm(film[0]);
    Genre genre = Genre.valueOf(genreInput);
    
    if (film != null && genre != null
        && administration.ajouterGenres(films, genre)) {
      listeGenresFilm.getItems().add("Genre ajouté avec succès.");
    } else {
      listeGenresFilm.getItems().add("Échec de l'ajout ou genre existant.");
    }
  }
  
  @FXML
  void actionBoutonChercherArtiste(ActionEvent event) {
    listeArtistes.getItems().clear();
    String nom = entreeNomArtiste.getText();
    String prenom = entreePrenomArtiste.getText();
    
    if (nom == null || prenom == null || nom.isEmpty() || prenom.isEmpty()) {
      listeArtistes.getItems().add("Entrez un nom et un prénom.");
      return;
    }
    
    Artiste artiste = administration.getArtiste(nom, prenom);
    listeArtistes.getItems()
        .add(artiste == null ? "Artiste introuvable."
            : artiste.getNom() + " " + artiste.getPrenom() + " - "
                + artiste.getNationalite());
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    
    listeFilms.getItems().clear();
    String titre = entreeTitreFilm.getText();
    
    if (titre == null || titre.isEmpty()) {
      listeFilms.getItems().add("Entrez un titre de film.");
      return;
    }
    
    Film film = administration.getFilm(titre);
    listeFilms.getItems()
        .add(film == null ? "Film introuvable."
            : film.getTitre() + " - (" + film.getAnnee() + ") - Réalisé par : "
                + film.getRealisateur().getNom() + " "
                + film.getRealisateur().getPrenom());
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    // Efface les éléments existants dans la liste avant d'ajouter un nouveau
    // message.
    listeArtistes.getItems().clear();
    
    try {
      // Vérifie si un élément est sélectionné dans la liste.
      String selection = listeArtistes.getSelectionModel().getSelectedItem();
      if (selection == null || selection.isEmpty()) {
        listeArtistes.getItems().add("Veuillez sélectionner un réalisateur !");
        return;
      }
      
      // Affiche la sélection dans le champ texte correspondant.
      entreeNomPrenomRealisateur.setText(selection);
      
      // Affiche un message de succès.
      listeArtistes.getItems().add("Réalisateur sélectionné : " + selection);
      
    } catch (Exception e) {
      // En cas d'erreur, ajoute un message d'erreur dans la liste.
      listeArtistes.getItems().add("Erreur : " + e.getMessage());
    }
  }
  
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    
    // Efface les éléments existants dans la liste avant d'ajouter un nouveau
    // message.
    listeArtistes.getItems().clear();
    
    // Vérifications des champs requis.
    if (entreeNomArtiste.getText().isEmpty()) {
      listeArtistes.getItems().add("Le nom de l'artiste est requis !");
      return;
    }
    if (entreePrenomArtiste.getText().isEmpty()) {
      listeArtistes.getItems().add("Le prénom de l'artiste est requis !");
      return;
    }
    if (entreeNationaliteArtiste.getText().isEmpty()) {
      listeArtistes.getItems().add("La nationalité de l'artiste est requise !");
      return;
    }
    
    // Déclaration de la variable artiste
    Artiste artiste = null;
    
    try {
      // Création de l'artiste
      artiste = administration.creerArtiste(entreeNomArtiste.getText(),
          entreePrenomArtiste.getText(), entreeNationaliteArtiste.getText());
      
      // Vérifie si l'artiste a été créé avec succès.
      if (artiste == null) {
        listeArtistes.getItems().add("La création de l'artiste a échoué !");
        actionBoutonNouveauArtiste(event);
        return;
      }
      
      // Mise à jour de la liste avec les artistes existants après l'ajout.
      for (Artiste e : administration.ensembleActeurs()) {
        listeArtistes.getItems()
            .add(e.getNom() + " " + e.getPrenom() + " - " + e.getNationalite());
      }
      
      // Affiche un message de succès après ajout de l'artiste.
      listeArtistes.getItems().add("L'artiste '" + artiste.getNom() + " "
          + artiste.getPrenom() + "' a été ajouté avec succès !");
      
    } catch (Exception e) {
      listeArtistes.getItems().add("Erreur inattendue : " + e.getMessage());
    }
  }
  
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    
    // Efface les éléments existants dans la liste avant d'ajouter un nouveau
    // message.
    listeFilms.getItems().clear();
    
    // Vérifications des champs requis.
    if (entreeTitreFilm.getText().isEmpty()) {
      listeFilms.getItems().add("Le titre du film est requis !");
      return;
    }
    if (entreeAnneeFilm.getText().isEmpty()
        || Integer.parseInt(entreeAnneeFilm.getText()) < 1895) {
      listeFilms.getItems().add(
          "L'année de réalisation du film est requise et doit être après 1895 !");
      return;
    }
    
    if (listeArtistes.getSelectionModel().getSelectedItem() == null) { // Vérifie
                                                                       // si un
                                                                       // artiste
                                                                       // est
                                                                       // sélectionné
      listeFilms.getItems().add("Veuillez sélectionner un réalisateur !");
      return;
    }
    
    // Déclaration de la variable film en dehors des blocs if et else
    Film film = null;
    
    try {
      // Récupération de l'artiste sélectionné dans la liste
      String selection = listeArtistes.getSelectionModel().getSelectedItem();
      
      // Récupère l'objet artiste depuis l'administration
      String[] artisteInfo = selection.split(" - ");
      String[] nomPrenom = artisteInfo[0].split(" ");
      Artiste realisateur =
          administration.getArtiste(nomPrenom[0], nomPrenom[1]);
      administration.setRealisateur(realisateur);
      
      
      // Vérifie que le réalisateur existe
      if (realisateur == null) {
        listeFilms.getItems()
            .add("Le réalisateur sélectionné est introuvable !");
        return;
      }
      
      // Création du film avec âge minimum par défaut à 0 s'il n'est pas
      // spécifié
      int ageMinimum = listeChoixAgeLimite.getValue() == null ? 0
          : Integer.parseInt(listeChoixAgeLimite.getValue());
      
      // Création du film
      film = administration.creerFilm(entreeTitreFilm.getText(), realisateur, // Utilise
                                                                              // l'artiste
                                                                              // sélectionné
                                                                              // comme
                                                                              // réalisateur
          Integer.parseInt(entreeAnneeFilm.getText()), ageMinimum);
      
      // Vérification et mise à jour de l'état de location
      if (checkBoxLocationFilm.isSelected()) {
        administration.ouvrirLocation(film); // ou film.mettreEnLocation() selon
                                             // votre implémentation
      }
      // Vérifie si le film a été créé avec succès.
      if (film == null) {
        listeFilms.getItems().add("La création du film a échoué !");
        actionBoutonNouveauFilm(event);
        return;
      }
      
      // Mise à jour de la liste avec les films existants après l'ajout.
      for (Film e : administration.ensembleFilms()) {
        listeFilms.getItems().add(e.toString());
      }
      
      // Affiche un message de succès après ajout du film.
      listeFilms.getItems()
          .add("Le film '" + film.getTitre() + "' a été ajouté avec succès !");
      
    } catch (NumberFormatException e) {
      listeFilms.getItems().add("Erreur : Année ou âge minimum invalide !");
    } catch (Exception e) {
      listeFilms.getItems().add("Erreur inattendue : " + e.getMessage());
    }
  }
  
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    // Réinitialise les champs pour l'ajout d'un nouvel artiste
    entreeNomArtiste.clear();
    entreePrenomArtiste.clear();
    entreeNationaliteArtiste.clear();
    listeArtistes.getSelectionModel().clearSelection();
    listeArtistes.getItems().clear();
    listeArtistes.getItems()
        .add("Remplissez les champs pour ajouter un nouvel artiste.");
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    // Réinitialise les champs pour l'ajout d'un nouveau film
    entreeTitreFilm.clear();
    entreeAnneeFilm.clear();
    listeChoixAgeLimite.getSelectionModel().clearSelection();
    checkBoxLocationFilm.setSelected(false);
    entreeAffiche.clear();
    entreeNomPrenomRealisateur.clear();
    listeFilms.getSelectionModel().clearSelection();
    listeFilms.getItems().clear();
    listeFilms.getItems()
        .add("Remplissez les champs pour ajouter un nouveau film.");
    listeGenresFilm.getItems().clear();
  }
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    
    // Efface les éléments existants dans la liste avant d'ajouter un nouveau
    // message.
    listeFilms.getItems().clear();
    
    try {
      // Vérifie si un film est sélectionné dans la liste.
      if (listeFilms.getSelectionModel().getSelectedItem() == null) {
        listeFilms.getItems().add("Veuillez sélectionner un film !");
        return;
      }
      
      // Recherche le film en fonction du titre saisi.
      Film film = administration.getFilm(entreeTitreFilm.getText());
      if (film == null) {
        listeFilms.getItems().add("Film introuvable !");
        return;
      }
      
      // Création du sélecteur de fichiers pour l'image.
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Sélectionner une affiche pour le film");
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
          "Images", "*.jpg", "*.png", "*.gif", "*.avi"));
      
      // Ouverture de la boîte de dialogue pour sélectionner un fichier.
      Stage stage = (Stage) entreeAffiche.getScene().getWindow();
      File selectedFile = fileChooser.showOpenDialog(stage);
      
      if (selectedFile != null) {
        // Vérifie et crée le répertoire "images" s'il n'existe pas.
        File imagesDir = new File("images");
        if (!imagesDir.exists()) {
          imagesDir.mkdir();
        }
        
        // Copie l'image sélectionnée dans le dossier "images/".
        File destinationFile = new File(imagesDir, selectedFile.getName());
        Files.copy(selectedFile.toPath(), destinationFile.toPath(),
            StandardCopyOption.REPLACE_EXISTING);
        
        // Définit le chemin relatif pour l'affiche.
        String relativePath = "./images/" + selectedFile.getName();
        entreeAffiche.setText(relativePath);
        film.setAffiche(relativePath);
        
        // Ajoute un message de succès dans la liste.
        listeFilms.getItems()
            .add("Affiche mise à jour avec succès pour le film : "
                + film.getTitre());
        
      } else {
        listeFilms.getItems().add("Aucun fichier sélectionné !");
      }
      
    } catch (IOException e) {
      listeFilms.getItems()
          .add("Erreur lors de la copie de l'image : " + e.getMessage());
    } catch (Exception e) {
      listeFilms.getItems()
          .add("Erreur inattendue lors de la sélection de l'affiche : "
              + e.getMessage());
    }
  }
  
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    String selection = listeArtistes.getSelectionModel().getSelectedItem();
    
    listeArtistes.getItems().clear();
    if (selection == null || selection.isEmpty()) {
      listeArtistes.getItems().add("Sélectionnez un artiste à supprimer.");
      return;
    }
    
    String[] info = selection.split(" ");
    boolean success = administration
        .supprimerArtiste(administration.getArtiste(info[1], info[0]));
    
    listeArtistes.getItems().add(success ? "Artiste supprimé avec succès."
        : "Échec de la suppression de l'artiste.");
  }
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    String selection = listeFilms.getSelectionModel().getSelectedItem();
    listeFilms.getItems().clear();
    
    if (selection == null || selection.isEmpty()) {
      listeFilms.getItems().add("Sélectionnez un film à supprimer.");
      return;
    }
    
    String titre = selection.split(" ")[0];
    Film film = administration.getFilm(titre);
    
    if (film == null) {
      listeFilms.getItems().add("Film introuvable.");
      return;
    }
    
    boolean success = administration.supprimerFilm(film);
    
    if (success) {
      listeFilms.getItems().add("Film supprimé avec succès.");
    } else {
      listeFilms.getItems().add("Échec de la suppression.");
    }
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    // Crée une boîte de dialogue de type Information
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("À Propos");
    alert.setHeaderText("CineRent - Application de gestion de films");
    alert.setContentText(
        "Version 1.0\nDéveloppée par : Nachid Ayman - Akbal Lyna - Ayad Oumaina  - Zeroil Ahlam "
            + "\nDescription : "
            + "Cette application permet de gérer les films, les réalisateurs et les acteurs, "
            + "ainsi que les locations de films.");
    
    // Affiche la boîte de dialogue et attend la fermeture
    alert.showAndWait();
  }
  
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    try {
      String nomFichier = "donnees.txt";
      
      // Créer une instance de Sauvegarde
      Sauvegarde sauvegarde = new Sauvegarde(administration);
      
      // Charger les données
      sauvegarde.chargerDonnees(nomFichier);
      
      // Mettre à jour les instances après le chargement
      administration = sauvegarde.getAdministrateur();
      
      // Mettre à jour l'interface utilisateur
      listeFilms.getItems().setAll(
          administration.ensembleFilms().stream().map(Film::toString).toList());
      
      listeArtistes.getItems().setAll(administration.ensembleActeurs().stream()
          .map(Artiste::toString).toList());
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Données chargées avec succès depuis " + nomFichier + " !");
      alert.showAndWait();
      
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Erreur lors du chargement : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    // Ferme toutes les fenêtres ouvertes et arrête l'application proprement
    javafx.application.Platform.exit(); // Ferme toutes les fenêtres JavaFX
    System.exit(0); // Quitte le processus
  }
  
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      String nomFichier = "donnees.txt";
      
      
      // Créer une instance de Sauvegarde
      Sauvegarde sauvegarde = new Sauvegarde(administration);
      
      // Sauvegarder les données
      sauvegarde.sauvegarderDonnees(nomFichier);
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Données sauvegardées avec succès dans " + nomFichier + " !");
      alert.showAndWait();
      
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Erreur lors de la sauvegarde : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    String selection = listeArtistes.getSelectionModel().getSelectedItem();
    if (selection != null && !selection.isEmpty()) {
      String[] info = selection.split(" ");
      Artiste artiste = administration.getArtiste(info[0], info[1]);
      if (artiste != null) {
        entreeNomArtiste.setText(artiste.getNom());
        entreePrenomArtiste.setText(artiste.getPrenom());
        entreeNationaliteArtiste.setText(artiste.getNationalite());
      }
    }
    
  }
  
  @FXML
  void actionListeSelectionFilm(MouseEvent event) {
    // Récupère l'élément sélectionné dans la liste des films
    String selection = listeFilms.getSelectionModel().getSelectedItem();
    
    // Vérifie si une sélection a été effectuée
    if (selection != null && !selection.isEmpty()) {
      // Sépare les informations du film en utilisant " - " comme délimiteur
      String[] info = selection.split(" - ");
      
      // Vérifie si la sélection contient suffisamment d'informations
      if (info.length >= 3) {
        // Récupère le film à partir de son titre
        Film film = administration.getFilm(info[0]); // info[0] contient le
                                                     // titre du film
        
        // Vérifie si le film existe
        if (film != null) {
          // Remplit les champs avec les informations récupérées
          entreeTitreFilm.setText(film.getTitre());
          entreeAnneeFilm.setText(String.valueOf(film.getAnnee()));
          listeChoixAgeLimite.setValue(String.valueOf(film.getAgeMinimum())); // Sélectionne
                                                                              // l'âge
                                                                              // limite
          entreeNomPrenomRealisateur.setText(film.getRealisateur().getNom()
              + " " + film.getRealisateur().getPrenom());
        } else {
          // Message si le film est introuvable
          listeFilms.getItems().add("Film introuvable !");
        }
      } else {
        // Message si les informations sont incomplètes
        listeFilms.getItems().add("Informations incomplètes !");
      }
    }
  }
  
  
  @FXML
  void initialize() {
    // Initialisation des artistes
    labelListeArtistes.setText("Tous les artistes de l'application : ");
    listeArtistes.getItems().clear();
    
    // Charger les acteurs
    Set<Artiste> acteurs = administration.ensembleActeurs();
    if (acteurs.isEmpty()) {
      listeArtistes.getItems().add("Aucun acteur trouvé !");
    } else {
      for (Artiste e : acteurs) {
        listeArtistes.getItems().add(e.toString());
      }
    }
    
    // Charger les réalisateurs
    Set<Artiste> realisateurs = administration.ensembleRealisateurs();
    if (realisateurs.isEmpty()) {
      listeArtistes.getItems().add("Aucun réalisateur trouvé !");
    } else {
      for (Artiste e : realisateurs) {
        listeArtistes.getItems().add(e.toString());
      }
    }
    
    // Initialisation des films
    labelListeFilms.setText("Tous les films de l'application :");
    listeFilms.getItems().clear();
    
    // Chargement des âges limites
    for (int i = 8; i <= 18; i++) {
      listeChoixAgeLimite.getItems().add(String.valueOf(i)); // Ajout des âges
                                                             // en tant que
                                                             // String
    }
    
    // Chargement des genres
    for (Genre e : Genre.values()) {
      listeTousGenres.getItems().add(e.toString());
    }
    
    // Charger les films
    Set<Film> films = administration.ensembleFilms();
    if (films.isEmpty()) {
      listeFilms.getItems().add("Aucun film disponible !");
    } else {
      for (Film e : films) {
        listeFilms.getItems().add(e.toString());
      }
    }
  }
  
}
