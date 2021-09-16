package net.atos.bscs211.client.main;

import net.atos.bscs211.client.chatuser.ChatClient;
import net.atos.bscs211.objects.User;
import net.atos.bscs211.utils.DatabaseManager;

public class Main {

    public static User currentUser = null;
    public static int group = 1;

    public static void main(String[] args){
        //Do everything
        net.atos.bscs211.client.main.windows.Main.main(args);
        ChatClient client = new ChatClient("", 1234);
        client.execute();
        DatabaseManager.closeConnection();
    }
}
