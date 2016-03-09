package com.asia.bala_he.GameManager.BoardManager;

import com.asia.bala_he.GameManager.PieceManager.Piece;

public class BoardManager {
	private int[][] board;
	private int score;
	private Piece current;
	private Piece next;

	public BoardManager(int[][] board, int score, Piece current, Piece next) {
		super();
		this.board = board;
		this.score = score;
		this.current = current;
		this.next = next;
		for (int i = 0; i < board[20].length; i++) {
			board[20][i] = 1;
		}

	}

	public void fillBoardWithCurrentPiece() {
		int a = 0;
		for (int i = this.current.getX(); i < 4; i++) {
			for (int j = this.current.getY(); j < 4; j++) {
				if (this.board[i][j] == 0 && this.current.getPiece()[a] != 0) {
					this.board[i][j] = this.current.getPiece()[a];
				}
				a++;
			}
		}
	}

	public void eraseBoardWithCurrentPiece() {
		int a = 0;
		for (int i = this.current.getX(); i < 4; i++) {
			for (int j = this.current.getY(); j < 4; j++) {
				if (this.board[i][j] == this.current.getPiece()[a]) {
					this.board[i][j] = 0;
				}
				a++;
			}
		}
	}

	public boolean move_down() {
		this.eraseBoardWithCurrentPiece();
		int y = this.current.getY() + 1;
		for (int i = this.current.getX(); i < 4; i++) {
			for (int j = y; j < 4; j++) {
				if (this.board[i][j] !=0 && this.current.getPiece()[a]!=0) {
					return false;
				}
				a++;
			}
		}
		return true;
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
