package com.asia.bala_he.NetworkManager.MessageHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*server process data when client sends to it*/
public class SendMessageToAll implements Runnable {

	private Socket socket;
	private List<Socket> clients;
	private Map player = null;
	private BufferedReader in;
	private Map m = null;
	private String str;
	private int clientId;
	private volatile boolean isRunning = true;

	

	public SendMessageToAll(Socket s, List<Socket> clients, Map player, int clientId) {
		this.socket = s;
		this.clients = clients;
		this.player = player;
		this.clientId=clientId;
	}

	public void run() {
		System.out.println("new thread send message");
		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (isRunning) {
			try {
				System.out
						.println("thread with " + clients.size() + " clients");
				Thread.sleep(0);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				System.out.println("trying to read");
				str = in.readLine();
				System.out.println("finish reading");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
				isRunning = false;
				break;
			}

			m = DataServant.parseIntoHashMap(str);
			if(m.size()>0){
				System.out.println("hello");
				if (m.containsKey("malus")) {
					sendToAll();
				}
				if(m.containsKey("name") && m.containsKey("id")){
					System.out.println("adding");
					this.player.put(m.get("id"), m.get("name"));
				}
				if(m.containsKey("requestId")){
					sendLastClientId();
				}
				if(m.containsKey("listPlayers")){
					sendListPlayer();
				}
				if(m.containsKey("closeAll")){
					closeAllSocket();
				}
				if(m.containsKey("startGame")){
					notifyAllGameStarting();
				}
			}

		}
		System.out.println("thread closed ?");
	}

	//Envoie des manus à tous les clients
	private void notifyAllGameStarting() {
		for (int i = 0; i < this.clients.size(); i++) {
			if (this.clients.get(i).isClosed()) {
				this.clients.remove(i);
				i--;
			} else if (this.clients.get(i) != socket
					&& !this.clients.get(i).isClosed()) {
				try {
					PrintWriter out;
					out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(this.clients.get(i)
									.getOutputStream())), true);
					out.println("startGame="+m.get("startGame"));
				} catch (IOException e) {
					// TODO Auto-generated catch block

					// this.clients.remove(i);
					e.printStackTrace();

				}

			}
		}
	}

	//Lorsqu'un joueur quitte le jeu ça ferme le socket
	private void closeAllSocket() {
		for(Socket s : this.clients){
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendListPlayer() {
		try {
			PrintWriter out;
			System.out.println("List");
			System.out.println(this.player.toString());
			out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(this.clients.get(this.clientId)
							.getOutputStream())), true);
			out.println("listPlayers="+this.player);
		} catch (IOException e) {
			// TODO Auto-generated catch block

			// this.clients.remove(i);
			e.printStackTrace();

		}
	}

	private void sendLastClientId() {
		try {
			PrintWriter out;
			System.out.println("last id ="+this.clientId);
			out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(this.clients.get(this.clientId)
							.getOutputStream())), true);
			out.println("id="+this.clientId);
		} catch (IOException e) {
			// TODO Auto-generated catch block

			// this.clients.remove(i);
			e.printStackTrace();

		}
	}

	private void sendToAll() {
		for (int i = 0; i < this.clients.size(); i++) {
			if (this.clients.get(i).isClosed()) {
				this.clients.remove(i);
				i--;
			} else if (this.clients.get(i) != socket
					&& !this.clients.get(i).isClosed()) {
				try {
					PrintWriter out;
					out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(this.clients.get(i)
									.getOutputStream())), true);
					out.println("malus="+m.get("malus"));
				} catch (IOException e) {
					// TODO Auto-generated catch block

					// this.clients.remove(i);
					e.printStackTrace();

				}

			}
		}
	}
	
	public Map getAllPlayer(){
		return this.player;
	}
	
	public void kill(){
		isRunning = false;
	}

}
