package com.asia.bala_he.GameManager;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;
import com.asia.bala_he.IHM.RawConsoleInput;
import com.asia.bala_he.NetworkManager.ConnectionHandler.Client;

import java.io.IOException;

//role:handle a tetris game,
public class Game implements Runnable{
	private BoardManager bm;
	private PieceManager pm;
	private int score;
	private boolean endOfGame;
	private Client player;
	private int speed;

	public Game(BoardManager bm, PieceManager pm, Client player) {
		this.bm = bm;
		this.pm = pm;
		this.score = 0;
		this.endOfGame = false;
		this.player = player;
		this.speed=1000;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Client getPlayer() {
		return player;
	}

	public void setPlayer(Client player) {
		this.player = player;
	}

	public void run() {
		System.out.println("hello");
		this.playAGame();
	}

	public BoardManager getBoardManager(){
		return this.bm;
	}

	public boolean getEndOfGame(){
		return this.endOfGame;
	}

	public void isEndOfGame() {
		for(int i=0;i<bm.getBoard()[0].length;i++){
			if(bm.getBoardValueAt(0, i)!=8 && bm.getBoardValueAt(0, i)!=0){
				System.out.println("End Of Game !");
				this.endOfGame = true;
			}
		}
	}

	//Fonction permettant de commencer le jeu
	public void playAGame() {
		System.out.println("playing game");
		
		while (!this.endOfGame) {

			String malus = receivedMalus(); //Réception d'un manus

			if(!malus.equals("")){
				System.out.println("receivedMalus");
				bm.eraseBoardWithCurrentPiece();


				handleMalus(malus);
			} 

			
			bm.fillBoardWithCurrentPiece();

			if(bm.can_move_down()){
				bm.move_down();
			}else{
				this.isEndOfGame();
				int malusId = bm.deleteFilledRows();
				int lastScore=this.score;
				this.score+=malusId;
				System.out.println("id : "+malusId);
				if(lastScore!=this.score){
					if(this.score%2==0){
						System.out.println("Sending");
						this.player.sendData("malus=2");
					} else if(this.score%3==0){
						this.player.sendData("malus=3");
					} else if(this.score%1==0){
						this.player.sendData("malus=1");
					}
				}

					
							
				
				
				bm.setX(0);
				bm.setY((int)(bm.getBoard()[0].length/2)-2);
				bm.setCurrent(pm.generateRandomPiece());
			}

			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// if(bm.can_move_right()){
			// 	System.out.println("right");
			// 	bm.move_right();
			// }
		}
	}
	
	private void handleMalus(String mode){
		//add row
		System.out.println(mode+" "+mode.length());
		if(mode.equals("1")){
			while(bm.can_move_down()){//move the current piece all the way down
				bm.move_down();
			}
		} else if(mode.equals("2")){ 
			this.speed=(int)this.speed/2;
		} else if(mode.equals("3")){
			this.bm.malusAddRow();
		}
	}

	// Affichage de la grille = function noob
//	public void displayBoard(int[][] board) {
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//
//				/* Afficher la pi¨¨ce */
//
//				if (board[i][j] != 0 && board[i][j] != 8) {
//					System.out.print("[" + board[i][j] + "]");
//				}
//
//				/* Bord de gauche */
//				else if (j == 0) {
//					System.out.print("[8][8]");
//				}
//
//				/* Bord de droite */
//				else if (j == board[0].length - 1) {
//					System.out.print("[8][8]");
//				}
//
//				/* Bord du bas */
//				else if (i == board.length - 1) {
//					System.out.print("[" + board[i][j] + "]");
//				}
//
//				/* Espacement de la grille */
//				else {
//					System.out.print("   ");
//				}
//				// System.out.print("["+board[i][j]+"]");
//
//			}
//			System.out.println();
//
//		}
//		// Afichage de la deuxieme bord en bas
//		for (int i = 0; i < board[0].length + 2; i++) {
//			System.out.print("[8]");
//		}
//		
//		System.out.println();
//
//	}

	private String receivedMalus() {
		String malusReceived = this.player.getRit().getMapValue("malus");//manus mesage provenant du serveur
		if(malusReceived.equals("")){
			return "";
		}
		this.player.getRit().changeMapValue("malus", "");
		return malusReceived;
	}

	public static void clear(){
//		System.out.print("\033[H\033[2J");  
//    	System.out.flush();
//		try{
//   	 		if(System.getProperty("os.name" ).startsWith("Windows" ))
//			  Runtime.getRuntime().exec("cls" );
//			else
//			  Runtime.getRuntime().exec("clear" );
//		}catch(IOException e){
//			e.printStackTrace();
//		}
	}
	
	//Focntion pour affciher le manus sur la console 
	public void displayBoard(){
		clear();
		System.out.println("Score :"+this.score);
		String d ="";
		int[][] board = this.bm.getBoard();
		for(int l=0;l<board.length-2;l++){
			for(int c=2;c<board[0].length-2;c++){
				if(board[l][c]==0){
					// System.out.print("   ");
					d+="   ";
				}else{
					// System.out.print("[X]");
					d+="[X]";
				}
				// }else if(board[l][c]==0){
					// System.out.print("   ");
				// }else{
				// }
			}
			// System.out.println();
			d+="\n";
		}
		System.out.println(d);



	}
	

	public void setEndOfGame(boolean endOfGame) {
		this.endOfGame = endOfGame;
	}

	public void displayPiece(int[] piece){
		for(int i=0; i<piece.length;i++){
			if(i%4==0){
				System.out.println();
			}
			System.out.print("["+piece[i]+"]");
		}
		System.out.println();
	}

	

}
