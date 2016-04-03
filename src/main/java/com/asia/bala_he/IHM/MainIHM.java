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

import java.util.*;
import java.util.prefs.BackingStoreException;

public class MainIHM {

	private static Scanner enter = new Scanner(System.in);
	private static boolean backToMenu = false;
	private static boolean gameStarted = false;
	private static Server s = null;
	private static Client c = null;

	public static void main(String[] args) {
		int a = 0;
		 Constants.displayWelcome();
		c = new Client("Player");
		while (true) {
			Constants.displayMenu();
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
		// String piece_num = chooseRandomnNumber_1to7() + "";
		//
		// Piece p = new PieceFactory().getPiece(piece_num);
		// displayPiece(p.getData()[0]);

		// System.out.println();

		/*
		 * PieceManager pm = new PieceManager(new PieceFactory()); BoardManager
		 * bm = new BoardManager(new int[21][18], pm.generateRandomPiece(),
		 * null, 0, 3); Game g = new Game(bm, pm); Thread t = new Thread(g,
		 * "game"); Thread k = new KeyboardManager(g); // // g.playAGame();
		 * t.start(); k.start();
		 * 
		 * Thread d = new DisplayManager(g); d.start();
		 * while(!g.getEndOfGame()){ try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		// while(true){
		// g.displayBoard();
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// displayBoard(bm.getBoard());
		// bm.fillBoardWithCurrentPiece();
		// displayBoard(bm.getBoard());
		// bm.eraseBoardWithCurrentPiece();
		// simple_displayBoard(bm.getBoard());
		// bm.rotate_right();
		// bm.rotate_right();
		// bm.move_down();
		// bm.move_left();
		// bm.move_right();
		// bm.eraseBoardWithCurrentPiece();
		// simple_displayBoard(bm.getBoard());
		// displayPiece(bm.getCurrent().getData()[0]);
		// System.out.println();
		// displayBoard(bm.getBoard());

		// int read;
		// try {
		// read = RawConsoleInput.read(false);
		// if(read==10){
		// System.exit(0);
		// }
		// System.out.println(read);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// }
	}

	// Affichage de la grille
	public static void simple_displayBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}
	}

	// public static void clientConnection(Client c, String ipAddr, int port){
	// c.connect(ipAddr,port);
	// c.setClientId(clientId);
	// }

	public static void createGame() {
		int a = 0;
		s = new Server();
		c.connect("127.0.0.1", 8078);
		c.setClientId(s.getClientId() + "");
		c.sendData("id=" + c.getClientId() + "&name=" + c.getName());
		Thread t = new Thread(new Runnable() {
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
			switch (a) {
			case 1:
				gameStarted = true;
				c.sendData("startGame=true");
				startGame(c);
				break;
			case 2:
				backToMenu = true;
				break;
			}
		}
	}

	public static void displayPlayer(Server s) {
		Map m = s.requestPlayer();
		System.out.println(m);
	}

	public static void startGame(Client c) {
		PieceManager pm = new PieceManager(new PieceFactory());
		BoardManager bm = new BoardManager(new int[21][18],
				pm.generateRandomPiece(), null, 0, 3);
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
		
	}

	public static void findServer() {
		backToMenu = false;
		// list of game server ... ?
		int a = 0;
		Constants.displayMenu3();
		c.connect("127.0.0.1", 8078);
		
//		while(true){
//			Scanner sc = new Scanner(System.in);
//			String s = sc.nextLine();
//			c.sendData(s);
//			System.out.println(c.getRit().getStr());
//		}
//		c.sendData("closeAll=true");
		c.sendData("requestId=true");
		String id = c.getRit().getStr();
		Map m = DataServant.parseIntoHashMap(id);
		c.setClientId(m.get("id")+"");
		System.out.println(c.getRit().getStr());
		System.out.println(c.getClientId());
		c.sendData("id=" + c.getClientId() + "&name=" + c.getName());
		System.out.println("Connected");
		int i = 0;
		while(i<10){
			String s = c.getRit().getStr();
			i++;
		}
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (!backToMenu && !gameStarted) {
					System.out.println(backToMenu);
					System.out.println(gameStarted);
					String s = c.getRit().getStr();
					Map m = DataServant.parseIntoHashMap(s);
					if(m.get("startGame")!=null)
						gameStarted = Boolean.parseBoolean((String) m.get("startGame"));
					c.sendData("listPlayers=true");
					System.out.println("omg");
					System.out.println(c.getRit().getStr());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				if(gameStarted){
					startGame(c);
				}
			}
		});
		t.start();
		while (!backToMenu) {
			System.out.println("wtf ? " + backToMenu);
			a = enter.nextInt();
			switch (a) {
			case 1:
				backToMenu = true;
				c.disconnect();
				break;
			}
		}
	}

	public static void viewScores() {

	}

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