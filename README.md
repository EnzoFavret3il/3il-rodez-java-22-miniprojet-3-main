# PARTIE TECHNIQUE

SPOILER ALERT: 
Le projet ne respecte pas totalement les séparations MVC à cause de mon manque d'expérience et de mon manque de compréhension du modèle MVC.

Au niveau des demandes de bases :
- Lecture aléatoire d'un mot à deviner à partir d'un fichier texte donné à la racine du projet.
- Affichage graphique de l'interface du jeu à l'aide de Swing.
- Affichage graphique du pendu qui évolue en fonction des erreurs du joueur.
- Affichage graphique des lettres déjà proposées par le joueur.
- Affichage (ou non) de la définition (niveau de difficulté).
- Utilisation (ou non) d'un timer (niveau de difficulté).
- Gestion des entrées utilisateur pour proposer des lettres.
- Vérification de la validité des entrées utilisateur (lettre de l'alphabet uniquement).
- Gestion du décompte des tentatives restantes.
- Gestion de la victoire ou de la défaite du joueur.
- Possibilité de rejouer une partie après la fin d'une partie.

D'après ma compréhension de ces demandes, elles sont toutes terminées et fonctionnelles. 

## Les Interfaces graphiques

Le projet est composé de trois classes permettant de définir les interfaces interfaces Graphiques.


La classe interfaceBegin est la première interface graphique affiché pour demander à l'utilisateur qu'elle mode de jeu il souhaite jouer.

La classe interfaceJeu est l'interface qui affiche le jeu. C'est notament dans cette classe que le MVC et d'autres régles de programmation ne sont pas du tout respecté.

Enfin la classe newGame est l'interface graphique qui s'affiche lorsqu'un utilisateur à terminé une partie (gagné ou perdu) et lui demande si il souhaite rejouer ou pas.


Ces 3 classes implémentes l'interface affichage qui définit des méthodes de bases qui semblent convenir pour des classes graphiques.

## Les données

L'application fonctionne via des données externes qui sont le fichier le mots.txt et des sons. Ces données se trouve respectivement à la racine du projet et dans un package Sounds pour structurer un peu le projet.


Ces données sont utilisés par les classes Dictionnaires et Sound.


La classe Dictionnaire est essentielle au fonctionnement du projet. Cette classe permet de traiter le fichier mots.txt et d'y extraire les mots et les définitions utilisés dans le jeu du pendu. 

La classe Sound est une classe utilisé en Bonus. Celle-ci permet de jouer des sons lorsque l'utilisateur fait une erreur ou réalise une bonne réponse via des fichiers en .wav .

## Les Tests

Deux classes Tests ont étaient réalisés pour tester les données.

La classe DictionnaireTest qui test l'existance d'un mot dans le dictionnaire, si un mot à une définition...

La classe SoundTest qui test les types de son (erreur, bonne réponse, inconnue) et si les son sont joués.



# PARTIE FONCTIONNEMENT APPLICATION

Pour lancer l'application, il faut lancer la classe `Main.java` dans le package `Boot`.


Le jeu est composé de deux modes de jeu, Facile et Difficile:

## Facile:
Lorsque le jeu est lancé en mode facile, l'utilisateur a accès à un bouton permettant d'afficher la définition du mot à chercher.

## Difficile:

Lorsque le jeu est lancé en mode difficile, l'utilisateur n'a plus de définition du mot et a un timer de 20 secondes pour répondre. Celui-ci ne repart pas à zéro lorsqu'une réponse est donnée pour ajouter plus de difficulté au jeu. Si le timer est atteint 0, une vie est retiré à l'utilisateur et repart à 20 secondes.

Le jeu joue des sons lors des erreurs et bonnes réponses de l'utilisateur au cours de la partie est affiche l'evolution d'un pendu au long de la partie sur 7 étapes, donc 7 vies pour l'utilisateur. 


En fin de partie l'utilisateur est invité à relancer une partie ou de quitter l'application qu'importe le résultat de la partie précédente. 

# RETOUR SUR LE PROJET

Ce projet était très intéréssant dans sa réalisation car il y a énormément de manière pour répondre aux problèmatiques et de s'amuser dans les bonus.

Ce projet a aussi révélé mon manque de compréhension important des modèles MVC, ce qui est plutôt embettant dans un projet avec une IHM... J'ai eu sur ce sujet en grand problème au début du projet à visualiser comment faire les liens entres les différentes parties. J'ai donc décidé de mettre un peu de coté le MVC en essayant de garder une structure de code propre même si on peu mieux faire.

Sur les structures et utilisations des classes de SWING, j'ai aprris a utiliser, de manière basique, les classes de bases de SWING afin de réaliser une interface simple. Il me manque toutes les notions de gestions et de placement des objets dans un frame SWING. 

# CE QUI RESTE A FAIRE

L'application est fonctionnelle mais elle ne respecte pas vraiment le MVC, il faudrait donc déstructurer certaines classes afin de séparer correctement les parties du MVC de ces classes.

Nettoyer le code est essentielle car cetaines classes ont trop de lignes de code avec un peu trop de dépendance à mon avis. Il faudrait essayer de décomposer plusieurs méthodes pour défaire ces dépendances.
