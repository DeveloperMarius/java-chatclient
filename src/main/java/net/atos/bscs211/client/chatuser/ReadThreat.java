package net.atos.bscs211.client.chatuser;

import com.google.gson.Gson;
import net.atos.bscs211.client.main.Main;
import net.atos.bscs211.objects.SocketEvent;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class ReadThreat extends Thread {

    private BufferedReader reader;
    private Socket socket;

    public ReadThreat(Socket socket) {
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch(IOException e) {
            System.out.println("Error getting input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();

                SocketEvent event = SocketEvent.fromJson(response);
                try {
                    event.progressClient();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
