Membres de l’équipe :                            
BALA Stéphane
HE Joseph
4A TCSI2

Lancement du programme

Premièrement, pour builder le programme, on doit éxécuter la ligne de commande suivante sur la console à partir du bon chemin:
$mvn clean install

Une fois que cette étape est terminée, on doit exécuter la commande suivante pour pouvoir lancer le tetris:
$mvn exec:java -Dexec.mainClass="com.asia.bala_he.IHM.MainIHM"
Ce jeu peut être joué uniquement sur les consoles et ne marchera pas avec les IDE.


Le GamePlay

Une fois que le programme est lancé, un premier menu apparaît où il faudra entrer une valeur entre 1 et 5 puis faudra appuyer sur le bouton entrée. Celui ci contient 5 éléments:
En choisissant 1, vous pouvez créer une nouvelle partie
En choisissant 2, vous pouvez chercher un serveur pour y rejoindre 
En choisissant 3, vous pouvez accèder aux scores
En choisissant 4, vous pouvez vous attribuer un username ou changer votre username
En choisissant 5, vous pouvez quitter le jeu

Lorsque vous créez une nouvelle partie, un deuxième menu apparaît. En choisissant le 1, vous pouvez commencer la partie ou quitter la partie en choisissant 2.
Une fois que la partie est lancée, une pièce apparaitra. Pou déplacer la pièce, vous pouvez utiliser les touches suivantes:
-  touche q pour bouger la pièce à gauche
- touche d pour bouger la pièce à droite
- touche s pour descendre plus vite
- touche r pour tourner la pièce


		
La conception des pièces 

Nous avons décidé d’inclure 7 pièces à notre tetris.		

Chaque pièce est contenu dans une matrice 4*4.
Toutes les positions de rotation de chacune des pièces sont stockées dans un tableau de deux dimension. 


Les malus

Nous avons implémenter deux malus pour le tetris.
Le premier malus se déclenche lorsque le joueur à chaque score de 5. Ce dernier consiste à descendre d’un coup la pièce courante dans le plateau.
Le deuxième se déclenche lorsque le joueur à chaque score de 10. Ce malus consiste à augmenter le temps de déplacement des pièces dans le plateau.
Par ailleurs, nous avons essayé de faire un troisième bonus qui permet d’ajouter une ligne dans le plateau mais ce dernier n’est pas opérationnel.


L’architecture

Dans ce projet, nous avons appliqué l’architecture C4 vue en cours. 
Tout d’abord nous avons établi quatre conteneurs. 
Le premier correspond au GameManager, celui-ci s’agit de moteur du jeu plus précisément l’interaction des pièces avec le plateau. Il contient deux composants. Le premier composant s’appelle BoardManager et contient une classe BoardManager permettant la gestion du plateau et  le déplacement des pièces dans ce plateau. 

Le deuxième composant se nomme PieceManager et s’occupe de la génération des pièces. Il possède trois classes Piece, PieceFactory, PieceManager. Le PieceFactory permet de générer les objets pièces selon un Id. Ensuite la classe Piece permet d’agir sur les propriétes d’une pièce(ex:orientation, numéro de la pièce…) . Enfin la  classe PieceManager permet de générer une pièce aléatoire.
Nous avons la classe Game.java qui est le coordinateur, il est responsable du déroulement du jeu.

Le deuxième conteneur/composant est l’IHM, l’interface homme machine. Ce dernier contient quatre classes. La classe DisplayManager s’occupe de l’affichage du jeu, la classe KeyboardManager permet d’interagir avec les pièces du plateau, la classe RawConsoleInput permet d’utiliser les touches directement sur la console et enfin la classe MainIHm correspond au main pour lancer le jeu .

Le troisième conteneur est le NetworkManager. Ce dernier est essentiel pour le mode multijoueur du tetris, c’est la partie communication réseau. 
 Il possède deux composants. Le composant ConnectionHandler s’occupe de la connexion entre le serveur et un nouveau client et contient 3 classes. La première classe est Client, elle a la responsabilité de se connecter au severur et gestion des flux d’entrée et de sortie pour communiquer au serveur. La deuxième classe est le ClientConnexionThread qui permet d’accepter les client et enfin la dernière classe Server  permet d’avoir l’état des différents clients.
Le deuxième composant est le MessageHandler, il s’occupe de du traitement des messages reçus et envoyés. Il possède 3 classes. La classe DataServant analyse les message pour les transformer en une classe HashMap pour manipuler les données(string envoyés par serveur ou client). La deuxième classe ReadInputThread permet au client d’écouter les messages en continu provenant du serveur.
La dernière classe de ce compasant est le SendMessageToAll et permet au serveur d’écouter les messages en provenance d’un client et en fonction de message reçu , il va envoyer des données à un ou plusieurs clients.

Le dernier conteneur s’agit du ScoreManager et s’occupe de la gestion du score. Il contient une classe FileManager qui écrit le score dans un ficher texte à la fin de chaque partie.


Les design patterns / Solid 

Singleton:
Nous avons besoin d’une instance unique qui représente la classe Serveur, pour pas qu’il y ait plusieurs serveur lorsqu’un utilisateur créer une partie auxquels d’autre utilisateurs vont se connecter.
Servant:
Nous avons une classe DataServant pour analyser les messages et les mettre dans un hashmap pour une utilisation plus facile.
Template:
La classe MainIHM représente un template pour les cas d’utilisations (il y a un ordre dans l’exécution)
Factory:
Pour la génération d’objet Piece.

Notre conception est plus souvent basé sur une seule responsabilité par composant. Par exemple, on a un composant BoardManager qui a les responsabilité des opérations du plateau, et GameManager qui a la responsabilité de la partie, une partie de jeu, et qui va dépendre de BoardManager et donc utiliser ses opérations.
