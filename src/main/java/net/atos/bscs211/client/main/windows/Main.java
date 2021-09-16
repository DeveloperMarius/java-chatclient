package net.atos.bscs211.client.main.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.atos.bscs211.client.chatuser.ChatClient;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            URL url = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("Java Chat App");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
}
