# QREmargement Client
Partie client de l'application QREmargement

### Structure du projet

La partie cliente à une organisation orientée composants

+-- app : Contient le code Angular de l'application (vues, contrôleurs, factories, services, directives)
|	+-- components : composant de l'application
|   |   +-- {nom_composant} : Un sous dossier par composant
|   |   |   +-- : {nom_composant}.ctrl.js : contrôleur Angular du composant
|   |   |   +-- : {nom_composant}.fact.js : factory du composant
|   |   |   +-- : {nom_composant}.tpl.html : vue principale du composant
|   |   |   +-- : {nom_composant}.*.html : différentes vues du composant
|   +-- shared : composants partagés de l'application (directives angular, authentification,...)
|   +-- app.constant.js : Fichier Angular contenant les constantes de l'application
|   +-- app.ctrl.js : Contrôleur principale de l'application
|   +-- app.module.js : Initialisation de l'application avec configuration de services et injection des dépendances
|   +-- app.routes.js : Déclaration des routes de l'application
+-- assets : Contient les fichiers externes à Angular
|   +-- img : Images de l'application
|   +-- js : Fichiers Javascript ne contenant pas de code Angular
|   +-- libs : Différentes librairies pouvant être intégrées à un projet
|   +-- style : Fichiers CSS de personnalisation de l'application
|
+-- bower_components : Répertoire contenant les dépendances récupérées par l'utilitaire Bower
+-- bower.json : Dépendances utilisées dans le projet ainsi que leurs versions
+-- index.html : Point d'entrée de l'application, contient également les liens vers les dépendances du projet

### Gestion des dépendances

Pour gérer les dépendances de la partie cliente du projet, nous utilisons l'utilitaire [Bower](http://bower.io/) qui permet de récupérer les dépendances dynamiquement
La commande pour installer les dépendances se lance depuis __/client__ :
```sh
$ bower install
```
