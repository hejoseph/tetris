package com.asia.bala_he.GameManager.PieceManager;


//role: create piece static
public class PieceFactory {

	//Récupérer une pièce avec les différentes postions de rotation selon l'id choisi
	public Piece getPiece(String id) {
		
				/*Pièce1
		
					[1]
					[1][1]
					   [1]
				 			*/
		if (id.equalsIgnoreCase("1")) {
			int[][] array = { 
					{0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0},
					{0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0} };
			return new Piece(array);
		}
		
				/*Pièce2
				
					[2][2]
					[2][2]
			 				*/
		if (id.equalsIgnoreCase("2")) {
			int[][] array = { 
					{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
					{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
					{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0} };
			return new Piece(array);
		}
		
				/*Pièce3
				
					[3][3][3][3]
		 							*/
		if (id.equalsIgnoreCase("3")) {
			int[][] array ={ 
					{0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
					{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
					{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0} };
			return new Piece(array);
		}
		
				/*Pièce4
				
					   [4]
					[4][4]
					[4]
				 			*/
		if (id.equalsIgnoreCase("4")) {
			int[][] array = { 
					{0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
					{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0},
					{0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
					{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0} };
			return new Piece(array);
		}
		
				/*Pièce5
				
					   [5]
					[5][5][5]
			 			   		*/
		if (id.equalsIgnoreCase("5")) {
			int[][] array = { 
					{0,5,0,0,0,5,5,0,0,5,0,0,0,0,0,0},
					{0,0,0,0,0,0,5,0,0,5,5,5,0,0,0,0},
					{0,0,0,5,0,0,5,5,0,0,0,5,0,0,0,0},
					{0,5,5,5,0,0,5,0,0,0,0,0,0,0,0,0} }; 
			return new Piece(array);
		}
		
				/*Pièce6
				
					[6][6][6]
						  [6]
						   		*/
		if (id.equalsIgnoreCase("6")) {
			int[][] array = { 
					{0,0,6,0,0,0,6,0,0,6,6,0,0,0,0,0},
					{0,0,0,0,0,6,6,6,0,0,0,6,0,0,0,0},
					{0,6,6,0,0,6,0,0,0,6,0,0,0,0,0,0},
					{0,0,0,0,0,6,0,0,0,6,6,6,0,0,0,0} };
			return new Piece(array);
		}
		
				/*Pièce7
				
					[7][7][7]
				    [7]
						   		*/
		if (id.equalsIgnoreCase("7")) {
			int[][] array =  { 
					{0,7,0,0,0,7,0,0,0,7,7,0,0,0,0,0},
					{0,0,0,0,0,0,0,7,0,7,7,7,0,0,0,0},
					{0,7,7,0,0,0,7,0,0,0,7,0,0,0,0,0},
					{0,0,0,0,0,7,7,7,0,7,0,0,0,0,0,0} };
			return new Piece(array);
		}
		return null;
	}

}
