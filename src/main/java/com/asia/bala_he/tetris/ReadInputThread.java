package com.asia.bala_he.tetris;


import java.net.Socket;

public class ReadInputThread implements Runnable{
	
	private Socket socket;
	
	public ReadInputThread(Socket s){
		this.socket = s;
	}
	public void run() {
		while(true)
		{
			
			
			readInput ri = new readInput(socket);
			String str = ri.getStr();

			if(str !=null)
			{
				System.out.println(str);
			}
				
				//System.out.println("Connection received from " + socket.getPort());
			
	
				  
		
				
		}
	}



}
