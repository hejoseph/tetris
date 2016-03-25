package com.asia.bala_he.NetworkManager.MessageHandler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadInputThread implements Runnable{
	
	private Socket socket;
	BufferedReader in;
	private String str;
	private boolean eow = true;
	
	public ReadInputThread(Socket s){
		this.socket = s;
	}
	public void run() {
		
		
		try {
			in = new BufferedReader(
				    new InputStreamReader(socket.getInputStream())
				     );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		while(eow)
		{
			
			
	
			try {
				str = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			if(str !=null)
			{
				System.out.println(str);
			}
				
				//System.out.println("Connection received from " + socket.getPort());
			
				
		}
	}



}
