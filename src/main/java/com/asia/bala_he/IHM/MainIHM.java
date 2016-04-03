package com.asia.bala_he.IHM;

import java.io.IOException;

import com.asia.bala_he.Constants;
import com.asia.bala_he.GameManager.Game;
import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.PieceFactory;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;
import com.asia.bala_he.NetworkManager.ConnectionHandler.Client;
import com.asia.bala_he.NetworkManager.ConnectionHandler.Server;
import com.asia.bala_he.NetworkManager.MessageHandler.DataServant;
import com.asia.bala_he.ScoreManager.FileManager;

import java.util.*;
import java.util.prefs.BackingStoreException;


//Classe main pour le jeu
public class MainIHM {
	
	//Initialisation 
	private static Scanner enter = new Scanner(System.in);
	private static boolean backToMenu = false;
	private static boolean gameStarted = false;
	private static Server s = null;
	private static Client c = null;
	private static Thread t = null;

	public static void main(String[] args) {
		int a = 0;
		 Constants.displayWelcome();//Affichage Message d'accueil
		c = new Client("Player");
		while (true) {
			Constants.displayMenu();//Affichage menu
			a = enter.nextInt();
			// a=1;
			switch (a) {
			case 1:
				createGame();
				break;
			case 2:
				findServer();
				break;
			case 3:
				viewScores();
				break;
			case 4:
				changeName();
				break;
			case 5:
				quit();
			}
			a = 0;
		}

	}



	// public static void clientConnection(Client c, String ipAddr, int port){
	// c.connect(ipAddr,port);
	// c.setClientId(clientId);
	// }

	//Fonction qui crée une nouvelle partie
	public static void createGame() {
		int a = 0;
		s = new Server();//Cration d'un serveur
		c.connect("127.0.0.1", 8078);

		String d="";
//		while(d!="quit"){
//			d = enter.next();
//			c.sendData(d);
//		}
		c.setClientId(s.getClientId() + "");
		c.sendData("id=" + c.getClientId() + "&name=" + c.getName());

		t = new Thread(new Runnable() {
			public void run() {
				while (!backToMenu && !gameStarted) {
					Constants.displayMenu2();
					displayPlayer(s);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		t.start();
		while (!backToMenu) {
			a = enter.nextInt();
			switch (a) {//Etat du jeu
			case 1:
				gameStarted = true;
				c.sendData("startGame=true");
				startGame();
				
				gameStarted=false;
				s.acceptClient();
				break;
			case 2:
				backToMenu = true;
				c.disconnect();
				s.disconnect();
				break;
			}
		}
	}

	//Affiche le nom du joueur
	public static void displayPlayer(Server s) {
		Map m = s.requestPlayer();
		System.out.println(m);
	}

	public static void startGame() {
		PieceManager pm = new PieceManager(new PieceFactory());
		BoardManager bm = new BoardManager(new int[21][18],
				pm.generateRandomPiece(), null, 0, 7);
		Game g = new Game(bm, pm, c);
		Thread t = new Thread(g, "game");
		Thread k = new KeyboardManager(g);
		// // g.playAGame();
		t.start();
		k.start();

		Thread d = new DisplayManager(g);
		d.start();
		while (!g.getEndOfGame()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		c.sendData("listPlayers=nb");
		while(c.getRit().getMapValue("listPlayers").equals("")){
			System.out.println("waiting nb player +"+c.getRit().getMapValue("listPlayers"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int nb = Integer.parseInt(c.getRit().getMapValue("listPlayers"));
		System.out.println(nb);
		FileManager.write(nb, c.getName(), g.getScore());
	}

	//Chercher un serveur
	public static void findServer() {
		backToMenu = false;
		// list of game server ... ?
		int a = 0;
		Constants.displayMenu3();
		c.connect("127.0.0.1", 8078);//Par défault
		
		c.sendData("requestId=true");
		String id = c.getRit().getStr();
		Map m = DataServant.parseIntoHashMap(id);
		c.setClientId(m.get("id")+"");
		System.out.println(c.getRit().getStr());
		System.out.println(c.getClientId());
		c.sendData("id=" + c.getClientId() + "&name=" + c.getName());
		System.out.println("Connected");
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (!backToMenu && !gameStarted) {
					System.out.println(backToMenu);
					System.out.println(gameStarted);
					String m = c.getRit().getMapValue("startGame");
					if(!m.equals("")){
						gameStarted = Boolean.parseBoolean((String) m);
						c.getRit().changeMapValue("startGame", "");
					}
					c.sendData("listPlayers=true");
					System.out.println("omg");
					System.out.println(c.getRit().getStr());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		t.start();
		while (!gameStarted) {
//			System.out.println("wtf ? " + backToMenu);
//			a = enter.nextInt();
//			switch (a) {
//			case 1:
//				backToMenu = true;
//				c.disconnect();
//				break;
//			}
//			String b ="";
//			while(b!="quit"){
//				b=enter.next();
//				c.sendData(b);
//			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(gameStarted){
			startGame();
			gameStarted=false;
		}
	}

	public static void viewScores() {
		FileManager.read();
	}

	//POur changer le nom du joueur
	public static void changeName() {
		System.out.println("Enter your Name :");
		String name = enter.next();
		c.setName(name);
		System.out.println("Welcome " + c.getName());
	}

	public static void quit() {
		System.exit(0);
	}
}