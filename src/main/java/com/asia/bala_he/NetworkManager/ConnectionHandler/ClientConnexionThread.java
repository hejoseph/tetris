package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.asia.bala_he.NetworkManager.MessageHandler.SendMessageToAll;

public class ClientConnexionThread implements Runnable {
	
	   private ServerSocket server;
	   private Socket socket;
	   private List<Socket> clients;
	   
	   public ClientConnexionThread(ServerSocket s, List<Socket> clients){
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
				  System.out.println("New player is connected");
				  
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
}