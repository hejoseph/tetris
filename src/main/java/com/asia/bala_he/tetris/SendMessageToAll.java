package com.asia.bala_he.tetris;


import java.net.Socket;
import java.util.List;

public class SendMessageToAll implements Runnable {

	private Socket socket ;
	private List<Socket> clients;
	
	private boolean endOfWhile =true;
	
	public SendMessageToAll(Socket s, List<Socket> clients){
		this.socket = s;
		this.clients = clients;
	}
	
	
	public void run() {
		while(endOfWhile)
		{
				
			readInput ri = new readInput(socket);
			String str = ri.getStr();
				
			for (int i = 0; i < this.clients.size(); i++){
			
				if(this.clients.get(i)!=socket && this.clients.get(i)!=null){
				
					
					if(str.equals("malus")){
						
						WriteOutput wo = new WriteOutput(this.clients.get(i), "oh shit");
	
					}
			
				}	
			
			}
						
		}
			
	}
	
}
