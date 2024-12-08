# Projet de Location de Films

Ce projet est un système de gestion de location de films. Il permet de gérer des films, leurs acteurs, réalisateurs, genres, et des évaluations laissées par les utilisateurs.

## Description

Le système gère les entités suivantes :
- **Film** : Représente un film avec son titre, sa date de sortie, son réalisateur, ses acteurs, ses genres et l'âge minimum requis pour le visionnage.
- **Artiste** : Représente une personne (acteur ou réalisateur) impliquée dans un film.
- **Genre** : Enumération des différents genres de films (Action, Drame, Comédie, etc.).
- **Evaluation** : Représente une évaluation laissée par un utilisateur pour un film, comprenant un pseudo, une note et un commentaire.

## Fonctionnalités

- **Film** : 
  - Ajouter des acteurs, des genres et définir un réalisateur.
  - Gérer l'état du film (ouvert ou fermé).
  
- **Artiste** :
  - Définir un artiste comme acteur ou réalisateur.
  - Accéder au nom, prénom et nationalité d'un artiste.

- **Evaluation** :
  - Ajouter des évaluations pour les films avec une note entre 0 et 5.
  - Laisser un commentaire pour l'évaluation du film.

## Structure du projet

Le projet est organisé en plusieurs classes qui correspondent aux différentes entités du système :

1. **location/Film.java** : Représente un film.
2. **location/Artiste.java** : Représente un artiste (acteur ou réalisateur).
3. **location/Genre.java** : Enumération des genres de films.
4. **location/Evaluation.java** : Représente une évaluation laissée par un utilisateur.

## Tests

Le projet utilise **JUnit 5** pour les tests unitaires. Des tests sont fournis pour les classes suivantes :

- **Film** : Vérification de l'ajout d'acteurs, genres, et des getters/setters.
- **Artiste** : Test des attributs d'un artiste, ainsi que du rôle d'acteur ou de réalisateur.
- **Evaluation** : Test des méthodes de gestion des évaluations (validation de la note, commentaires, etc.).

### Lancer les tests

Pour exécuter les tests unitaires, vous pouvez utiliser votre IDE (comme IntelliJ IDEA ou Eclipse), ou exécuter la commande suivante dans votre terminal (si vous utilisez Maven ou Gradle) :

- **Maven** : `mvn test`
- **Gradle** : `gradle test`

## Auteurs
- **Auteurs** : NACHID AYMAN - OUMAIMA AYAD - AHLAM ZEROIL - LYNA AKBAL

Copyright © 2024 [NACHID]. All rights reserved.
Permission to view this code is granted, but modification or redistribution is prohibited.
