package net.atos.bscs211.objects;

import com.google.gson.Gson;
import javafx.application.Platform;
import net.atos.bscs211.client.main.Main;
import net.atos.bscs211.server.main.ChatServer;
import net.atos.bscs211.server.main.UserThread;

import java.sql.SQLException;
import java.util.HashMap;

public class SocketEvent {

    private SocketEventType event_type;
    private HashMap<String, Object> data;

    public SocketEvent(SocketEventType event_type, HashMap<String, Object> data) {
        this.event_type = event_type;
        this.data = data;
    }

    public SocketEventType getEventType() {
        return event_type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void progressClient() throws SQLException {
        User user = User.getById(((Double) getData().get("user")).intValue());
        switch (this.getEventType()){
            case CONNECT:
                //TODO load messages
                break;
            case DISCONNECT:
                //nothing
                break;
            case MESSAGE_SEND:
                Message message = Message.getById(((Double) getData().get("message")).intValue());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.chat.addMessage(user, message.getContent());
                    }
                });
                //TODO add message to message list
                break;
            case UPDATE_USER_LIST:
                //TODO update user online list
                break;
            default:
                //Event not found
                break;
        }
    }

    public void progressServer(ChatServer server, UserThread thread) throws SQLException {
        System.out.println(getData().get("user").toString());
        User user = User.getById(((Double) getData().get("user")).intValue());
        switch (this.getEventType()){
            case CONNECT:
                server.addUser(user.getId(), thread);
                //TODO send to all (not connected user) connected event
                //TODO send user_list_info to connected user
                break;
            case DISCONNECT:
                server.removeUser(user.getId());
                //TODO send to all (not connected user) disconnected event
                break;
            case MESSAGE_SEND:
                //Create message object
                Message message = Message.create(user.getId(), ((Double) getData().get("group")).intValue(), (String) getData().get("message"));
                //Override message with object id
                getData().put("message", message.getId());
                server.broadcast(this.toJson(), user.getId());
                break;
            case UPDATE_USER_LIST:
                //will never reach server
                break;
            default:
                //Event not found
                break;
        }
    }

    public String toJson(){
        return (new Gson()).toJson(this);
    }

    public static SocketEvent fromJson(String json){
        System.out.println("Create Event from JSON: " + json);
        return new Gson().fromJson(json, SocketEvent.class);
    }

    public enum SocketEventType{

        CONNECT("connect"),
        DISCONNECT("disconnect"),
        MESSAGE_SEND("message_send"),
        UPDATE_USER_LIST("update_user_list");

        private String tag;

        SocketEventType(String tag){
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }
}
