package com.asia.bala_he.GameManager.PieceTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.asia.bala_he.GameManager.PieceManager.Piece;
import com.asia.bala_he.GameManager.PieceManager.PieceFactory;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;

public class PieceTest {
	
	PieceFactory pf = new PieceFactory();
	
	PieceManager pm = new PieceManager(pf);
	
	Piece p = pm.generateRandomPiece();
	
	int tabPiece[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	@Test
	public void shouldGenerateAPiece(){
		
		for(int i=0;i<4;i++){
			assertFalse(p.getPieceAtOrientation(i)==tabPiece);
		}
		
		
	}
	
	
	
	
	
}
