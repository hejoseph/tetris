package com.asia.bala_he.GameManager;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;
import com.asia.bala_he.IHM.RawConsoleInput;
import java.io.IOException;

//role:handle a tetris game,
public class Game implements Runnable{
	private BoardManager bm;
	private PieceManager pm;
	private int score;
	private boolean endOfGame;

	public Game(BoardManager bm, PieceManager pm) {
		this.bm = bm;
		this.pm = pm;
		this.score = 0;
		this.endOfGame = false;
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

	public void playAGame() {
		System.out.println("playing game");
		
		while (!this.endOfGame) {
			// System.out.print("\033[H\033[2J");  
   //  		System.out.flush();
			
			bm.fillBoardWithCurrentPiece();
//			displayPiece(bm.getCurrent().getPiece());
			// displayBoard();
			
//			simple_display_board(bm.getBoard());
			if(bm.can_move_down()){
				bm.move_down();
			}else{
				this.isEndOfGame();
				
			
				bm.deleteFilledRows();
				//bm.manusAddRow();
					
							
				
				
				bm.setX(0);
				bm.setCurrent(pm.generateRandomPiece());
			}
			// bm.move_right();
			// displayBoard();
			
//			simple_display_board(bm.getBoard());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// if(bm.can_move_right()){
			// 	System.out.println("right");
			// 	bm.move_right();
			// }
		}
		System.exit(0);
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

	public static void clear(){
		System.out.print("\033[H\033[2J");  
    	System.out.flush();
		try{
   	 		if(System.getProperty("os.name" ).startsWith("Windows" ))
			  Runtime.getRuntime().exec("cls" );
			else
			  Runtime.getRuntime().exec("clear" );
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void displayBoard(){
		clear();
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


		// for(int[] x : this.bm.getBoard()){
		// 	for(int y : x){
		// 		if(y!=0){
		// 			System.out.print("["+y+"]");
		// 		}else{
		// 			System.out.print("   ");
		// 		}
				
		// 	}
		// 	System.out.println();
		// }
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
	
	public static void simple_display_board(int[][] board){
		for(int[] l : board){
			for(int c : l){
				System.out.print("["+c+"]");
			}
			System.out.println();
		}
	}

}