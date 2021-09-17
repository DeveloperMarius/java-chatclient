package net.atos.bscs211.server.main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.atos.bscs211.objects.SocketEvent;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;

//TODO: update with database implementation

public class UserThread extends Thread{

    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    //constructor
    //
    public UserThread(Socket socket, ChatServer server){
        System.out.println("New User Thread");
        this.socket = socket;
        this.server = server;
    }

    //run
    //
    //expands Thread.run(), checks for user input
    //
    public void run(){
        SocketEvent event = null;
        int user = -1;
        try {
            System.out.println("Run User Thread");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            do{
                String clientMessage = reader.readLine();
                event = SocketEvent.fromJson(clientMessage);

                if(event.getEventType() == SocketEvent.SocketEventType.CONNECT){
                    user = ((Double)event.getData().get("user")).intValue();
                }
                try {
                    event.progressServer(server, this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }while(event.getEventType() != SocketEvent.SocketEventType.DISCONNECT);

            socket.close();

        } catch (IOException e){
            System.out.println("Error in UserThread" + e.getMessage());
            if(e instanceof SocketException) {
                System.out.println("Socket Connection Error");
            }else{
                e.printStackTrace();
            }

            if(user != -1) {
                System.out.println("Removing User");
                HashMap<String, Object> data = new HashMap<>();
                data.put("user", (double) user);
                SocketEvent event2 = new SocketEvent(SocketEvent.SocketEventType.DISCONNECT, data);
                try {
                    event2.progressServer(server, this);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                System.out.println("Closing Socket for User");
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void sendMessage(String message){
        writer.println(message);
    }

}
