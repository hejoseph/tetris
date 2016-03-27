package com.asia.bala_he.GameManager.BoardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.Piece;
import com.asia.bala_he.GameManager.PieceManager.PieceFactory;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;

public class BoardTest {
	
	PieceFactory pf = new PieceFactory();
	
	PieceManager pm = new PieceManager(pf);
	
	BoardManager bm = new BoardManager(new int[21][18],this.pf.getPiece(1+""), null,3,3);
	
	int[][] board= new int[21][18];
	
		
	
	
	@Test
	public void shouldInitiateBordersForTheBoard(){
		
		
		for(int j=0;j<4;j++){
			assertFalse(bm.getBoard()==board);
		}
		
		
	}
	
	@Test
	public void shouldFillTheBoardWithPiece(){
		BoardManager bm2 = new BoardManager(new int[21][18],this.pf.getPiece(1+""), null,3,3);
		bm2.fillBoardWithCurrentPiece();
		
		boolean equal = true;
		
		
		for(int i=0 ; i<4;i++){
			
			for(int j=0 ; j<4; j++)
			{
				if(bm.getBoard()[bm.getX()+i][bm.getY()+j] == bm2.getBoard()[bm2.getX()+i][bm2.getY()+j]){
					equal=true;
				}
				else{
					equal=false;
					break;
				}
			}
			if(equal==false){
				break;
			}
		}
		
		assertFalse(equal);

	}
	
	
	@Test
	public void shouldRotatePiece(){
		
		Piece p = this.pf.getPiece(1+"");
		
		bm.rotate_right();
		
		p.setOrientation((p.getOrientation()+1)%4);
	
		assertTrue(bm.getCurrent().getOrientation() == p.getOrientation());
		
		
		
	}
	
	@Test
	public void shouldMovePieceRightInTheBoard(){
		
		
		BoardManager bm2 = new BoardManager(new int[21][18],this.pf.getPiece(1+""), null,3,3);
		bm.move_right();
		bm2.setY(bm2.getY()+1);
	
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
		
		
	}
	
	@Test
	public void shouldMovePieceLeftInTheBoard(){
		
		
		BoardManager bm2 = new BoardManager(new int[21][18],this.pf.getPiece(1+""), null,3,3);
		bm.move_left();
		bm2.setY(bm2.getY()-1);
	
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
		
		
	}
	
	@Test
	public void shouldNotMovePieceRightInTheBoard(){
		
		BoardManager bm2 = new BoardManager(new int[21][18],pm.generateRandomPiece(),null,0,3);
		int y = 0;
		int k=1;
		bm2.fillBoardWithCurrentPiece();
		for(int i=3 ; i>=0;i--){
			
			for(int j= bm2.getX(); j<bm2.getX()+4; j++)
			{
				if(bm2.getBoard()[j][bm2.getY()+i]!=0 && k==1){
					
						
					y= bm2.getBoard()[0].length-4-i;
					k=0;		
				}
			}
	
		}
		
		
		while(bm2.can_move_right()){
			bm2.move_right();
		}
	
		
		
		assertTrue(bm2.getX()==0 && bm2.getY()==y);
		
		
	}
	
	@Test
	public void shouldNotMovePieceLeftInTheBoard(){
		
		BoardManager bm2 = new BoardManager(new int[21][18],pm.generateRandomPiece(),null,0,3);
		
		int y = 0;
		int k=1;
		bm2.fillBoardWithCurrentPiece();
		for(int i=0 ; i<4; i++){
			
			for(int j= bm2.getX(); j<bm2.getX()+4; j++)
			{
				if(bm2.getBoard()[j][bm2.getY()+i]!=0 && k==1){
					
						
					y= 1+i;
					k=0;		
			
				}
			}
	
		}
		
		
		while(bm2.can_move_left()){
			bm2.move_left();
		}
		
		assertTrue(bm2.getX()==0 && bm2.getY()==y);
		
		
		
	}
	
	@Test
	public void shouldGoDownInTheBoard(){
		
		BoardManager bm2 = new BoardManager(new int[21][18],this.pf.getPiece(1+""), null,3,3);
		bm.move_down();
		bm2.setX(bm2.getX()+1);
	
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
	}
	
	
	@Test
	public void shoulNotGodownInTheBoard(){
		BoardManager bm2 = new BoardManager(new int[21][18],pm.generateRandomPiece(),null,0,3);
		
		int x = 0;
		int k=1;
		bm2.fillBoardWithCurrentPiece();
		
		for(int i=3 ; i>=0; i--){
			
			for(int j= bm2.getY(); j<bm2.getY()+4; j++)
			{
				if(bm2.getBoard()[bm2.getX()+i][j]!=0 && k==1){
				
				
					x= bm2.getBoard().length-4-i;
					k=0;	

			
				}
			}
		
		}
		
		
		while(bm2.can_move_down()){
			bm2.move_down();
			
		}
		
		
		assertTrue(bm2.getX()==x && bm2.getY()==3);
		
	}

}
