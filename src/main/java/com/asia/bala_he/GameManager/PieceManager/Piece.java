package com.asia.bala_he.GameManager.PieceManager;

import java.util.Arrays;

public class Piece {
	
	private int orientation;
	
	private int[][] data;
	
//	private int x;
//	private int y;

	public Piece(int[][] data) {
		this.data = data;
//		this.x=0;
//		this.y=0;
		this.orientation=0;
	}
	
/*	public int getX() {
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
	}*/

	


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
