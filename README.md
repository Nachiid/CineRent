# **Système de Location de Films**

## **Description**
Ce projet est une application Java qui gère un système de location de films. Il comprend une interface graphique pour les utilisateurs et les administrateurs, ainsi que des fonctionnalités telles que :
- La gestion des films (ajout, suppression, location).
- La gestion des artistes (acteurs, réalisateurs).
- La gestion des utilisateurs (inscription, connexion, déconnexion).
- La sauvegarde et le chargement des données.

Le projet est conçu en utilisant une architecture orientée objet et inclut des classes pour gérer les films, les utilisateurs, les évaluations et les locations.

---

## **Fonctionnalités principales**

### **1. Gestion des Films**
- Création de films par un administrateur.
- Association des genres et des acteurs à un film.
- Gestion des locations de films (ouverture et fermeture de la location).

### **2. Gestion des Artistes**
- Création d'acteurs et de réalisateurs.
- Association d'acteurs à des films.
- Suppression des artistes (uniquement s'ils ne sont pas associés à des films).

### **3. Gestion des Utilisateurs**
- Inscription avec des informations personnelles.
- Connexion et déconnexion.
- Location de films avec contrôle des restrictions d'âge.
- Ajout et modification d'évaluations pour les films.

### **4. Interface Graphique**
- Une interface utilisateur pour louer des films.
- Une interface administrateur pour gérer les films et les artistes.

### **5. Sauvegarde et Chargement des Données**
- Les données des films, artistes et utilisateurs sont sauvegardées dans des fichiers texte.
- Chargement des données au démarrage de l'application.

---

## **Architecture du Projet**

### **Packages**
- **`location`** : Contient les classes principales pour gérer les films, les artistes, les utilisateurs et les évaluations.
- **`io`** : Fournit les fonctionnalités de sauvegarde et de chargement des données.
- **`ui`** : Contient les fichiers et classes pour l'interface graphique.
- **`test`** : Contient les classes de test pour valider les fonctionnalités du projet.

### **Principales Classes**
#### **Location**
- `Film` : Représente un film.
- `Artiste` : Représente un acteur ou un réalisateur.
- `Evaluation` : Permet aux utilisateurs de noter et commenter un film.
- `Reservation` : Gère les films loués par un utilisateur.

#### **IO**
- `Sauvegarde` : Gère la sauvegarde et le chargement des données.

#### **UI**
- `MainInterface` : Lance l'interface graphique.
- `MainLocation` : Exemple d'utilisation des classes principales pour démonstration.

---

## **Prérequis**
### **1. Environnement**
- **Java 8** ou supérieur.
- **JavaFX** pour l'interface graphique.

### **2. Bibliothèques utilisées**
Aucune bibliothèque externe n'est utilisée, uniquement les API Java standard.

---

## **Installation**

1. Clonez ce dépôt sur votre machine :
   ```bash
   git clone <URL_DU_DEPOT>
   cd <NOM_DU_PROJET>
   ```

2. Importez le projet dans votre IDE préféré (Eclipse, IntelliJ IDEA, etc.).

3. Configurez les chemins pour JavaFX (si nécessaire).

4. Compilez et exécutez le fichier principal `ui.MainInterface` pour lancer l'application.

---

## **Exécution**
### **Interface graphique**
Lancez l'application via `MainInterface`. Deux fenêtres s'ouvriront :
- Une pour l'interface utilisateur.
- Une pour l'interface administrateur.

### **Exemple d'utilisation**
Lancez le fichier `MainLocation` pour tester les fonctionnalités principales.

---

## **Tests**
Les tests sont inclus dans le package `test`. Pour exécuter les tests :
1. Assurez-vous que JUnit est configuré dans votre IDE.
2. Exécutez les tests dans le package `test` pour vérifier le bon fonctionnement des fonctionnalités.

---

## **Sauvegarde des Données**
Les données sont sauvegardées dans des fichiers texte :
- Par défaut, dans un fichier nommé `donnees.sav`.
- Modifiable via la classe `Sauvegarde`.

---

## **Structure des Fichiers**
```
src/
├── io/
│   ├── Sauvegarde.java
├── location/
│   ├── Administrateur.java
│   ├── Artiste.java
│   ├── Evaluation.java
│   ├── Film.java
│   ├── GestionArtistes.java
│   ├── GestionFilms.java
│   ├── GestionUtilisateur.java
│   ├── Genre.java
│   ├── InformationPersonnelle.java
│   ├── InterAdministration.java
│   ├── InterUtilisateur.java
│   ├── LocationException.java
│   ├── NonConnecteException.java
│   ├── Reservation.java
│   ├── Utilisateur.java
├── test/
│   ├── AdministrateurTest.java
|   ├── ArtisteTest.java
|   ├── EvaluationTest.java
|   ├── FilmTest.java
|   ├── ReservationTest.java
|   ├── TestInformationPersonnelle.java
|   ├── UtilisateurTest.java
├── ui/
│   ├── MainInterface.java
│   ├── utilisateur.fxml
│   ├── administration.fxml
```

---

## **Auteurs**
- **Nachid Ayman**
- **Akbal Lyna**
- **Ayad Oumaina**
- **Zeroil Ahlam**

---

## **Licence**
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, le modifier et le distribuer.

---