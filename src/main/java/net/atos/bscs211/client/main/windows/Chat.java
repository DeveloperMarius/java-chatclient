package net.atos.bscs211.client.main.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import net.atos.bscs211.client.chatuser.ChatClient;
import net.atos.bscs211.client.main.Main;
import net.atos.bscs211.objects.Group;
import net.atos.bscs211.objects.Message;
import net.atos.bscs211.objects.SocketEvent;
import net.atos.bscs211.objects.User;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class Chat {

    @FXML private TextField sendMessage;
    @FXML private Button sendButton;
    @FXML private Button ausloggenButton;
    @FXML private AnchorPane chatPanel;
    @FXML private ScrollPane scroll;
    @FXML private AnchorPane usersList;

    public Chat() throws SQLException {
        Main.chat = this;
    }

    @FXML
    public void sendMessage(ActionEvent event) throws IOException{
        if(sendMessage.getText().isEmpty()) return;
        Main.client.sendMessage(sendMessage.getText());
        this.addMessage(Main.currentUser, sendMessage.getText());
        sendMessage.clear();
    }

    @FXML
    public void ausloggenButton(ActionEvent event) throws IOException, URISyntaxException {
        URL url = getClass().getResource("/fxml/login.fxml");
        if(url == null)
            throw new RuntimeException("File not found");
        url = url.toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ausloggenButton.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", Main.currentUser.getId());
        SocketEvent event2 = new SocketEvent(SocketEvent.SocketEventType.DISCONNECT, data);
        Main.client.sendEvent(event2);
        Main.client.close();
        Main.currentUser = null;
        Main.userlist = new ArrayList<>();
    }

    public void addMessage(User user, String msg){
        int childNumber = chatPanel.getChildren().size();
        chatPanel.getChildren().add(new Text(10, 12 + 12*childNumber, user.getUsername() + ": " + msg));
        scroll.setVvalue(1.1);
    }

    public void updateUsers(ArrayList<User> usernames){
        usersList.getChildren().clear();
        for(int i = 0; i < usernames.size(); i++){
            usersList.getChildren().add(new Text(0, 12 * i + 12, usernames.get(i).getUsername()));
        }
    }

}