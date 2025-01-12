package ui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe principale de l'application qui lance l'interface graphique. Cette
 * classe utilise JavaFX pour afficher les différentes fenêtres de
 * l'application, notamment celles destinées aux utilisateurs et aux
 * administrateurs.
 *
 * @author Eric Cariou
 */
public final class MainInterface extends Application {
  
  /**
   * Affiche la fenêtre dédiée aux utilisateurs, leur permettant de louer des
   * films.
   */
  public void startFenetreEtudiants() {
    try {
      // Chargement de la vue FXML pour les utilisateurs
      URL url = getClass().getResource("utilisateur.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      // Création de la scène avec des dimensions personnalisées
      Scene scene = new Scene(root, 1200, 650);
      
      // Initialisation et configuration de la fenêtre
      Stage stage = new Stage();
      stage.setResizable(true);
      stage.setTitle("Location de films");
      
      // Association de la scène à la fenêtre et affichage
      stage.setScene(scene);
      stage.show();
      
    } catch (IOException e) {
      // Gestion des erreurs lors du chargement de la fenêtre
      System.err
          .println("Erreur au chargement de la fenêtre utilisateur : " + e);
    }
  }
  
  /**
   * Affiche la fenêtre dédiée à l'administration, permettant aux
   * administrateurs de gérer les films et les utilisateurs.
   *
   * @param primaryStage la fenêtre principale fournie par JavaFX
   */
  public void startFenetreFormation(Stage primaryStage) {
    try {
      // Chargement de la vue FXML pour les administrateurs
      URL url = getClass().getResource("administration.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      // Création de la scène avec des dimensions personnalisées
      Scene scene = new Scene(root, 615, 725);
      
      // Configuration de la fenêtre principale
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Administration des films");
      
      // Affichage de la fenêtre
      primaryStage.show();
      
    } catch (IOException e) {
      // Gestion des erreurs lors du chargement de la fenêtre
      System.err
          .println("Erreur au chargement de la fenêtre administration : " + e);
    }
  }
  
  /**
   * Point d'entrée principal de l'application JavaFX. Cette méthode initialise
   * et lance les deux fenêtres principales de l'application (utilisateur et
   * administration).
   *
   * @param primaryStage la fenêtre principale fournie par JavaFX
   */
  @Override
  public void start(Stage primaryStage) {
    // Lancement de la fenêtre d'administration
    this.startFenetreFormation(primaryStage);
    
    // Lancement de la fenêtre utilisateur
    this.startFenetreEtudiants();
    
    // Ajout potentiel de code supplémentaire avant ou après le lancement des
    // fenêtres
  }
  
  /**
   * Méthode main pour lancer l'application JavaFX.
   *
   * @param args les arguments passés à l'application
   */
  public static void main(String[] args) {
    launch(args); // Lance l'application JavaFX
  }
}
