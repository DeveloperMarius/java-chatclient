package net.atos.bscs211.client.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection != null)
            return connection;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mariadb://185.245.99.56:3306/java-chatclient", "java-chatclient", "zX3u8^8bg1t11Kq_");
        } catch (Exception se) {
            se.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
