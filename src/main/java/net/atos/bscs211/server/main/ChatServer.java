package net.atos.bscs211.server.main;


import java.io.*;
import java.net.*;
import java.util.*;


//TODO: update with database implementation

public class ChatServer {

    private int port;

    //saved usernames
    private HashMap<Integer, UserThread> users = new HashMap<>();

    //constructor
    //
    public ChatServer(int port){
        this.port = port;
    }

    /*execute
    //
    //waits for a new user and accepts the new connection
    */
    public void execute(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Start of execute function");

            while(true){
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                newUser.start();
            }
        } catch (IOException e){
            System.out.println("Server Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    //main function
    //
    //initialises the server, starts execute function
    //
    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.execute();
    }


    //broadcast function
    //
    //sends a message to all users except the origin
    //
    public void broadcast(String message, int excludeUser){ //excluded user = self
        for(Map.Entry<Integer, UserThread> entry : users.entrySet()){
            if (entry.getKey() != excludeUser){
                entry.getValue().sendMessage(message);
            }
        }
    }

    public void addUser(int user, UserThread thread){
        this.users.put(user, thread);
    }

    //removeUser
    //
    //removes a user from the server, deletes the thread
    //
    public void removeUser(int user){
        this.users.remove(user);
    }

    //getUserNames
    //
    //returns all saved usernames
    //
    public HashMap<Integer, UserThread> getUsers() {
        return this.users;
    }

    //hasUsers
    //
    //checks if any users are connected
    //
    public boolean hasUsers(){
        return !this.users.isEmpty();
    }
}
