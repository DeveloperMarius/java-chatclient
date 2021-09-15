package net.atos.bscs211.objects;

import net.atos.bscs211.utils.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Group {

    protected int id;
    protected String title;

    public Group(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Message> getMessages() throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `messages` WHERE `group` = ?");
        statement.setInt(1, getId());
        ResultSet result = statement.executeQuery();
        ArrayList<Message> messages = new ArrayList<>();
        while(result.next()){
            messages.add(new Message(result.getInt("id"), result.getInt("user"), result.getInt("group"), result.getString("content"), result.getLong("sent")));
        }
        return messages;
    }

    public static Group getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `groups` WHERE `id` = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new Group(result.getInt("id"), result.getString("title"));
        }
        throw new SQLException("User not found");
    }

    public static ArrayList<Group> getGroups() throws SQLException {
        Statement statement = DatabaseManager.getConnection().createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM `messages`");
        ArrayList<Group> groups = new ArrayList<>();
        while(result.next()){
            groups.add(new Group(result.getInt("id"), result.getString("title")));
        }
        return groups;
    }
}
