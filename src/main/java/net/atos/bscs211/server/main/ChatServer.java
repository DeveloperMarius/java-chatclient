package net.atos.bscs211.server.main;


import java.io.*;
import java.net.*;
import java.util.*;


//TODO: update with database implementation

public class ChatServer {
    private int port;

    //saved usernames
    private Set<String> userNames = new HashSet<>(); //necessary with database?
    //saved user threads
    private Set<UserThread> userThreads = new HashSet<>();

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
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
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

        if(args.length<1){
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
    void broadcast(String message, UserThread excludeUser){ //excluded user = self
        for(UserThread aUser : userThreads){
            if (aUser !=excludeUser){
                aUser.sendMessage(message);
            }
        }
    }

    //addUserName
    //
    //saves usernames on the server
    //
    public void addUserName(String userName){
        userNames.add(userName);
    }

    //removeUser
    //
    //removes a user from the server, deletes the thread
    //
    void removeUser(String userName, UserThread aUser){
        boolean removed = userNames.remove(userName);
        if(removed){
            userThreads.remove(aUser);
            System.out.println("User" + userName + "has disconnected");
        }
    }

    //getUserNames
    //
    //returns all saved usernames
    //
    public Set<String> getUserNames() {
        return this.userNames;
    }

    //hasUsers
    //
    //checks if any users are connected
    //
    public boolean hasUsers(){
        return !this.userNames.isEmpty();
    }
}
