package com.asia.bala_he.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class readInput {
	
	private String str;
	
	public readInput(Socket s){
		
		BufferedReader in;
			try {
				in = new BufferedReader(
					    new InputStreamReader(s.getInputStream())
					     );
				this.str = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public String getStr() {
		return str;
	}
}
