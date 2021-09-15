package net.atos.bscs211.client.main.windows;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;

public class Register extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            URL url = new File("src/main/java/net/atos/bscs211/client/main/windows/fxml/register.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("Register");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @FXML private TextField username;
    @FXML private PasswordField password;

    @FXML
    public void register(ActionEvent event){
        System.out.println(username.getText());
        System.out.println(password.getText());
    }

}