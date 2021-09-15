package net.atos.bscs211.client.chatuser;

import java.net.*;
import java.io.*;

public class ChatClient {
    private String hostname;
    private int port;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the Chat");

            new ReadThreat(socket, this).run();
            new WriteThread(socket, this).run();

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
