package net.atos.bscs211.client.main.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import net.atos.bscs211.client.chatuser.ChatClient;
import net.atos.bscs211.client.main.Main;
import net.atos.bscs211.objects.User;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {


    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button registerButton;
    @FXML private Text error;

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException, URISyntaxException {
        error.setText("");
        if(User.exists(username.getText()) && User.login(username.getText(), password.getText())) {
            Main.currentUser = User.getByUsername(username.getText());
            Main.client = new ChatClient("localhost", 8888);
            Main.userlist = new ArrayList<User>();
            Main.client.execute();
            URL url = getClass().getResource("/fxml/chat.fxml");
            if(url == null)
                throw new RuntimeException("File not found");
            url = url.toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } else {
            error.setText("Login fehlgeschlagen.");
        }
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException, URISyntaxException {
        URL url = getClass().getResource("/fxml/register.fxml");
        if(url == null)
            throw new RuntimeException("File not found");
        url = url.toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }

}