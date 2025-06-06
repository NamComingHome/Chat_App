package com.chat_app.Process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {
    private Socket socket;
    public Receive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            while(true){
                String mess;
                mess = reader.readLine();
                System.out.println("Mess: " + mess);
            }
        }catch (Exception e){
            System.out.println("Cannot connect");
        }
    }
}
