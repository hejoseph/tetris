package com.asia.bala_he.GameManager.PieceManager;


//piece static
public class PieceFactory {

	public Piece getPiece(String id) {
		if (id.equalsIgnoreCase("1")) {
			int[][] array = {
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 } };
			return new Piece(array);
		}
		return null;
	}

}
