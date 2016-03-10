package com.asia.bala_he.tetris;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/** Le processus client se connecte au site fourni dans la commande
 *   d'appel en premier argument et utilise le port distant 8080.
 */
public class Client {
   static final int port = 8085;

   public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", port);
        System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(
                               new InputStreamReader(socket.getInputStream())
                               );

        PrintWriter pred = new PrintWriter(
                             new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                             true);

        String str = "bonjour";
        Scanner sc = new Scanner(System.in);
        String message_envoyé = sc.nextLine();
        pred.println(message_envoyé); 
        
        pred.println("END") ;
        System.out.println("END"); 
        
        plec.close();
        pred.close();
        socket.close();
        
        
        
   }
}