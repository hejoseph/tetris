package com.asia.bala_he.IHM;

import com.asia.bala_he.GameManager.Game;
import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.PieceFactory;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;


public class MainIHM {
	public static void main(String[] args) {
		
		WelcomeText text = new WelcomeText();
		
//		System.out.println("hey");
		
//		String piece_num = chooseRandomnNumber_1to7() + "";
//	
//		Piece p = new PieceFactory().getPiece(piece_num);
//		displayPiece(p.getData()[0]);
		
		System.out.println();
		PieceManager pm = new PieceManager(new PieceFactory());
		BoardManager bm = new BoardManager(new int[21][10], pm.generateRandomPiece(), null,0,3);
		Game g = new Game(bm,pm);
		Thread t = new Thread(g,"game");
		t.start();
//		displayBoard(bm.getBoard());
//		bm.fillBoardWithCurrentPiece();
//		displayBoard(bm.getBoard());
//		bm.eraseBoardWithCurrentPiece();
//		simple_displayBoard(bm.getBoard());
//		bm.rotate_right();
//		bm.rotate_right();
//		bm.move_down();
//		bm.move_left();
//		bm.move_right();
//		bm.eraseBoardWithCurrentPiece();
		//simple_displayBoard(bm.getBoard());
//		displayPiece(bm.getCurrent().getData()[0]);
//		System.out.println();
//		displayBoard(bm.getBoard());
	}
	
	
	//Affichage de la grille
		public static void simple_displayBoard(int[][] board){
			for(int i=0; i<board.length;i++){
				for(int j=0;j<board[0].length;j++){
					System.out.print("["+board[i][j]+"]");
				}
				System.out.println();
			}
		}
	
	
	
	
	
	
	
}