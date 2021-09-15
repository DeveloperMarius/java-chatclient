package net.atos.bscs211.client.main.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Register {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button toLoginButton;

    @FXML
    public void register(ActionEvent event){
        System.out.println(username.getText());
        System.out.println(password.getText());
    }

    public void toLogin(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) toLoginButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }
}