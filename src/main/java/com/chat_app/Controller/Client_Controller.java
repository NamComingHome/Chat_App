package com.chat_app.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Client_Controller implements Initializable {

    @FXML
    private Label lbStatus;

    @FXML
    private TextArea chatBox;

    @FXML
    private TextField tfMessage;

    @FXML
    private Button btnSend;

    private PrintWriter out;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/send.png")));
//        ImageView icon = new ImageView(img);
//        icon.setFitHeight(16);
//        icon.setFitWidth(16);
//        btnSend.setGraphic(icon);
        try{
            Socket socket = new Socket("localhost",99);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            lbStatus.setText("Connected");
            new Thread(() ->{
               try{
                   String msg;
                   while ((msg = in.readLine()) != null) {
                       String finalMsg = msg;
                       Platform.runLater(() -> chatBox.appendText(finalMsg + "\n"));
                       System.out.println(finalMsg);
                   }
               }catch (IOException e){
                   e.printStackTrace();
               }
            }).start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void actionSend(){
        String text = tfMessage.getText();
        if (!text.isEmpty()) {
            out.println(text);
            tfMessage.clear();
            System.out.println(text);
        }
    }

    @FXML
    void actionEnter(ActionEvent event) {
        String text = tfMessage.getText();
        if (!text.isEmpty()) {
            out.println(text);
            tfMessage.clear();
        }
    }
}

