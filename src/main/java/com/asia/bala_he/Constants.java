package com.asia.bala_he;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Classe pour gérer tout ce qui est avant le commencement du jeu
public final class Constants{
	
	//Message d'acceuil
	public static void displayWelcome(){
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
		        System.out.println ("Problem while reading : " + exception.getMessage());
		        
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("3RROR 404: NOT FOUND");
		}
		System.out.println();
	}

	//Affichage des menus
	public static void displayMenu() {
		System.out.println("1 : Create/Host a game");
		System.out.println("2 : Find server/game");
		System.out.println("3 : View Scores");
		System.out.println("4 : Change your name");
		System.out.println("5 : Quit");
		System.out.println("Choose a number and press enter ...");
	}
	
	public static void displayMenu2() {
		System.out.println("1 : Start the Game");
		System.out.println("2 : Exit Room");
	}
	
	public static void displayMenu3(){
		System.out.println("Enter the ip add and port number you would like to connect ...");
	}
}




