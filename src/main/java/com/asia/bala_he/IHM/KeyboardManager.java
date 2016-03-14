package com.asia.bala_he.IHM;

import java.io.IOException;
import com.asia.bala_he.GameManager.*;

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
                        System.exit(0);
                    }
                    if(read==114){
                        g.getBoardManager().rotate_right();
                    }
                    if(read==113){
                        g.getBoardManager().move_left();
                    }
                    if(read==115){
                        g.getBoardManager().move_down();
                    } 
                    if(read==100){
                        g.getBoardManager().move_right();
                    }
                    // System.out.println(read);
                    this.g.displayBoard();
                }
                // try{
                //     Thread.sleep(500);
                // }catch(Exception e){
                //     e.printStackTrace();
                // }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        // int read = 0;
        // while(true){
        //     try{
        //         read = RawConsoleInput.read(false);
        //         if(read!=-2){
        //             System.out.println(read);
        //         }
        //         try{
        //             Thread.sleep(500);
        //         }catch(Exception e){
        //             e.printStackTrace();
        //         }
        //     }catch(IOException ioe){

        //     }
        // }
    }

}