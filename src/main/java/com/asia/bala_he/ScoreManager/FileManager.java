package com.asia.bala_he.ScoreManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	public static void main(String[] args) {
		read();
	}
	
	
	public static void write(int nbPlayer, String name, int score){
		 // The name of the file to open.
		File log = new File("scores.txt");

		try{
		    if(!log.exists()){
		        System.out.println("We had to make a new file.");
		        log.createNewFile();
		    }

		    FileWriter fileWriter = new FileWriter(log, true);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    String s = "";
		    
		    s+=name+" "+score+" ";
		    if(nbPlayer>1){
		    	s+="Multiplayer\n";
		    } else {
		    	s+="Solo\n";
		    }
		    
		    bufferedWriter.write(s);
		    bufferedWriter.close();

		    System.out.println("Done");
		} catch(IOException e) {
		    System.out.println("COULD NOT LOG!!");
		}
	}
	
	public static void read(){
		// The name of the file to open.
        String fileName = "scores.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
}
