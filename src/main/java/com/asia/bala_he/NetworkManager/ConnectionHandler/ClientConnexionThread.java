package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asia.bala_he.NetworkManager.MessageHandler.SendMessageToAll;

//Classe permettant d'atablir la connexion entre un client et un serveur
public class ClientConnexionThread implements Runnable {

	private ServerSocket server;
	private Socket socket;
	private volatile List<Socket> clients;
	private boolean inGame;
	private int clientId=-1;
	private SendMessageToAll serverProcessing = null;
	private Map player = new HashMap();

	public ClientConnexionThread(ServerSocket s, List<Socket> clients, boolean inGame) {
		this.server = s;
		this.clients = clients;
		this.inGame = inGame;
	}
	
	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public void kill(){
		inGame = true;
		serverProcessing.kill();
	}

	public void run() {

		try {
			while(!inGame) {

				socket = server.accept();//Arrivée d'un nouveau client
				System.out.println("someone connected");
				this.clientId++;
				System.out.println("Client ID = "+this.clientId);//Affiche l'Id du nouveau client
				this.clients.add(socket);

				serverProcessing = new SendMessageToAll(socket, this.clients, this.player, this.clientId);

				new Thread(serverProcessing).start();
				// t2.start();
				System.out.println("New player is connected");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map getAllPlayer(){
		return this.serverProcessing.getAllPlayer();
	}
	
	public int getClientId(){
		return this.clientId;
	}
}