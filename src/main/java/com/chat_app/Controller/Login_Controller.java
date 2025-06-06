package com.chat_app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
    @FXML
    private TextField tfNickname;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String nickname = tfNickname.getText();
//        String username = tfNickname.getText();
//        String password = tfPassword.getText();
//        if(username.equals("user") && password.equals("1234")){
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chat_app/client_view.fxml"));
//                Parent root = null;
//                root = loader.load();
//                Stage stage = new Stage();
//                stage.setTitle("Chat App");
//                stage.setScene(new Scene(root));
//                stage.show();
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

    }
    @FXML
    public void actionLogin(){
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        if(username.equals("user") && password.equals("1234")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chat_app/client_view.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Chat App");
                stage.setScene(scene);
                stage.show();
                Stage currentStage = (Stage) tfUsername.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
