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
        this.socket = socket;
        this.server = server;
    }

    //run
    //
    //expands Thread.run(), checks for user input
    //
    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            SocketEvent event = null;
            do{
                String clientMessage = reader.readLine();
                event = SocketEvent.fromJson(clientMessage);

                try {
                    event.progressServer(server, this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }while(event.getEventType() != SocketEvent.SocketEventType.DISCONNECT);

            socket.close();

        } catch (IOException e){
            System.out.println("Error in UserThread" + e.getMessage());
            e.printStackTrace();
        }

    }


    public void sendMessage(String message){
        writer.println(message);
    }

}
