package com.chat_app.Model;

import com.chat_app.Process.Receive;
import com.chat_app.Process.Send;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(){
        try{
            ServerSocket serverSocket = new ServerSocket(99);
            while(true){
                Socket clientSocket = serverSocket.accept();
                Send serverSend = new Send(clientSocket);
                Receive serverReceive = new Receive(clientSocket);
                new Thread(serverReceive).start();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
