package com.asia.bala_he.tetris;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class connectClient implements Runnable {
	
	   private ServerSocket server;
	   private Socket socket;
	   private int nbrclient = 1;
	   private List<Socket> clients;
	   
	   public connectClient(ServerSocket s, List<Socket> clients){
			this.server = s;
			this.clients =clients;
		}
		
		public void run() {

	        try {
	        	while(true){
	        		
	        	  	
				  socket = server.accept(); 
				  
				  this.clients.add(socket);
				  
				  Thread t2 = new Thread(new SendMessageToAll(socket,this.clients));
			      t2.start();
				  System.out.println("Client "+nbrclient+ " is here");
				  nbrclient++;
				  
				  //Thread com = new Thread(new sendMessage(socket));
			      //com.start();
				  
				  
				  //socket.close();
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
}