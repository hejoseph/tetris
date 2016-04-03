package com.asia.bala_he.GameManager.PieceManager;

import java.util.Arrays;

//Classe permettant d'agir sur les propri�t�s d'une pi�ce
public class Piece {
	
	private int orientation;//orientation dela pi�ce
	
	private int[][] data;
	

	public Piece(int[][] data) {
		this.data = data;
		this.orientation=0;
	}
	


	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int[][] getData() {
		return data;
	}
	
	public int[] getPiece(){
		return this.getData()[orientation];
	}
	
	public int[] getPieceAtOrientation(int o){
		return this.getData()[o];
	}

	@Override
	public String toString() {
		return "Piece [data=" + Arrays.toString(data) + "]";
	}

	
}
