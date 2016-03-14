package com.asia.bala_he.IHM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WelcomeText {
	
	public WelcomeText(){
		
		displaytext();
	}
	
	void displaytext(){
		try
		{
		    File f = new File ("tetris.txt");
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);
		 
		    try
		    {
		        String line = br.readLine();
		 
		        while (line != null)
		        {
		            System.out.println (line);
		            Thread.sleep(100);
		            line = br.readLine();
		        }
		 
		        br.close();
		        fr.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Problmen while reading : " + exception.getMessage());
		        
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("File not Found");
		}
	}
}




