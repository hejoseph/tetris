package com.asia.bala_he.GameManager.PieceManager;

//role : handle piece, generate random piece, 
public class PieceManager {
	private PieceFactory pf;

	
	
	public PieceManager(PieceFactory pf) {
		this.pf = new PieceFactory();
	}

	// Choose a random number between 1 and 7
	public static int chooseRandomnNumber_1to7() {

		int piece_num = 1 + (int) (Math.random() * 7);

		return piece_num;
	}

	public Piece generateRandomPiece() {
		String piece_num = chooseRandomnNumber_1to7() + "";
		Piece p = this.pf.getPiece(piece_num);
		return p;
	}

}
