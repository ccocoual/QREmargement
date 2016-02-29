<h1>QREmargement</h1>

<h2>Synopsis</h2>
<p>QREmargement est un projet universitaire qui a pour vocation de remplacer les feuilles de contrôle d'assiduité des étudiants. Celles ci sont actuellement imprimées et collectées par les professeurs. Il est difficile de contrôler le taux d'absentéisme d'un étudiant et les absences non justifiées.</p>

<h2>Motivation</h2>
<p>Le projet QREmargement a pour but de collecter, de simplifier et de centraliser les feuilles d'émargement des enseignants numériquement et de contrôler la présence d'un étudiant à un cour grâce à une signature numérique produite par un QRCode.</p>

<h2>Projet</h2>
Le projet repose sur une architecture client / serveur classique.

<h2>Serveur</h2>
<p>Le serveur est un serveur Java propulsé par Jersey (framework webservices REST) couplé avec Grizzly (framework HTTP REST). La gestion des dépendences du projet est assuré par le framework Maven. La sauvegarde des données ce fait grâce à une base relationnelle MySQL.</p>

<h4>Structure</h4>
<p>Le serveur est découpé en trois parties</p>
<ul>
<li> Les ressources : 
    <code>
      $ cd QREmargement\serveur\qre\src\main\java\com\qre
    </code>
  </li>
<li> Les accès base de données : 
    <code>
      $ cd QREmargement\serveur\qre\src\main\java\database
    </code>
  </li>
<li> Les modèles métiers : 
    <code>
      $ cd QREmargement\serveur\qre\src\main\java\model
    </code>
</li>
</ul>

<h4>Installation</h4>
  Installation du projet et de ses dépendances.
  <pre>
    <code>
      $ git clone https://github.com/ccocoual/QREmargement.git
      $ cd QREmargement/
      $ mvn clean install
    </code>
  </pre>
  
  Configuration de la base de données.
  <pre>
    <code>
      $ cd QREmargement\serveur\qre\src\main\java\database/
      $ nano Database.java
    </code>
  </pre>
  
  <h4>Mise en ligne</h4>
  /!\ Avant la mise en ligne, assurer vous qu'aucun processus n'écoute déjà le port 8080.
  <pre>
    <code>
      $ cd QREmargement/
      $ cd serveur/qre
      $ cd nohup mvn exec:java -Dexec.mainClass="com.qre.Main" &
    </code>
  </pre>

  <h4>API</h4>
  Le détail de l'api est disponible à l'adresse <b>http://**ip_serveur**/api</b>

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

Tests

Describe and show how to run the tests with code examples.

<h2>Team members</h2>
<ul>
<li>Mariane Kinfack Dongmo</li>
<li>Julien Moulin</li>
<li>Corentin Cocoual</li>
<li>Maxime Meunier</li>
<li>Alexandre Berteaux</li>
</ul>


License

A short snippet describing the license (MIT, Apache, etc.)
