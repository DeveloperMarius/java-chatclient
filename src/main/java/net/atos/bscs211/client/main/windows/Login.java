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

public class Login {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button registerButton;

    @FXML
    public void login(ActionEvent event) throws IOException{
        System.out.println(username.getText());
        System.out.println(password.getText());
        // if success
        URL url = new File("src/main/resources/fxml/chat.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/fxml/register.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }

}