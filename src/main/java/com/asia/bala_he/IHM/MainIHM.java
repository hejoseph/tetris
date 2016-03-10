package com.asia.bala_he.IHM;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.*;


public class MainIHM {
	public static void main(String[] args) {
		System.out.println("hey");
		
		String piece_num = chooseRandomnNumber_1to7() + "";
	
		Piece p = new PieceFactory().getPiece(piece_num);
//		displayPiece(p.getData()[0]);
		
		System.out.println();
		
		BoardManager bm = new BoardManager(new int[21][10], 0, p, null,0,3);
//		displayBoard(bm.getBoard());
		bm.fillBoardWithCurrentPiece();
//		displayBoard(bm.getBoard());
//		bm.eraseBoardWithCurrentPiece();
//		simple_displayBoard(bm.getBoard());
//		bm.rotate_right();
//		bm.rotate_right();
//		bm.move_down();
//		bm.move_left();
		bm.move_right();
//		bm.eraseBoardWithCurrentPiece();
		//simple_displayBoard(bm.getBoard());
		displayPiece(p.getData()[0]);
		System.out.println();
		displayBoard(bm.getBoard());
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
	
	
	//Affichage de la grille
	public static void displayBoard(int[][] board){
		for(int i=0; i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				
				/*Afficher la pi�ce*/
				
				if(board[i][j] != 0 && board[i][j] !=8)
				{
					System.out.print("["+board[i][j]+"]");
				}
				
				
				/*Bord de gauche*/
				else if(j==0)
				{
					System.out.print("[8][8]");
				}
				
				/*Bord de droite*/
				else if(j == board[0].length-1)
				{
					System.out.print("[8][8]");
				}
				
				/*Bord du bas*/
				else if(i==board.length-1 )
				{
					System.out.print("["+board[i][j]+"]");
				}
				
				/*Espacement de la grille*/
				else{
					System.out.print("   ");
				}
				//System.out.print("["+board[i][j]+"]");
				
			}
			System.out.println();
			
		}
		//Afichage de la deuxieme bord en bas
		for (int i = 0; i < board[20].length+2; i++) 
		{
			System.out.print("[8]");
		}
		
		
	}
	
	public static void displayPiece(int[] piece){
		for(int i=0; i<piece.length;i++){
			if(i%4==0){
				System.out.println();
			}
			System.out.print("["+piece[i]+"]");
		}
	}
	
	//Choose a random number between 1 and 7
	public static int chooseRandomnNumber_1to7(){
		
		int piece_num = 1 + (int)(Math.random() * 7); 
		
		return piece_num;
	}
}
