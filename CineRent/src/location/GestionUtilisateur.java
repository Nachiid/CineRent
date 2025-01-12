package location;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsable de la gestion des utilisateurs dans le système. Elle
 * fournit des fonctionnalités pour inscrire, connecter et gérer les
 * utilisateurs. Les utilisateurs sont identifiés de manière unique par leur
 * pseudo.
 */
public class GestionUtilisateur implements Serializable {
  
  private static final long serialVersionUID = 684731815545298968L;
  
  // Map pour stocker les utilisateurs inscrits, identifiés par leur pseudo.
  private static final Map<String, Utilisateur> utilisateursInscrits =
      new HashMap<>();
  
  // Instance unique pour implémenter le modèle Singleton.
  private static GestionUtilisateur instance = null;
  
  /**
   * Récupère l'instance unique de GestionUtilisateur. Implémente le modèle
   * Singleton.
   *
   * @return L'instance unique de GestionUtilisateur.
   */
  public static GestionUtilisateur getInstance() {
    if (instance == null) {
      instance = new GestionUtilisateur();
    }
    return instance;
  }
  
  /**
   * Retourne une copie de la liste des utilisateurs inscrits. Cela empêche
   * toute modification directe de la collection interne.
   *
   * @return Une map contenant les utilisateurs inscrits, identifiés par leur
   *         pseudo.
   */
  public Map<String, Utilisateur> getUtilisateurs() {
    return new HashMap<>(utilisateursInscrits);
  }
  
  /**
   * Inscrit un nouvel utilisateur dans le système. Vérifie si les informations
   * sont valides et si le pseudo n'est pas déjà utilisé.
   *
   * @param pseudo Le pseudo unique de l'utilisateur.
   * @param motDePasse Le mot de passe de l'utilisateur.
   * @param info Les informations personnelles de l'utilisateur.
   * @return Un code représentant le résultat de l'inscription :
   *         <ul>
   *         <li>0 : Inscription réussie</li>
   *         <li>1 : Pseudo déjà utilisé</li>
   *         <li>2 : Pseudo ou mot de passe vide</li>
   *         <li>3 : Informations personnelles invalides</li>
   *         </ul>
   */
  public int inscription(String pseudo, String motDePasse,
      InformationPersonnelle info) {
    if (pseudo == null || pseudo.isEmpty() || motDePasse == null
        || motDePasse.isEmpty()) {
      return 2; // Pseudo ou mot de passe vide
    }
    if (info == null) {
      return 3; // Informations personnelles invalides
    }
    if (utilisateursInscrits.containsKey(pseudo)) {
      return 1; // Pseudo déjà utilisé
    }
    
    // Création et ajout de l'utilisateur.
    Utilisateur nouveauUtilisateur = new Utilisateur(pseudo, motDePasse, info);
    nouveauUtilisateur.setUtilisateurConnecte(pseudo);
    utilisateursInscrits.put(pseudo, nouveauUtilisateur);
    return 0; // Inscription réussie
  }
  
  /**
   * Connecte un utilisateur existant en vérifiant ses informations
   * d'identification.
   *
   * @param pseudo Le pseudo de l'utilisateur.
   * @param motDePasse Le mot de passe de l'utilisateur.
   * @return L'objet Utilisateur correspondant si les informations sont
   *         correctes, sinon null.
   */
  public Utilisateur connexion(String pseudo, String motDePasse) {
    // Vérifie si l'utilisateur existe et si le mot de passe correspond.
    Utilisateur utilisateur = utilisateursInscrits.get(pseudo);
    if (utilisateur != null && utilisateur.getMdp().equals(motDePasse)) {
      return utilisateur; // Connexion réussie
    }
    return null; // Connexion échouée
  }
}
