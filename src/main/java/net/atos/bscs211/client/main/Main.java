package net.atos.bscs211.client.main;

import net.atos.bscs211.objects.User;
import net.atos.bscs211.utils.DatabaseManager;

public class Main {

    public static User $currentUser = null;

    public static void main(String[] args){
        //Do everything
        DatabaseManager.closeConnection();
    }
}
