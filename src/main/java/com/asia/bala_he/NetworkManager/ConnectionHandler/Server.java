package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

//Partie Serveur du réseau
public class Server {
	static final int port = 8078;
	//increment each time a client connect to the server ... so it's unique for "this" server
	public int getClientId() {
		return this.c.getClientId();
	}

	private static List<Socket> clients = new ArrayList<Socket>();//Liste pour stocker les sockets
	private boolean inGame=false;
	private ClientConnexionThread c = null;
	private ServerSocket s = null;
	
	
	public Server(){
		try {
			s = new ServerSocket(port);
			acceptClient();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Socket soc = s.accept();
		
	}
	
	public void acceptClient(){
		c = new ClientConnexionThread(s, clients, inGame);
		Thread t = new Thread(c);
		t.start();
	}

	public void disconnect(){
		if(this.s!=null){
			try {
				s.close();
				c.kill();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void refuseClient(){
		c.setInGame(true);
	}
	
	public Map requestPlayer(){
		return this.c.getAllPlayer();
	}

	public static void main(String[] args) throws Exception {
		new Server();
//		ServerSocket s = new ServerSocket(port);
//		// Socket soc = s.accept();
//		Thread t = new Thread(new ClientConnexionThread(s, clients));
//		t.start();

	}

}
