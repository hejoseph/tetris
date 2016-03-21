package com.asia.bala_he.NetworkManager.MessageHandler;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SendMessageToAll implements Runnable {

	private Socket socket ;
	private List<Socket> clients;
	
	private BufferedReader in;
	private PrintWriter out;
	
	private String str;
	
	public SendMessageToAll(Socket s, List<Socket> clients){
		this.socket = s;
		this.clients = clients;
	}
	
	
	public void run() {
		
		try {
			
			in = new BufferedReader(
				    new InputStreamReader(socket.getInputStream())
				     );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true)
		{
			

			
			try {
				str = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				
			for (int i = 0; i < this.clients.size(); i++){
			
				if(this.clients.get(i)!=socket && this.clients.get(i)!=null){
				
					
					if(str.equals("malus")){
						
						try {
							out = new PrintWriter(
							    new BufferedWriter(
							         new OutputStreamWriter(this.clients.get(i).getOutputStream())), 
							      true);
							out.println("oh shit");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
	
					}
					if(str.equals("quit")){
						
					
							//this.clients.get(i).close();
							this.clients.remove(i);
							
						
	
					}
			
				}	
			
			}
						
		}
			
	}
	
}
