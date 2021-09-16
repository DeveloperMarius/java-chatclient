package net.atos.bscs211.client.main.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import net.atos.bscs211.objects.User;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

public class Register {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button toLoginButton;

    @FXML
    public void register(ActionEvent event) throws SQLException {
        if(User.exists(username.getText())) {
            User.create(username.getText(), password.getText());
            //succesfully registered message -> continue with login
        } else {

        }
    }

    public void toLogin(ActionEvent event) throws IOException, URISyntaxException {
        URL url = getClass().getResource("/fxml/login.fxml");
        if(url == null)
            throw new RuntimeException("File not found");
        url = url.toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) toLoginButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
    }
}