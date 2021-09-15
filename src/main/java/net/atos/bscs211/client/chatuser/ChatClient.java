package net.atos.bscs211.client.chatuser;

import com.google.gson.JsonObject;
import net.atos.bscs211.client.main.Main;
import net.atos.bscs211.objects.SocketEvent;

import java.net.*;
import java.io.*;
import java.util.HashMap;

public class ChatClient {

    private String hostname;
    private int port;
    private Socket socket;
    private PrintWriter writer;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            socket = new Socket(hostname, port);

            System.out.println("Connected to the Chat");

            new ReadThreat(socket).start();

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            //Send connect event
            HashMap<String, Object> data = new HashMap<>();
            data.put("user", Main.currentUser.getId());
            data.put("group", Main.group);
            sendEvent(new SocketEvent(SocketEvent.SocketEventType.CONNECT, data));

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }

    public void close(){
        try {
            this.writer.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEvent(SocketEvent event){
        writer.println(event.toJson());
    }

    public void sendMessage(String content){
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", Main.currentUser.getId());
        data.put("group", Main.group);
        data.put("message", content);
        sendEvent(new SocketEvent(SocketEvent.SocketEventType.MESSAGE_SEND, data));
    }
}
