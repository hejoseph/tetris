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
	
	//Given that we have two board which generates piece randomly one for functions and one to compare
	BoardManager bm = new BoardManager(new int[21][18],pm.generateRandomPiece(),null,0,3);
	BoardManager bm2 = new BoardManager(new int[21][18],pm.generateRandomPiece(),null,0,3);
	
	int[][] board= new int[21][18];
	
		
	
	
	@Test
	public void shouldInitiateBordersForTheBoard(){
		
		//When we test that the borders of the board are initiated 
		for(int j=0;j<4;j++){
			
			//Then the board must be different to the empty board
			assertFalse(bm.getBoard()==board);
		}
		
		
	}
	
	@Test
	public void shouldFillTheBoardWithPiece(){
		
		//When we fill the board with a piece
		bm2.fillBoardWithCurrentPiece();
		
		boolean equal = true;
		
		//Then the board with a piece must be different to the board without a piece
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
		
		//And equal must be false
		assertFalse(equal);

	}
	
	
	@Test
	public void shouldRotatePiece(){
		
		//Given that we choose the piece 1 
		Piece p = this.pf.getPiece(1+"");
		
		//When we rotate the piece
		bm.rotate();
		
		//We set the orientation manually
		p.setOrientation((p.getOrientation()+1)%4);
	
		//Then I should have the same result while setting manually the orientation and rotating using the function
		assertTrue(bm.getCurrent().getOrientation() == p.getOrientation());
		
		
		
	}
	
	
	@Test
	public void shouldGoDownInTheBoard(){
		
		//When we move the piece on the down side
		bm.move_down();
		
		//We set the position manually
		bm2.setX(bm2.getX()+1);
	
		//Then I should have the same result while setting manually the position and moving using the function
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
	}
	
	@Test
	public void shouldMovePieceRightInTheBoard(){
		
		
		//When we move the piece on the right side
		bm.move_right();
		
		//We set the position manually
		bm2.setY(bm2.getY()+1);
	
		//Then I should have the same result while setting manually the position and moving using the function
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
		
		
	}
	
	@Test
	public void shouldMovePieceLeftInTheBoard(){
		
		
		
		//When we move the piece on the left side
		bm.move_left();
		
		//We set the position manually
		bm2.setY(bm2.getY()-1);
	
		//Then I should have the same result while setting manually the position and moving using the function
		assertTrue(bm.getX()==bm2.getX() && bm.getY()==bm2.getY());
		
		
		
	}
	
	
	
	@Test
	public void shoulNotGodownInTheBoard(){
		
		int count;
		int x=0;
		
		//Given that we fill the board with a piece
		bm.fillBoardWithCurrentPiece();
		
		//And we should find the position of the first cell of the piece on the matrix 4*4 when going from the down to up
		for(int i= bm.getX()+3; i>=bm.getX(); i--){
			count =0;
			for(int j= bm.getY(); j<bm.getY()+4; j++)
			{
				if(bm.getBoard()[i][j] != 0 ){
					count++;
				}
			}
		
			if(count>0){
				x=i-bm.getX();
				break;
			}
		}
		
		//When moving the piece down
		while(bm.can_move_down()){
			bm.move_down();
			
		}
		
		//Then the piece should not overlaps with the down border or an other piece and should stop moving
		assertTrue(bm.getBoard()[bm.getX()+x+1][bm.getY()]!=0 );
		
	}
	
	
	
	@Test
	public void shouldNotMovePieceRightInTheBoard(){
		
		int count;
		int y=0;
		
		//Given that we fill the board with a piece
		bm.fillBoardWithCurrentPiece();
		
		//And we should find the position of the first cell of the piece on the matrix 4*4 when going from the right to left
		for(int j=bm.getY()+3; j>=bm.getY(); j--){
			count =0;
			for(int i= bm.getX(); i<bm.getX()+4; i++)
			{
				if(bm.getBoard()[i][j] != 0){
					count++;
				}
			}
		
			if(count>0){
				y=j-bm.getY();
				break;
			}
		}
		
		//When moving the piece right
		while(bm.can_move_right()){
			bm.move_right();
		}
		
		//Then the piece should not overlaps with the right border or an other piece and should stop moving
		assertTrue(bm.getBoard()[bm.getX()][bm.getY()+y+1]!=0 );
		
		
	}
	
	@Test
	public void shouldNotMovePieceLeftInTheBoard(){
		
		
		int count;
		int y=0;
		
		//Given that we fill the board with a piece
		bm.fillBoardWithCurrentPiece();
		
		//And we should find the position of the first cell of the piece on the matrix 4*4 when going from the left to right
		for(int j=bm.getY(); j<bm.getY()+4; j++){
			count =0;
			for(int i= bm.getX(); i<bm.getX()+4; i++)
			{
				if(bm.getBoard()[i][j] != 0){
					count++;
				}
			}
		
			if(count>0){
				y=j-bm.getY();
				break;
			}
		}
		
		//When moving the piece left
		while(bm.can_move_left()){
			bm.move_left();
		}
		
		//Then the piece should not overlaps with the left border or an other piece and should stop moving
		assertTrue(bm.getBoard()[bm.getX()][bm.getY()+y-1]!=0);
		
		
		
	}
	
	@Test
	public void shouldRemoveCompletedLinesInTheBoard(){
		
		//Given that we filled last line of the board 
		for(int j=3; j<bm.getBoard()[0].length-3;j++){
			
			bm.getBoard()[bm.getBoard().length-4][j] = 1;
		}
		
		//Then we check that the last is filled comparing to an empty board
		for(int j=3; j<bm.getBoard()[0].length-3;j++){
			
			assertFalse(bm.getBoard()[bm.getBoard().length-4][j] == bm2.getBoard()[bm2.getBoard().length-4][j]);
		}
		
		//When we delete the completed line
		bm.deleteFilledRows();
		
		//Then the completed line must be removed
		for(int j=3; j<bm.getBoard()[0].length-3;j++){
			
			assertTrue(bm.getBoard()[bm.getBoard().length-4][j] == bm2.getBoard()[bm2.getBoard().length-4][j]);
		}
		
	}
	
	
	
	

}
