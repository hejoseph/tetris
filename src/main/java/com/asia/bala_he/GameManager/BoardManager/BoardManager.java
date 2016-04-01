package com.asia.bala_he.GameManager.BoardManager;

import java.awt.DisplayMode;

import com.asia.bala_he.GameManager.Game;
import com.asia.bala_he.GameManager.PieceManager.Piece;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;

//role:handle piece in board, movement piece, 
public class BoardManager {
	private PieceManager pm;

	private int[][] board;
	// private int score;
	private Piece current;
	private Piece next;

	private int x;
	private int y;
	
	public int rowToDelete;

	public BoardManager(int[][] board, Piece current, Piece next, int x, int y) {
		// this.score = score;
		this.current = current;
		this.next = next;
		this.x = x;
		this.y = y;

		// for (int i = 0; i < board[20].length; i++) {
		// board[20][i] = 1;
		// }

		this.board = initBoard(board);

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

	// return true if current piece can move down
	public boolean can_move_down() {
		// this.eraseBoardWithCurrentPiece();
		if (possible_move(this.x + 1, this.y, this.current.getPiece())) {
			// this.fillBoardWithCurrentPiece();
			return true;
		}
		// this.fillBoardWithCurrentPiece();
		return false;
	}

	// move the current piece down of the board
	public void move_down() {
		if (this.can_move_down()) {
			this.eraseBoardWithCurrentPiece();
			this.x = this.x + 1;
			this.fillBoardWithCurrentPiece();
		}
			
	}

	// check if current piece is overlapping another piece on the board
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

	// can rotate the current piece to the right?
	public boolean can_rotate_right() {
		int o = this.current.getOrientation();
		o++;
		o = o % 4;
		if (possible_move(this.x, this.y, this.current.getPieceAtOrientation(o))) {
			return true;
		}
		return false;
	}

	public void rotate_right() {
		if (can_rotate_right()) {
			this.eraseBoardWithCurrentPiece();
			int o = this.current.getOrientation() + 1;
			o = o % 4;
			this.current.setOrientation(o);
			this.fillBoardWithCurrentPiece();
		}
	}

	public boolean can_move_left() {
		if (this.y > 0) {
			if (possible_move(this.x, this.y - 1, this.current.getPiece())) {
				return true;
			}
		}
		return false;
	}

	public void move_left() {
		if (can_move_left()) {
			this.eraseBoardWithCurrentPiece();
			this.y = this.y - 1;
			this.fillBoardWithCurrentPiece();
		}
	}

	public boolean can_move_right() {
		
		System.out.println("taille :" +this.board.length+", y="+this.y);
		if (this.y < this.board.length - 4) {
			if (possible_move(this.x, this.y + 1, this.current.getPiece())) {
				
				return true;
			}
		}
		return false;
	}

	public void move_right() {
		if (can_move_right()) {
			this.eraseBoardWithCurrentPiece();
			this.y = this.y + 1;
			this.fillBoardWithCurrentPiece();
		}
	}
	
	/*Suprression Ligne complet*/
	
	public void deleteFilledRows() {
		
		int count = 0;
		
		for (int i  = 0; i < this.board.length-3; i++)
		{
			count=0;
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] != 0 && this.board[i][j] != 8 ) {
					count++;
					this.rowToDelete = i;
				}
				
			}
			if(count == 12){
				deleteFilledRowAndMovePieces(i);
			}
		
		}		
	}
	
	public void deleteFilledRowAndMovePieces(int row_num) {

		for (int i = row_num; i >= 0; i--) {
			for (int j = 3; j < board[0].length-3; j++) {
					
				if(this.board[i][j] != 8 && this.board[i][j] != 0  ){
					this.board[i+1][j] = this.board[i][j];
					this.board[i][j] = 0;
				}
				
			}
		}
		
	}
	
	
	/*MAnus Ajout Ligne*/
	
	public void AddExtraRow(int row) {
		
		int i;
		for (i  = row; i < this.board.length-3; i++)
		{
			
			for (int j = 3; j < this.board[0].length-3; j++) {
				
				this.board[i-1][j] = this.board[i][j];
				this.board[i][j] = 0;
			}
			
		}
		for (int j = 3; j < this.board[0].length-3; j++){
			this.board[this.board.length-4][j] = 8;
		}
			
		
	}
	
	
	public void manusAddRow() {
		
		int i =1;
		int row=0; 
		while(row < board.length-3 && i==1){
			
			
			for (int j = 3 ; j < board[0].length-3; j++) {
				if (this.board[i][j] != 0 && this.board[i][j] != 8 ) {
					AddExtraRow(row);
					i=0;
				}
				
			}		
			
			row++;
			
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
