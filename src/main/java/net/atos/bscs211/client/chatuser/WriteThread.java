package net.atos.bscs211.client.chatuser;

import net.atos.bscs211.client.main.Main;

import java.io.*;
import java.net.*;
import java.sql.SQLOutput;

public class WriteThread extends Thread{

    private Socket socket;
    private PrintWriter writer;

    public WriteThread(Socket socket) {
        this.socket = socket;
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch(IOException e) {
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        Console console = System.console();
        String text;

        do {
            text = console.readLine("[" + Main.$currentUser.getUsername() + "]: ");
            writer.println(text);
        } while(!text.equals("bye"));
        try {
            socket.close();
        } catch(IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        }
    }
}
