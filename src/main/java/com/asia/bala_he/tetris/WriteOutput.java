package com.asia.bala_he.tetris;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteOutput {
	
	public WriteOutput(Socket s, String str){
		
		PrintWriter out;
		
			try {
				out = new PrintWriter(
				    new BufferedWriter(
				         new OutputStreamWriter(s.getOutputStream())), 
				      true);
				out.println(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	

}

