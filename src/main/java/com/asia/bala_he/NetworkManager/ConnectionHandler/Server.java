package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

//Partie Serveur du r�seau
public class Server {
	static final int port = 8078;
	//increment each time a client connect to the server ... so it's unique for "this" server
	public int getClientId() {
		return this.c.getClientId();
	}

	private static List<Socket> clients = new ArrayList<Socket>();//Liste pour stocker les sockets
	private boolean inGame=false;
	private ClientConnexionThread c = null;
	
	public Server(){
		ServerSocket s;
		try {
			s = new ServerSocket(port);//Cr�ation du serveur
			c = new ClientConnexionThread(s, clients, inGame);//Etablissement de la connexion avec le client
			Thread t = new Thread(c);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Socket soc = s.accept();
		
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
