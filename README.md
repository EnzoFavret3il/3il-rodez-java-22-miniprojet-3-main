# PARTIE TECHNIQUE

SPOILER ALERT: 
Le projet ne respecte pas totalement les séparations MVC à cause de mon manque d'expérience et de mon manque de compréhension du modèle MVC. Mais j'ai éssayé ... 

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


