package com.asia.bala_he.NetworkManager.ConnectionHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.asia.bala_he.NetworkManager.MessageHandler.ReadInputThread;

/**
 * Le processus client se connecte au site fourni dans la commande d'appel en
 * premier argument et utilise le port distant 8080.
 */

//Classe pour la partie Client(Réseau)
public class Client {
	//Inititalisation
	static final int port = 8078;
	private String clientId = "";
	private String name;
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedWriter bw = null;
	private boolean connected = false;

	private ReadInputThread rit = null;

	private static String data = "";

	public Client(String name) {
		this.name = name;
	}

	public static void main(String[] args) {

		Client c = new Client("player");
		c.connect("127.0.0.1", 8078);
		// c.sendData("id=50&name=lol");
		while (true) {

			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			c.sendData(s);
			// System.out.println(c.getRit().getStr());
			// String data = c.getRit().getStr();
			// System.out.println(data);
			// Map m = new HashMap();
			// m = DataServant.parseIntoHashMap(data);
			// System.out.println(data+" stuf="+m.get("startGame"));
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	public void connect(String ipAddr, int port) {

		try {
			socket = new Socket(ipAddr, port);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// socket.close();
			connected = true;
			rit = new ReadInputThread(this.socket, this.connected, data);
			Thread com = new Thread(rit);
			com.start();
			bw = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			out = new PrintWriter(bw, true);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ReadInputThread getRit() {
		return rit;
	}

	public void disconnect() {
		if(this.rit!=null){
			this.rit.setConnected(false);
		}
		try {
			if(this.out!=null && this.bw!=null && this.socket!=null){
				out.close();
				bw.close();
				this.socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void newId() {
		this.setClientId(this.rit.getStr());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void sendData(String data) {
		if (connected)
			out.println(data);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
