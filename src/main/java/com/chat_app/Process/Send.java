package com.chat_app.Process;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Send implements Runnable {
    private Socket socket;
    protected PrintWriter writer;
    private String mess;
    public Send(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            writer = new PrintWriter(
                            socket.getOutputStream()
                    );
            writer.println(mess);
            System.out.println("Message is submitted");
        }catch (Exception e){
            System.out.println("Cannot connect");
        }
    }
}
