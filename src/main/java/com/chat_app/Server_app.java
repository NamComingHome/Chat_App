package com.chat_app;


import com.chat_app.Model.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server_app {
    private static final int PORT = 99;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server đang chạy...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client đã kết nối: " + clientSocket);
            new ClientHandler(clientSocket).start();
        }
    }
}
