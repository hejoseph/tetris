package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.asia.bala_he.NetworkManager.MessageHandler.ReadInputThread;
/** Le processus client se connecte au site fourni dans la commande
 *   d'appel en premier argument et utilise le port distant 8080.
 */
public class Client {
   static final int port = 8078;
  
   
   public static void main(String[] args){
		
		Socket socket;
		try {
			socket = new Socket("127.0.0.1",8078);
			//socket.close();
			
			Thread com = new Thread(new ReadInputThread(socket));
		    com.start();
	
			
	
			PrintWriter out = new PrintWriter(
	              new BufferedWriter(
	                 new OutputStreamWriter(socket.getOutputStream())),
	              true);
			
			while (true) {	
	
		        Scanner sc = new Scanner(System.in);
		        String message_envoye = sc.nextLine();
		     
		        out.println(message_envoye); 
		    
		    }
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
 
}