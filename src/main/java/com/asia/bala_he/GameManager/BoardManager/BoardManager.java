package com.asia.bala_he.GameManager.BoardManager;

import com.asia.bala_he.GameManager.PieceManager.Piece;

public class BoardManager {
	private int[][] board;
	private int score;
	private Piece current;
	private Piece next;
	
	private int x;
	private int y;

	public BoardManager(int[][] board, int score, Piece current, Piece next, int x, int y) {
		super();
		this.board = board;
		this.score = score;
		this.current = current;
		this.next = next;
		this.x = x;
		this.y = y;
		
		for (int i = 0; i < board[20].length; i++) {
			board[20][i] = 1;
		}

	}

	public void fillBoardWithCurrentPiece() {
		int a = 0;
		for (int i = this.x; i < this.x+4; i++) {
			for (int j = this.y; j < this.y+4; j++) {
				if (this.board[i][j] == 0 && this.current.getPiece()[a] != 0) {
					this.board[i][j] = this.current.getPiece()[a];
				}
				a++;
			}
		}
	}

	public void eraseBoardWithCurrentPiece() {
		int a = 0;
		for (int i = this.x; i < this.x+4; i++) {
			for (int j = this.y; j < this.y+4; j++) {
				if (this.board[i][j] == this.current.getPiece()[a]) {
					this.board[i][j] = 0;
				}
				a++;
			}
		}
	}
	
	//return true if current piece can move down
	public boolean can_move_down() {
		this.eraseBoardWithCurrentPiece();
		if(possible_move(this.x+1, this.y, this.current.getPiece())){
			return true;
		}
		return false;
	}
	
	//move the current piece down of the board
	public void move_down(){
		if(this.can_move_down()){
			this.eraseBoardWithCurrentPiece();
			this.x=this.x+1;
			this.fillBoardWithCurrentPiece();
		}
	}
	
	//check if current piece is overlapping another piece on the board
	public boolean possible_move(int x, int y, int[] pieceToTest){
		int a = 0;
		for (int i = x; i < 4; i++) {
			for (int j = y; j < 4; j++) {
				if (this.board[i][j] !=0 && pieceToTest[a]!=0) {
					return false;
				}
				a++;
			}
		}
		return true;
	}
	
	//can rotate the current piece to the right?
	public boolean can_rotate_right(){
		int o = this.current.getOrientation();
		o++;
		o=o%4;
		if(possible_move(this.x,this.y,this.current.getPieceAtOrientation(o))){
			return true;
		}
		return false;
	}
	
	public void rotate_right(){
		if(can_rotate_right()){
			this.eraseBoardWithCurrentPiece();
			int o = this.current.getOrientation()+1;
			o=o%4;
			this.current.setOrientation(o);
			this.fillBoardWithCurrentPiece();
		}
	}
	
	
	public boolean can_move_left(){
		if(this.y>0){
			if(possible_move(this.x, this.y-1, this.current.getPiece())){
				return true;
			}
		}
		return false;
	}
	
	public void move_left(){
		if(can_move_left()){
			this.eraseBoardWithCurrentPiece();
			this.y=this.y-1;
			this.fillBoardWithCurrentPiece();
		}
	}
	
	public boolean can_move_right(){
		if(this.y<this.board.length-4){
			if(possible_move(this.x, this.y+1, this.current.getPiece())){
				return true;
			}
		}
		return false;
	}
	
	public void move_right(){
		if(can_move_right()){
			this.eraseBoardWithCurrentPiece();
			this.y=this.y+1;
			this.fillBoardWithCurrentPiece();
		}
	}
	

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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
