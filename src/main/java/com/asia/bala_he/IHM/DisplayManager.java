package com.asia.bala_he.IHM;

import java.io.IOException;
import com.asia.bala_he.GameManager.*;

public class DisplayManager extends Thread{
    private Game g;

    public DisplayManager(Game g){
        this.g = g;
    }

    @Override
    public void run(){
        while(!g.getEndOfGame()){
            try{
                Thread.sleep(250);
            }catch(Exception e){

            }

            g.displayBoard();
        }
    }

}