package net.atos.bscs211.client.chatuser;

import net.atos.bscs211.client.main.Main;

import java.io.*;
import java.net.*;

public class ReadThreat extends Thread {

    private BufferedReader reader;
    private Socket socket;

    public ReadThreat(Socket socket) {
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {
            System.out.println("Error getting input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                if (Main.$currentUser.getUsername() != null) {
                    System.out.println("[" + Main.$currentUser.getUsername() + "]");
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
