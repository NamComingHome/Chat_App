package com.chat_app.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
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

    @FXML
    private ListView<String> chatbox;

    private PrintWriter out;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Socket socket = new Socket("localhost",99);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            lbStatus.setText("Connected");
            new Thread(() ->{
                try{
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        String finalMsg = "You:"+ msg;
                        Platform.runLater(() -> chatbox.getItems().add(finalMsg + "\n"));
                        System.out.println(finalMsg);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //custom đoạn chat
        chatbox.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(String msg, boolean empty) {
                super.updateItem(msg, empty);
                if (empty || msg == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String[] message = msg.split(":");
                    Label label = new Label(message[1]);
                    label.setWrapText(true);

                    if (message[0].equals("You")) {
                        label.setStyle("-fx-background-color: lightgreen; -fx-alignment: center-right;");
                    } else {
                        label.setStyle("-fx-background-color: lightblue;");
                    }

                    setGraphic(label);
                }
            }
        });

    }




    @FXML
    public void actionSend(){
        String text = tfMessage.getText();
        if (!text.isEmpty()) {
            out.println(text);
            tfMessage.clear();
            tfMessage.requestFocus();
            chatbox.scrollTo(chatbox.getItems().size() - 1);
        }
    }
    @FXML
    void actionEnter(ActionEvent event) {
        String text = tfMessage.getText();
        if (!text.isEmpty()) {
            out.println(text);
            tfMessage.clear();
            tfMessage.requestFocus();
            chatbox.scrollTo(chatbox.getItems().size() - 1);
        }
    }
}

