package net.atos.bscs211.client.main;

import net.atos.bscs211.client.chatuser.ChatClient;
import net.atos.bscs211.client.main.windows.Chat;
import net.atos.bscs211.objects.User;
import net.atos.bscs211.utils.DatabaseManager;

import java.util.ArrayList;

public class Main {

    public static User currentUser = null;
    public static int group = 1;
    public static ChatClient client;
    public static Chat chat;
    public static ArrayList<User> userlist;
    public static void main(String[] args){
        //Do everything
        net.atos.bscs211.client.main.windows.Main.main(args);
    }
}
