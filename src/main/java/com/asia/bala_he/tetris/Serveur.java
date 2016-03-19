package com.asia.bala_he.tetris;
import java.util.List;
import java.net.*;
import java.util.ArrayList;

public class Serveur {
   static final int port = 8078;
   
   private static List<Socket> clients = new ArrayList<Socket>();
   
   

   public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(port);
       // Socket soc = s.accept();
        Thread t = new Thread(new connectClient(s,clients));
        t.start();
        
        
   }
   
   
}

