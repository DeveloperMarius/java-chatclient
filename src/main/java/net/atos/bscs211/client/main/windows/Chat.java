package net.atos.bscs211.client.main.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Chat {

    @FXML private TextField sendMessage;
    @FXML private Button sendButton;
    @FXML private Button ausloggenButton;

    @FXML
    public void sendMessage(ActionEvent event) throws IOException{
        System.out.println(sendMessage.getText());
    }

    @FXML
    public void ausloggenButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ausloggenButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }

}