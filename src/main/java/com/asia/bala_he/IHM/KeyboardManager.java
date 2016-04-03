package com.asia.bala_he.IHM;

import java.io.IOException;
import com.asia.bala_he.GameManager.*;

//Classe permettant de gérer les actions avec les touches
public class KeyboardManager extends Thread{
    private Game g;

    public KeyboardManager(Game g){
        this.g = g;
    }

    @Override
    public void run(){
            int read =0;
        while(!g.getEndOfGame()){
            try {
                read = RawConsoleInput.read(false);
                if(read!=-2){
                    if(read==27){
//                        System.exit(0);
                    	g.setEndOfGame(true);
                    }
                    if(read==114){//touche r
                        g.getBoardManager().rotate();
                    }
                    if(read==113){//touche q
                        g.getBoardManager().move_left();
                    }
                    if(read==115){//touche s
                        g.getBoardManager().move_down();
                    } 
                    if(read==100){//touche d
                        g.getBoardManager().move_right();
                    }
                    if(read==122){
                    	g.getPlayer().sendData("malus=2");
                    }
                    // System.out.println(read);
                    this.g.displayBoard();//affiche le résultat des déplacements sur la console
                }
             
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
       
    }

}