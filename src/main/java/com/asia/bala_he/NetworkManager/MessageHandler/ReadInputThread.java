package com.asia.bala_he.NetworkManager.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//Permet d'écouter les messages provenant du client ou du serveur
public class ReadInputThread implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private String str;
	private boolean connected;
	private Map m = new HashMap();
	private String data = "";
	
	public ReadInputThread(Socket s, boolean connected, String data) {
		this.socket = s;
		this.connected = connected;
		this.data = data;
	}

	public void run() {

		try {
			//Crée le buffer pour écouter
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}

		while (connected) {
			try {
				str = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				connected = false;
			}

			if (str != null) {
				this.data = str;
				System.out.println(this.data);
				this.m = DataServant.parseIntoHashMap(str);
//				System.out.println(m.get("malus"));
			}


		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public void changeMapValue(String key, String value){
		if(this.m.containsKey(key)){
			this.m.put(key, value);
		}
	}
	
	public String getMapValue(String key){
		if(this.m.containsKey(key)){
			return (String) this.m.get(key);
		}
		return "";
	}

}
