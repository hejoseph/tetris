package com.asia.bala_he.GameManager.BoardManager;

import java.awt.DisplayMode;

import com.asia.bala_he.GameManager.Game;
import com.asia.bala_he.GameManager.PieceManager.Piece;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;


/*Classe utilis�e pour :
  		- gestion du Board
  		- gestion des mouvements de la pi�ce
 */
public class BoardManager {
	private PieceManager pm;

	private int[][] board;
	// private int score;
	private Piece current;
	private Piece next;

	private int x;
	private int y;
	
	public int rowToDelete;

	//Prend en param�tre:un tableau 2D pour le board, une nouvelle pi�ce, les positions initiales de cette pi�ce en x et y 
	public BoardManager(int[][] board, Piece current, Piece next, int x, int y) {
		// this.score = score;
		this.current = current;
		this.next = next;
		this.x = x;
		this.y = y;

		
		this.board = initBoard(board);//Initialise le board avec les bordures

	}
	
	public int getBoardValueAt(int l, int c){
		return this.board[l][c];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PieceManager getPm() {
		return pm;
	}

	public void setPm(PieceManager pm) {
		this.pm = pm;
	}

	//Fonction permettant d'initialiser les bordures des boards � 8
	public int[][] initBoard(int[][] board) {
		if (!(board.length > 5 && board[0].length > 8)) {
			return null;
		}
		for (int i = 0; i < board.length; i++) {
			board[i][0] = 8;
			board[i][1] = 8;
			board[i][2] = 8;
			board[i][board[0].length - 1] = 8;
			board[i][board[0].length - 2] = 8;
			board[i][board[0].length - 3] = 8;
		}
		for (int i = 0; i < board[0].length; i++) {
			board[board.length - 1][i] = 8;
			board[board.length - 2][i] = 8;
			board[board.length - 3][i] = 8;
		}
		return board;
	}

	//Fonction permettant de remplir le board avec la pi�ce
	public void fillBoardWithCurrentPiece() {
		int a = 0;
		// System.out.println("x:"+this.x+" y:"+this.y);
		for (int i = this.x; i < this.x + 4; i++) {
			for (int j = this.y; j < this.y + 4; j++) {
				if (this.board[i][j] == 0 && this.current.getPiece()[a] != 0) {
					this.board[i][j] = this.current.getPiece()[a];
				}
				a++;
			}
		}
	}

	//Fonction permettant d'enlever la pi�ce du board
	public void eraseBoardWithCurrentPiece() {
		int a = 0;
		for (int i = this.x; i < this.x + 4; i++) {
			for (int j = this.y; j < this.y + 4; j++) {
				if (this.board[i][j] == this.current.getPiece()[a]) {
					this.board[i][j] = 0;
				}
				a++;
			}
		}
	}

	// Fonction permettant de v�rifier si la pi�ce peut se d�place en bas
	public boolean can_move_down() {
		// this.eraseBoardWithCurrentPiece();
		if (possible_move(this.x + 1, this.y, this.current.getPiece())) {
			// this.fillBoardWithCurrentPiece();
			return true;
		}
		// this.fillBoardWithCurrentPiece();
		return false;
	}

	// Fonction permettant de faire d�placer la pi�ce en bas
	public void move_down() {
		if (this.can_move_down()) {
			this.eraseBoardWithCurrentPiece();//On efface la pi�ce
			this.x = this.x + 1;
			this.fillBoardWithCurrentPiece();//On r�affiche la pi�ce avec sa nouvelle position
		}
			
	}

	// Fonction qui prend en param�tre la pi�ce et sa nouvelle position � tester et permet de tester le d�placement
	public boolean possible_move(int x, int y, int[] pieceToTest) {
		this.eraseBoardWithCurrentPiece();
		int a = 0;
		for (int i = x; i < x+4; i++) {
			for (int j = y; j < y+4; j++) {
				if (this.board[i][j] != 0 && pieceToTest[a] != 0) {
					this.fillBoardWithCurrentPiece();
					return false;
				}
				a++;
			}
		}
		this.fillBoardWithCurrentPiece();
		return true;
	}

	// Fonction permettant de v�rifier si la pi�ce peut tourner
	public boolean can_rotate() {
		int o = this.current.getOrientation();
		o++;
		o = o % 4; //4 position diff�rentes
		if (possible_move(this.x, this.y, this.current.getPieceAtOrientation(o))) {
			return true;
		}
		return false;
	}

	// Fonction permettant de tourner la pi�ce
	public void rotate() {
		if (can_rotate()) {
			this.eraseBoardWithCurrentPiece();
			int o = this.current.getOrientation() + 1;
			o = o % 4;
			this.current.setOrientation(o);
			this.fillBoardWithCurrentPiece();
		}
	}

	// Fonction permettant de v�rifier si la pi�ce peut se d�placer � gauche
	public boolean can_move_left() {
		if (this.y > 0) {
			if (possible_move(this.x, this.y - 1, this.current.getPiece())) {
				return true;
			}
		}
		return false;
	}
	
	// Fonction permettant de d�placer la pi�ce � gauche
	public void move_left() {
		if (can_move_left()) {
			this.eraseBoardWithCurrentPiece();
			this.y = this.y - 1;
			this.fillBoardWithCurrentPiece();
		}
	}

	// Fonction permettant de v�rifier si la pi�ce peut se d�placer � doite
	public boolean can_move_right() {
		
		System.out.println("taille :" +this.board.length+", y="+this.y);
		if (this.y < this.board.length - 4) {
			if (possible_move(this.x, this.y + 1, this.current.getPiece())) {
				
				return true;
			}
		}
		return false;
	}

	// Fonction permettant de d�placer la pi�ce � droite
	public void move_right() {
		if (can_move_right()) {
			this.eraseBoardWithCurrentPiece();
			this.y = this.y + 1;
			this.fillBoardWithCurrentPiece();
		}
	}
	
	
	//Fonction permettant de supprimer une ligne remplie
	public int deleteFilledRows() {
		
		int count = 0;
		int nbRowDeleted = 0;
		
		//On cherche si on a des lignes remplies
		for (int i  = 0; i < this.board.length-3; i++)
		{
			count=0;
			for (int j = 3; j < this.board[0].length-3; j++) {
				if (this.board[i][j] != 0 && this.board[i][j] != 8 ) {
					count++;
					this.rowToDelete = i;
				}
				
			}
			//Si une ligne est remplie
			if(count == this.board[0].length-6){
				malusAddRow();
				//deleteFilledRowAndMovePieces(i);//Supprime la ligne et d�cale les pi�ces vers le bas
				//System.out.println("shjgdsjhgds");
				nbRowDeleted++;
			}
		
		}
		return nbRowDeleted;
	}
	
	//Fonction permettant de supprimer une ligne et d�caler les pi�ces vers le bas
	public void deleteFilledRowAndMovePieces(int row_num) {

		int k=3;
		
		//Suppresion de la ligne
		while(k<board[0].length-3){
			
			this.board[row_num][k] = 0;
			k++;
			
		}
		
		//On d�cale le reste vers le bas
		for (int i = row_num-1; i >= 0; i--) {
			for (int j = 3; j < board[0].length-3; j++) {
					
				this.board[i+1][j] = this.board[i][j];
							
			}
		}
		
	}
	
	

	//Manus permettant d'ajouter une ligne
	public void malusAddRow() {
		
		int i=1;
		int row=0; 
		while(row < board.length-3 && i==1){
			
			
			for (int j = 3 ; j < board[0].length-3; j++) {

				if (this.board[row][j] != 0 ) {

					AddExtraRow(row);
					i=0;
				}
				
			}		
			
			row++;
			
		}
	
		
	}
	
	//Fonction permettant d'ajouter une ligne et d�caler le reste vers le haut
	public void AddExtraRow(int row) {
		System.out.println("RowNumber");
		System.out.println(row);
		for (int i  = row; i < this.board.length-3; i++)
		{
			
			for (int j = 3; j < this.board[0].length-3; j++) {
					
				
					if(this.board[i][j] !=0  ){
						this.board[i-1][j] = this.board[i][j];//D�cale les pi�ces vers le haut
						this.board[i][j] =0;
					}
			
				
			}
			
			
		}
		for (int j = 3; j < this.board[0].length-3; j++){
			
			this.board[this.board.length-4][j] = 8;//Ajoute une ligne
		
		}
			
		
	}
	
	
	

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	// public int getScore() {
	// return score;
	// }
	//
	// public void setScore(int score) {
	// this.score = score;
	// }

	public Piece getCurrent() {
		return current;
	}

	public void setCurrent(Piece current) {
		this.current = current;
	}

	public Piece getNext() {
		return next;
	}

	public void setNext(Piece next) {
		this.next = next;
	}

}
