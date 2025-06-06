package com.chat_app.Model;

import com.chat_app.Process.Receive;
import com.chat_app.Process.Send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private static final List<PrintWriter> clients = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            synchronized (clients) {
                clients.add(out);
            }

            String message;
            while ((message = in.readLine()) != null) {
                broadcast(message);
            }
        } catch (IOException e) {
            System.out.println("Lỗi client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            synchronized (clients) {
                clients.remove(out);
            }
        }
    }
    private void broadcast(String message) {
        synchronized (clients) {
            for (PrintWriter writer : clients) {
                writer.println(message);
            }
        }
    }
}
