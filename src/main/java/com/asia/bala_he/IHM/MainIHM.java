package com.asia.bala_he.IHM;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.*;


public class MainIHM {
	public static void main(String[] args) {
		System.out.println("hey");
		Piece p = new PieceFactory().getPiece("1");
//		displayPiece(p.getData()[0]);
		
		System.out.println();
		
		BoardManager bm = new BoardManager(new int[21][10], 0, p, null);
//		displayBoard(bm.getBoard());
		bm.fillBoardWithCurrentPiece();
//		displayBoard(bm.getBoard());
		bm.eraseBoardWithCurrentPiece();
		displayBoard(bm.getBoard());
	}
	
	//Affichage de la grille
	public static void displayBoard(int[][] board){
		for(int i=0; i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				
				/*Bord en bas à gauche*/
				if(i==board.length-1 && j==0)
				{
					System.out.print("|_ ");
				}
				
				/*Bord en bas à gauche*/
				else if(i==board.length-1 && j==board[0].length-1)
				{
					System.out.print(" _|");
				}
				
				/*Bord de gauche*/
				else if(j==0)
				{
					System.out.print("|  ");
				}
				
				/*Bord de droite*/
				else if(j == board[0].length-1)
				{
					System.out.print("  |");
				}
				
				/*Bord du bas*/
				else if(i==board.length-1 && (j!=0 && j!=board[0].length-1 ))
				{
					System.out.print(" _ ");
				}
				
				/*Espacement de la grille*/
				else{
					System.out.print("   ");
				}
				//System.out.print("["+board[i][j]+"]");
				
			}
			System.out.println();
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
}
