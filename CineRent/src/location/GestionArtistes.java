package location;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe pour gérer les artistes. Fournit des fonctionnalités pour créer,
 * rechercher, modifier et supprimer des artistes. Implémente un modèle
 * Singleton pour garantir une instance unique de gestion des artistes.
 */
public class GestionArtistes implements Serializable {
  
  private static final long serialVersionUID = 1297776707400850399L;
  
  // Map pour stocker les artistes, identifiés par une clé unique (nom +
  // prénom).
  private Map<String, Artiste> artistes;
  
  // Instance unique (Singleton)
  private static GestionArtistes instance = null;
  
  /**
   * Constructeur par défaut. Initialise la collection des artistes sous forme
   * de HashMap.
   */
  public GestionArtistes() {
    this.artistes = new HashMap<>();
  }
  
  /**
   * Méthode pour récupérer l'instance unique (Singleton) de GestionArtistes. Si
   * l'instance n'existe pas, elle est créée.
   *
   * @return L'instance unique de GestionArtistes.
   */
  public static GestionArtistes getInstance() {
    if (instance == null) {
      instance = new GestionArtistes();
    }
    return instance;
  }
  
  /**
   * Retourne tous les artistes sous forme de map. Fournit une copie de la map
   * pour éviter toute modification directe.
   *
   * @return Une copie de la map contenant tous les artistes.
   */
  public Map<String, Artiste> getArtistes() {
    return new HashMap<>(artistes);
  }
  
  /**
   * Définit un artiste comme réalisateur. Si l'artiste existe dans la
   * collection, son état est modifié en réalisateur.
   *
   * @param artiste L'artiste à modifier.
   * @return true si la modification a réussi, false si l'artiste n'existe pas
   *         ou est invalide.
   */
  public boolean setRealisateur(Artiste artiste) {
    if (artiste == null) {
      return false; // Vérifie si l'artiste est valide
    }
    String key = generateKey(artiste.getNom(), artiste.getPrenom());
    Artiste existingArtiste = artistes.get(key);
    
    if (existingArtiste != null) {
      existingArtiste.setEstActeur(false); // Modifie l'état en réalisateur
      return true;
    }
    return false; // L'artiste n'existe pas dans la collection
  }
  
  /**
   * Crée un nouvel artiste et l'ajoute à la collection. Par défaut, un nouvel
   * artiste est considéré comme un acteur.
   *
   * @param nom Le nom de l'artiste.
   * @param prenom Le prénom de l'artiste.
   * @param nationalite La nationalité de l'artiste.
   * @return L'artiste créé, ou null si un artiste avec le même nom et prénom
   *         existe déjà.
   */
  public Artiste creerArtiste(String nom, String prenom, String nationalite) {
    String key = generateKey(nom, prenom);
    if (artistes.containsKey(key)) {
      return null; // Vérifie si l'artiste existe déjà
    }
    Artiste artiste = new Artiste(nom, prenom, nationalite); // Instancie un
    // nouvel artiste
    artistes.put(key, artiste); // Ajoute l'artiste à la collection
    return artiste;
  }
  
  /**
   * Supprime un artiste par sa clé unique (nom et prénom) de la collection.
   *
   * @param nom Le nom de l'artiste à supprimer.
   * @param prenom Le prénom de l'artiste à supprimer.
   * @return true si l'artiste a été supprimé, false sinon.
   */
  public boolean supprimerArtiste(String nom, String prenom) {
    String key = generateKey(nom, prenom);
    return artistes.remove(key) != null; // Retourne true si l'artiste a été
    // supprimé
  }
  
  /**
   * Recherche un artiste dans la collection par son nom et son prénom.
   *
   * @param nom Le nom de l'artiste.
   * @param prenom Le prénom de l'artiste.
   * @return L'artiste correspondant, ou null s'il n'existe pas.
   */
  public Artiste getArtiste(String nom, String prenom) {
    return artistes.get(generateKey(nom, prenom)); // Retourne l'artiste si
    // trouvé
  }
  
  /**
   * Génère une clé unique pour identifier un artiste dans la collection. La clé
   * est formée par la concaténation du nom et du prénom, convertis en
   * minuscules.
   *
   * @param nom Le nom de l'artiste.
   * @param prenom Le prénom de l'artiste.
   * @return Une chaîne unique représentant la clé de l'artiste.
   */
  private String generateKey(String nom, String prenom) {
    return (nom + prenom).toLowerCase(); // Génère une clé unique insensible à
    // la casse
  }
}
