package ui;

import java.io.IOException;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import location.Administrateur;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.GestionUtilisateur;
import location.InformationPersonnelle;
import location.LocationException;
import location.NonConnecteException;
import location.Utilisateur;
import io.Sauvegarde;

/**
 * Controleur JavaFX de la fenêtre utilisateur.
 *
 * @author Eric Cariou
 *
 */
public class UtilisateurControleur {
  

  
  
  
  @FXML
  private CheckBox checkFilmLouable;
  
  @FXML
  private TextField entreeAdresseUtilisateur;
  
  @FXML
  private TextField entreeAgeLimiteFilm;
  
  @FXML
  private TextField entreeAgeUtilisateur;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeAuteurEvaluation;
  
  @FXML
  private TextField entreeEvaluationMoyenne;
  
  @FXML
  private TextField entreeGenresFilm;
  
  @FXML
  private TextField entreeMotDePasseUtilisateur;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateurFilm;
  
  @FXML
  private TextField entreeNomUtilisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreePrenomUtilisateur;
  
  @FXML
  private TextField entreePseudoUtilisateur;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ListView<String> listeEvaluations;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeFilmsEnLocation;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ChoiceBox<Integer> listeNoteEvaluation;
  
  @FXML
  private TextArea texteCommentaire;
  
  @FXML
  private StackPane paneAffiche;
  
  @FXML
  void actionBoutonAfficherActeursFilmSelectionne(ActionEvent event) {
    try {
      // Récupérer le film sélectionné dans la liste
      String titreFilmSelectionne =
          listeFilms.getSelectionModel().getSelectedItem();
      
      if (titreFilmSelectionne == null || titreFilmSelectionne.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText("Veuillez sélectionner un film dans la liste.");
        alert.showAndWait();
        return;
      }
      
      // Obtenir le film
      Film film = utilisation.getFilm(titreFilmSelectionne);
      
      if (film != null) {
        // Afficher la liste des acteurs dans la section "Artiste"
        listeArtistes.getItems().clear();
        for (Artiste acteur : film.getActeurs()) {
          listeArtistes.getItems()
              .add(acteur.getPrenom() + " " + acteur.getNom());
        }
        labelListeArtistes.setText("Acteurs du film : " + film.getTitre());
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Film introuvable");
        alert.setHeaderText("Aucun film correspondant");
        alert.setContentText("Impossible de trouver les acteurs pour ce film.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmLoue(ActionEvent event) {


      String selection = listeFilmsEnLocation.getSelectionModel().getSelectedItem();
      // Nettoyer la liste des films en location avant d'ajouter des éléments
      listeFilmsEnLocation.getItems().clear();
      // Sépare le prénom et le nom de l'acteur sélectionné
      String[] info = selection.split(" ");
      try {
          // Vérifier si l'utilisateur est connecté
          if (utilisation.getUtilisateurConnecte() == null) {
              // Afficher un message d'erreur si aucun utilisateur n'est connecté
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Utilisateur non connecté");
              alert.setHeaderText("Aucun utilisateur connecté");
              alert.setContentText("Veuillez vous connecter pour voir les films en location.");
              alert.showAndWait();
              return;
          }

          // Récupérer les films en location par l'utilisateur connecté
          Film films = utilisation.getFilm(info[0]);

          // Vérifier si aucun film n'est en location
          if (films == null ) {
              listeFilmsEnLocation.getItems().add("Aucun film en location.");
          } else {
            entreeTitreFilm.setText(films.getTitre());
            entreeAnneeFilm.setText(String.valueOf(films.getAnnee()));
            entreeAgeLimiteFilm.setText(String.valueOf(films.getAgeMinimum()));
            entreeNomPrenomRealisateurFilm.setText(films.getRealisateur().getNom() + " " + films.getRealisateur().getPrenom()  );              
          }
      } catch (NonConnecteException e) {
          // Gérer le cas où l'utilisateur n'est pas connecté
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur");
          alert.setHeaderText("Utilisateur non connecté");
          alert.setContentText("Erreur : " + e.getMessage());
          alert.showAndWait();
      }
  }

  
  @FXML
  void actionBoutonAfficherFilmRealisateurSelectionne(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsGenre(ActionEvent event) {
    try {
      // Récupérer le genre sélectionné
      String genreSelectionne =
          listeGenresFilm.getSelectionModel().getSelectedItem();
      
      if (genreSelectionne == null || genreSelectionne.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Genre non sélectionné");
        alert.setHeaderText("Aucun genre sélectionné");
        alert.setContentText(
            "Veuillez sélectionner un genre pour afficher les films.");
        alert.showAndWait();
        return;
      }
      
      // Récupérer les films du genre sélectionné
      Set<Film> films = utilisation.ensembleFilmsGenre(genreSelectionne);
      
      if (films != null && !films.isEmpty()) {
        listeFilms.getItems().clear();
        for (Film film : films) {
          listeFilms.getItems()
              .add(film.getTitre() + " (" + film.getAnnee() + ")");
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucun film trouvé");
        alert.setHeaderText("Pas de films pour ce genre");
        alert.setContentText(
            "Aucun film n'est disponible pour le genre sélectionné.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    try {
      // Récupérer le réalisateur sélectionné
      String realisateurSelectionne =
          listeArtistes.getSelectionModel().getSelectedItem();
      
      if (realisateurSelectionne == null || realisateurSelectionne.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Réalisateur non sélectionné");
        alert.setHeaderText("Aucun réalisateur sélectionné");
        alert.setContentText(
            "Veuillez sélectionner un réalisateur pour afficher ses films.");
        alert.showAndWait();
        return;
      }
      
      // Vérifier si le format est correct
      if (!realisateurSelectionne.contains(" ")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Réalisateur invalide");
        alert.setHeaderText("Format incorrect");
        alert.setContentText(
            "Le nom du réalisateur doit être au format 'Prénom Nom'.");
        alert.showAndWait();
        return;
      }
      
      // Diviser en nom et prénom
      String[] nomPrenom = realisateurSelectionne.split(" ");
      String nom = nomPrenom[1]; // Nom
      String prenom = nomPrenom[0]; // Prénom
      
      // Debug : Afficher les données récupérées
      System.out.println("Nom : " + nom + ", Prénom : " + prenom);
      
      // Récupérer les films du réalisateur
      Set<Film> films = utilisation.ensembleFilmsRealisateur(nom, prenom);
      
      if (films != null && !films.isEmpty()) {
        listeFilms.getItems().clear();
        for (Film film : films) {
          listeFilms.getItems()
              .add(film.getTitre() + " (" + film.getAnnee() + ")");
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucun film trouvé");
        alert.setHeaderText("Pas de films pour ce réalisateur");
        alert.setContentText(
            "Aucun film n'est disponible pour le réalisateur sélectionné.");
        alert.showAndWait();
      }
    } catch (NullPointerException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur");
      alert.setHeaderText("Données manquantes");
      alert.setContentText(
          "Le réalisateur ou les données des films sont manquants.");
      alert.showAndWait();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionBoutonAfficherMonEvaluation(ActionEvent event) {
    try {
      // Récupérer le film sélectionné dans la liste
      String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
      
      if (titreFilm == null || titreFilm.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText(
            "Veuillez sélectionner un film pour afficher ses évaluations.");
        alert.showAndWait();
        return;
      }
      
      // Récupérer les évaluations du film
      Set<Evaluation> evaluations =
          utilisation.ensembleEvaluationsFilm(titreFilm);
      
      if (evaluations != null && !evaluations.isEmpty()) {
        listeEvaluations.getItems().clear();
        for (Evaluation eval : evaluations) {
          listeEvaluations.getItems().add(eval.toString());
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucune évaluation trouvée");
        alert.setHeaderText("Pas d'évaluations");
        alert.setContentText(
            "Aucune évaluation n'est disponible pour le film sélectionné.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherTousFilms(ActionEvent event) {
    try {
      // Obtenir la liste de tous les films
      Set<Film> films = utilisation.ensembleFilms();
      
      // Vérifier si des films existent
      if (films != null && !films.isEmpty()) {
        listeFilms.getItems().clear();
        for (Film film : films) {
          listeFilms.getItems().add(film.getTitre());
        }
        labelListeFilms.setText("Tous les films disponibles");
      } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucun film");
        alert.setHeaderText("Pas de films disponibles");
        alert.setContentText(
            "Il n'y a actuellement aucun film dans l'application.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonChercherActeur(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    try {
      // Récupérer le titre du film depuis le champ texte
      String titre = entreeTitreFilm.getText().trim();
      
      // Vérifier si le champ est vide
      if (titre.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de recherche");
        alert.setHeaderText("Titre manquant");
        alert.setContentText("Veuillez entrer un titre de film.");
        alert.showAndWait();
        return;
      }
      
      // Chercher le film
      Film film = utilisation.getFilm(titre);
      
      if (film != null) {
        // Remplir les champs avec les informations du film trouvé
        entreeAnneeFilm.setText(String.valueOf(film.getAnnee()));
        entreeAgeLimiteFilm.setText(String.valueOf(film.getAgeMinimum()));
        entreeNomPrenomRealisateurFilm.setText(film.getRealisateur().getPrenom()
            + " " + film.getRealisateur().getNom());
        entreeGenresFilm.setText(film.getGenres().toString());
        checkFilmLouable.setSelected(film.isEnLocation());
      } else {
        // Si aucun film n'est trouvé
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Film introuvable");
        alert.setHeaderText("Aucun film trouvé");
        alert.setContentText("Aucun film ne correspond à ce titre.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      // Gestion des exceptions
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  @FXML
  void actionBoutonChercherRealisateur(ActionEvent event) {
    
  }
  
@FXML
void actionBoutonConnexion(ActionEvent event) {
    try {
        // Récupérer les données utilisateur depuis les champs texte
        String pseudo = entreePseudoUtilisateur.getText();
        String motDePasse = entreeMotDePasseUtilisateur.getText();
        
        // Vérifier si les champs pseudo et mot de passe ne sont pas vides
        if (pseudo.isEmpty() || motDePasse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText("Champs vides");
            alert.setContentText("Veuillez entrer un pseudo et un mot de passe.");
            alert.showAndWait();
            return;
        }
        
        // Appeler la méthode connexion
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur(); // Instance de gestion
        Utilisateur utilisateurConnecte = gestionUtilisateur.connexion(pseudo, motDePasse);
        
        if (utilisateurConnecte != null) {
            // Mettre à jour l'instance `utilisation` avec l'utilisateur connecté
            utilisation = utilisateurConnecte;

            // Afficher un popup de connexion réussie
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connexion réussie");
            alert.setHeaderText("Bienvenue !");
            alert.setContentText("Vous êtes connecté avec succès !");
            alert.showAndWait();
            
            // Récupérer et afficher les informations personnelles
            InformationPersonnelle info = utilisateurConnecte.getInfo();
            if (info != null) {
                // Affiche les informations personnelles dans les champs
                entreeNomUtilisateur.setText(info.getNom());
                entreePrenomUtilisateur.setText(info.getPrenom());
                entreeAdresseUtilisateur.setText(info.getAdresse());
                entreeAgeUtilisateur.setText(String.valueOf(info.getAge()));
                Set<Film> films = utilisation.filmsEnLocation();
                if (films != null && !films.isEmpty()) {
                    // Effacer les anciens éléments du ListView
                    listeFilmsEnLocation.getItems().clear();
                    
                    // Ajouter les titres des films au ListView
                    films.stream()
                         .map(Film::getTitre) // Extraire le titre de chaque film
                         .forEach(titre -> listeFilmsEnLocation.getItems().add(titre));
                } else {
                    // Si aucun film n'est en location, afficher un message dans le ListView
                    listeFilmsEnLocation.getItems().clear();
                    listeFilmsEnLocation.getItems().add("Aucun film en location.");
                }


            } else {
                Alert noInfoAlert = new Alert(Alert.AlertType.WARNING);
                noInfoAlert.setTitle("Informations manquantes");
                noInfoAlert.setHeaderText("Aucune information personnelle trouvée");
                noInfoAlert.setContentText("Certaines données sont absentes.");
                noInfoAlert.showAndWait();
            }
        } else {
            // Afficher un popup en cas d'échec de connexion
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText("Connexion échouée");
            alert.setContentText("Pseudo ou mot de passe incorrect.");
            alert.showAndWait();
            
            // Effacer les champs pseudo et mot de passe
            entreePseudoUtilisateur.clear();
            entreeMotDePasseUtilisateur.clear();
        }
    } catch (Exception e) {
        // Gérer les exceptions inattendues via une popup d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur inattendue");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText("Détails : " + e.getMessage());
        alert.showAndWait();
    }
}

  @FXML
  void actionBoutonCreerMonEvaluation(ActionEvent event) {
    try {
      // Récupérer les données de l'évaluation
      String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
      String commentaire = texteCommentaire.getText();
      Integer note = listeNoteEvaluation.getValue();
      
      if (titreFilm == null || titreFilm.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText(
            "Veuillez sélectionner un film pour créer une évaluation.");
        alert.showAndWait();
        return;
      }
      
      if (note == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Note non sélectionnée");
        alert.setHeaderText("Aucune note sélectionnée");
        alert.setContentText(
            "Veuillez sélectionner une note pour l'évaluation.");
        alert.showAndWait();
        return;
      }
      
      // Créer une nouvelle évaluation
      Evaluation evaluation =
          new Evaluation(commentaire, null, note, commentaire);
      
      // Ajouter l'évaluation
      utilisation.ajouterEvaluation(utilisation.getFilm(titreFilm), evaluation);
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Évaluation créée");
      alert.setHeaderText("Succès");
      alert.setContentText("Votre évaluation a été ajoutée avec succès !");
      alert.showAndWait();
      
      // Effacer les champs après création
      texteCommentaire.clear();
      listeNoteEvaluation.setValue(null);
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    try {
      // Tenter de déconnecter l'utilisateur
      utilisation.deconnexion();
      
      // Afficher un popup pour indiquer la déconnexion réussie
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Déconnexion");
      alert.setHeaderText("Déconnexion réussie");
      alert.setContentText("Vous êtes maintenant déconnecté !");
      alert.showAndWait();
      
      // Effacer les champs du formulaire après déconnexion
      entreePseudoUtilisateur.clear();
      entreeMotDePasseUtilisateur.clear();
      entreeNomUtilisateur.clear();
      entreePrenomUtilisateur.clear();
      entreeAdresseUtilisateur.clear();
      entreeAgeUtilisateur.clear();
    } catch (Exception e) {
      // Afficher un popup pour indiquer qu'aucun utilisateur n'est connecté
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur de déconnexion");
      alert.setHeaderText("Déconnexion impossible");
      alert.setContentText("Aucun utilisateur n'est actuellement connecté.");
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionBoutonFinLocation(ActionEvent event) {
    try {
      // Récupérer le film sélectionné dans la liste des films en location
      String titreFilmSelectionne =
          listeFilmsEnLocation.getSelectionModel().getSelectedItem();
      
      if (titreFilmSelectionne == null || titreFilmSelectionne.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText("Veuillez sélectionner un film à retourner.");
        alert.showAndWait();
        return;
      }
      
      // Obtenir le film
      Film film = utilisation.getFilm(titreFilmSelectionne);
      
      if (film != null) {
        // Retourner le film
        utilisation.finLocationFilm(film);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Film retourné");
        alert.setHeaderText("Retour réussi");
        alert.setContentText(
            "Le film '" + film.getTitre() + "' a été retourné avec succès !");
        alert.showAndWait();
        
        // Supprimer le film de la liste des films en location
        listeFilmsEnLocation.getItems().remove(titreFilmSelectionne);
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Film introuvable");
        alert.setHeaderText("Impossible de retourner le film");
        alert.setContentText("Le film sélectionné n'existe pas.");
        alert.showAndWait();
      }
    } catch (LocationException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Erreur de retour");
      alert.setHeaderText("Impossible de retourner le film");
      alert.setContentText("Raison : " + e.getMessage());
      alert.showAndWait();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    try {
      // Vérifier si l'objet `utilisation` est bien initialisé
      if (utilisation == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'inscription");
        alert.setHeaderText("Objet non initialisé");
        alert.setContentText("L'objet utilisateur n'est pas initialisé.");
        alert.showAndWait();
        return;
      }
      
      // Récupérer les données utilisateur depuis les champs texte
      String pseudo = entreePseudoUtilisateur.getText().trim();
      String motDePasse = entreeMotDePasseUtilisateur.getText().trim();
      String nom = entreeNomUtilisateur.getText().trim();
      String prenom = entreePrenomUtilisateur.getText().trim();
      String adresse = entreeAdresseUtilisateur.getText().trim();
      String ageInput = entreeAgeUtilisateur.getText().trim();
      
      // Vérifier si tous les champs obligatoires sont remplis
      if (pseudo.isEmpty() || motDePasse.isEmpty() || nom.isEmpty()
          || prenom.isEmpty() || ageInput.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'inscription");
        alert.setHeaderText("Champs obligatoires manquants");
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");
        alert.showAndWait();
        return;
      }
      
      // Vérifier si l'âge est un nombre valide
      int age = 0;
      try {
        age = Integer.parseInt(ageInput);
        if (age < 0) {
          throw new NumberFormatException("L'âge ne peut pas être négatif.");
        }
      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'inscription");
        alert.setHeaderText("Âge invalide");
        alert.setContentText(
            "Veuillez entrer un âge valide (un nombre entier non négatif).");
        alert.showAndWait();
        return;
      }
      
      // Vérifier si le mot de passe est suffisamment sécurisé (exemple :
      // longueur minimale)
      if (motDePasse.length() < 6) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'inscription");
        alert.setHeaderText("Mot de passe trop court");
        alert.setContentText(
            "Le mot de passe doit contenir au moins 6 caractères.");
        alert.showAndWait();
        // Effacer tous les champs après une inscription réussie
        entreePseudoUtilisateur.clear();
        entreeMotDePasseUtilisateur.clear();
        return;
      }
      
      // Créer un objet `InformationPersonnelle`
      InformationPersonnelle info =
          new InformationPersonnelle(nom, prenom, adresse, age);
      
      // Appeler la méthode `inscription`
      int resultat = utilisation.inscription(pseudo, motDePasse, info);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Inscription état");
      
      // Gérer les résultats de l'inscription
      switch (resultat) {
        case 0: // Inscription réussie
          alert.setHeaderText("Inscription réussie");
          alert.setContentText(
              "Votre inscription a été effectuée avec succès !");
          alert.showAndWait();
          
          // Effacer tous les champs après une inscription réussie
          entreePseudoUtilisateur.clear();
          entreeMotDePasseUtilisateur.clear();
          entreeNomUtilisateur.clear();
          entreePrenomUtilisateur.clear();
          entreeAdresseUtilisateur.clear();
          entreeAgeUtilisateur.clear();
          break;
        case 1: // Pseudo déjà utilisé
          alert.setHeaderText("Erreur d'inscription");
          alert.setContentText("Le pseudo est déjà utilisé !");
          alert.showAndWait();
          
          // Effacer seulement les champs pseudo et mot de passe
          entreePseudoUtilisateur.clear();
          entreeMotDePasseUtilisateur.clear();
          break;
        case 2: // Pseudo ou mot de passe vide
          alert.setHeaderText("Erreur d'inscription");
          alert.setContentText("Le pseudo ou le mot de passe est vide !");
          alert.showAndWait();
          
          // Effacer seulement les champs pseudo et mot de passe
          entreePseudoUtilisateur.clear();
          entreeMotDePasseUtilisateur.clear();
          break;
        case 3: // Informations personnelles invalides
          alert.setHeaderText("Erreur d'inscription");
          alert
              .setContentText("Les informations personnelles sont invalides !");
          alert.showAndWait();
          
          // Effacer seulement les champs pseudo et mot de passe
          entreePseudoUtilisateur.clear();
          entreeMotDePasseUtilisateur.clear();
          break;
        default: // Erreur inconnue
          alert.setHeaderText("Erreur inconnue");
          alert.setContentText("Une erreur inattendue s'est produite !");
          alert.showAndWait();
          
          // Effacer seulement les champs pseudo et mot de passe
          entreePseudoUtilisateur.clear();
          entreeMotDePasseUtilisateur.clear();
          break;
      }
    } catch (Exception e) {
      // Gérer les exceptions inattendues via une popup d'erreur
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur est survenue");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionBoutonLouerFilmSelectionne(ActionEvent event) {
    try {
      // Récupérer le film sélectionné dans la liste
      String titreFilmSelectionne =
          listeFilms.getSelectionModel().getSelectedItem();
      
      if (titreFilmSelectionne == null || titreFilmSelectionne.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText("Veuillez sélectionner un film à louer.");
        alert.showAndWait();
        return;
      }
      
      // Obtenir le film
      Film film = utilisation.getFilm(titreFilmSelectionne);
      
      if (film != null && film.isEnLocation()) {
        // Louer le film
        utilisation.louerFilm(film);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Location réussie");
        alert.setHeaderText("Film loué");
        alert.setContentText(
            "Le film \"" + film.getTitre() + "\" a été loué avec succès !");
        alert.showAndWait();
        
        // Mettre à jour la liste des films en location
        listeFilmsEnLocation.getItems().add(film.getTitre());
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Film non disponible");
        alert.setHeaderText("Impossible de louer le film");
        alert.setContentText(
            "Le film sélectionné n'est pas disponible à la location.");
        alert.showAndWait();
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur s'est produite");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  @FXML
  void actionBoutonModifierMonEvaluation(ActionEvent event) {
    try {
      // Récupérer le film sélectionné dans la liste
      String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
      
      // Vérifier si un film est sélectionné
      if (titreFilm == null || titreFilm.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Film non sélectionné");
        alert.setHeaderText("Aucun film sélectionné");
        alert.setContentText(
            "Veuillez sélectionner un film pour modifier une évaluation.");
        alert.showAndWait();
        return;
      }
      
      // Récupérer la nouvelle note et le commentaire
      Integer nouvelleNote = listeNoteEvaluation.getValue();
      String nouveauCommentaire = texteCommentaire.getText();
      
      // Vérifier si une nouvelle note est sélectionnée
      if (nouvelleNote == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Note non sélectionnée");
        alert.setHeaderText("Aucune note sélectionnée");
        alert.setContentText(
            "Veuillez sélectionner une note pour l'évaluation.");
        alert.showAndWait();
        return;
      }
      
      // Vérifier si un commentaire est fourni
      if (nouveauCommentaire == null || nouveauCommentaire.trim().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Commentaire manquant");
        alert.setHeaderText("Aucun commentaire saisi");
        alert.setContentText(
            "Veuillez saisir un commentaire pour l'évaluation.");
        alert.showAndWait();
        return;
      }
      
      // Récupérer le film correspondant au titre
      Film film = utilisation.getFilm(titreFilm);
      if (film == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Film introuvable");
        alert.setContentText("Le film sélectionné est introuvable.");
        alert.showAndWait();
        return;
      }
      
      // Créer une nouvelle évaluation avec les nouvelles données
      Evaluation nouvelleEvaluation = new Evaluation(nouveauCommentaire, film,
          nouvelleNote, nouveauCommentaire);
      
      // Modifier l'évaluation existante
      utilisation.modifierEvaluation(film, nouvelleEvaluation);
      
      // Afficher un message de confirmation
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Évaluation modifiée");
      alert.setHeaderText("Modification réussie");
      alert.setContentText("Votre évaluation a été modifiée avec succès !");
      alert.showAndWait();
      
      // Effacer les champs après modification
      texteCommentaire.clear();
      listeNoteEvaluation.setValue(null);
      
      // Mettre à jour la liste des évaluations affichées
      Set<Evaluation> evaluations =
          utilisation.ensembleEvaluationsFilm(titreFilm);
      listeEvaluations.getItems().clear();
      for (Evaluation eval : evaluations) {
        listeEvaluations.getItems().add(eval.toString());
      }
      
    } catch (NonConnecteException e) {
      // Gestion de l'erreur : utilisateur non connecté
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur");
      alert.setHeaderText("Utilisateur non connecté");
      alert.setContentText(
          "Vous devez être connecté pour modifier une évaluation.");
      alert.showAndWait();
    } catch (LocationException e) {
      // Gestion de l'erreur : problème lié à la modification
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur");
      alert.setHeaderText("Impossible de modifier l'évaluation");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    } catch (Exception e) {
      // Gestion des exceptions inattendues
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erreur inattendue");
      alert.setHeaderText("Une erreur est survenue");
      alert.setContentText("Détails : " + e.getMessage());
      alert.showAndWait();
    }
  }
  
  
  
  @FXML
  void actionSelectionArtiste(MouseEvent event) {
    
  }
  
  @FXML
  void actionSelectionEvaluation(MouseEvent event) {
    
  }
  
  @FXML
  void actionSelectionFilm(MouseEvent event) {
    
  }
  
  private InformationPersonnelle info = new InformationPersonnelle("default", "test", "123 Rue Principale", 20);
  private Utilisateur utilisation = new Utilisateur("def", "def123", info);
  private Administrateur administration = new Administrateur(); // Initialisation de l'administrateur

  @FXML
  void initialize() {
      try {
          // Charger automatiquement les données des films et artistes depuis le fichier
          Sauvegarde sauvegarde = new Sauvegarde(administration);
          sauvegarde.chargerDonnees("donnees.txt");

          // Mettre à jour l'interface utilisateur avec les films et artistes chargés
          listeFilms.getItems().setAll(
                  administration.ensembleFilms().stream()
                          .map(Film::getTitre) // Extraire uniquement le titre des films
                          .toList()
          );

          listeArtistes.getItems().setAll(
                  administration.ensembleActeurs().stream()
                          .map(artiste -> artiste.getPrenom() + " " + artiste.getNom()) // Formater les artistes
                          .toList()
          );

          System.out.println("Données chargées avec succès depuis donnees.txt !");
      } catch (IOException e) {
          // Afficher un message d'erreur si le chargement échoue
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur de chargement");
          alert.setHeaderText("Impossible de charger les données");
          alert.setContentText("Détails : " + e.getMessage());
          alert.showAndWait();
          e.printStackTrace();
      }
  }


  
}