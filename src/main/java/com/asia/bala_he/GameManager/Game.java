package com.asia.bala_he.GameManager;

import com.asia.bala_he.GameManager.BoardManager.BoardManager;
import com.asia.bala_he.GameManager.PieceManager.PieceManager;
import com.asia.bala_he.NetworkManager.ConnectionHandler.Client;


//role:handle a tetris game,
public class Game implements Runnable{
	private BoardManager bm;
	private PieceManager pm;
	private int score;
	private boolean endOfGame;
	private Client player;
	private int speed;

	public Game(BoardManager bm, PieceManager pm, Client player) {
		this.bm = bm;
		this.pm = pm;
		this.score = 0;
		this.endOfGame = false;
		this.player = player;
		this.speed=1000;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Client getPlayer() {
		return player;
	}

	public void setPlayer(Client player) {
		this.player = player;
	}

	public void run() {
		System.out.println("hello");
		this.playAGame();
	}

	public BoardManager getBoardManager(){
		return this.bm;
	}

	public boolean getEndOfGame(){
		return this.endOfGame;
	}

	public void isEndOfGame() {
		for(int i=0;i<bm.getBoard()[0].length;i++){
			if(bm.getBoardValueAt(0, i)!=8 && bm.getBoardValueAt(0, i)!=0){
				System.out.println("End Of Game !");
				this.endOfGame = true;
			}
		}
	}

	//Fonction permettant de commencer le jeu
	public void playAGame() {
		System.out.println("playing game");
		
		while (!this.endOfGame) {

			String malus = receivedMalus(); //Réception d'un manus

			if(!malus.equals("")){
				System.out.println("receivedMalus");
				bm.eraseBoardWithCurrentPiece();


				handleMalus(malus);
			} 

			
			bm.fillBoardWithCurrentPiece();

			if(bm.can_move_down()){
				bm.move_down();
			}else{
				this.isEndOfGame();
				int malusId = bm.deleteFilledRows();
				int lastScore=this.score;
				this.score+=malusId;
				if(lastScore!=this.score){
					if(this.score%10==0){
						this.player.sendData("malus=2");
					} 
					else if(this.score%5==0){
						this.player.sendData("malus=1");
					}
				}

					
							
				
				
				bm.setX(0);
				bm.setY((int)(bm.getBoard()[0].length/2)-2);
				bm.setCurrent(pm.generateRandomPiece());
			}

			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void handleMalus(String mode){
		//add row
		System.out.println(mode+" "+mode.length());
		if(mode.equals("1")){
			while(bm.can_move_down()){//move the current piece all the way down
				bm.move_down();
			}
		} else if(mode.equals("2")){ 
			this.speed=(int)this.speed/2;
		}
	}

	

	private String receivedMalus() {
		String malusReceived = this.player.getRit().getMapValue("malus");//manus mesage provenant du serveur
		if(malusReceived.equals("")){
			return "";
		}
		this.player.getRit().changeMapValue("malus", "");
		return malusReceived;
	}

	public static void clear(){
//		System.out.print("\033[H\033[2J");  
//    	System.out.flush();
//		try{
//   	 		if(System.getProperty("os.name" ).startsWith("Windows" ))
//			  Runtime.getRuntime().exec("cls" );
//			else
//			  Runtime.getRuntime().exec("clear" );
//		}catch(IOException e){
//			e.printStackTrace();
//		}
	}
	
	//Focntion pour affciher le manus sur la console 
	public void displayBoard(){
		clear();
		System.out.println("Score :"+this.score);
		String d ="";
		int[][] board = this.bm.getBoard();
		for(int l=0;l<board.length-2;l++){
			for(int c=2;c<board[0].length-2;c++){
				if(board[l][c]==0){
					
					d+="   ";
				}else{
					
					d+="[X]";
				}
				
			}
		
			d+="\n";
		}
		System.out.println(d);



	}
	

	public void setEndOfGame(boolean endOfGame) {
		this.endOfGame = endOfGame;
	}

	public void displayPiece(int[] piece){
		for(int i=0; i<piece.length;i++){
			if(i%4==0){
				System.out.println();
			}
			System.out.print("["+piece[i]+"]");
		}
		System.out.println();
	}

	

}
