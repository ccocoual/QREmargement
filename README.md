<h1>QREmargement</h1>

<h2>Synopsis</h2>
<p>QREmargement est un projet universitaire qui a pour vocation de remplacer les feuilles de contrôle d'assiduité des étudiants. Celles ci sont actuellement imprimées et collectées par les professeurs. Il est difficile de contrôler le taux d'absentéisme d'un étudiant et les absences non justifiées.</p>

<h2>Motivation</h2>
<p>Le projet QREmargement a pour but de collecter, de simplifier et de centraliser les feuilles d'émargement des enseignants numériquement et de contrôler la présence d'un étudiant à un cour grâce à une signature numérique produite par un QRCode.</p>

<h2>Projet</h2>
Le projet repose sur une architecture client / serveur classique.
<hr>
<h2>Serveur</h2>
<p>Le serveur est un serveur Java propulsé par Jersey (framework webservices REST) couplé avec Grizzly (framework HTTP REST). La gestion des dépendences du projet est assuré par le framework Maven. La sauvegarde des données ce fait grâce à une base relationnelle MySQL.</p>

<h4>Structure du serveur</h4>
<p>Le serveur est découpé en trois parties</p>
<table>
<tr>
    <td>Les ressources : </td> 
    <td>
        <code>$ cd QREmargement\serveur\qre\src\main\java\com\qre</code>
    </td>
</tr>
<tr>
    <td>Les accès base de données : </td>
    <td>
        <code>$ cd QREmargement\serveur\qre\src\main\java\database</code>
    </td>
</tr>
<tr>
    <td>Les modèles métiers : </td>
    <td>
        <code>$ cd QREmargement\serveur\qre\src\main\java\model</code>
    </td>
</tr>
</table>

<h4>Installation du serveur</h4>
  Installation du projet et de ses dépendances.
    <pre><code>
    $ git clone https://github.com/ccocoual/QREmargement.git
    $ cd QREmargement/
    $ mvn clean install</code></pre>
    
  
  Configuration de la base de données.
    <pre><code>
    $ cd QREmargement\serveur\qre\src\main\java\database\
    $ nano Database.java</code></pre>
  
  <h4>Mise en ligne du serveur</h4>
  /!\ Avant la mise en ligne, assurer vous qu'aucun processus n'écoute déjà le port 8080.
  <pre>
    <code>
    $ cd QREmargement/serveur/qre
    $ cd nohup mvn exec:java -Dexec.mainClass="com.qre.Main" &</code>
  </pre>

  <h4>API serveur</h4>
  
  Une fois le serveur lancé, l'ensemble des requetes sont disponible à l'adresse <b>http://**ip_serveur**/application.wadl</b>

  <h4>Tests serveur</h4>
  
  Les tests sont réalisés avec le framework Junit
  
  Dossier de test
  <code>$ cd QREmargement\serveur\qre\src\test\java\com\qre</code>

  Exécuter les tests
  <pre><code>
    $ cd QREmargement/serveur/qre
    $ mvn test
    </code></pre>

<hr>

<h2>Contributeurs</h2>
<ul>
<li>Mariane Kinfack Dongmo</li>
<li>Julien Moulin</li>
<li>Corentin Cocoual</li>
<li>Maxime Meunier</li>
<li>Alexandre Berteaux</li>
</ul>
