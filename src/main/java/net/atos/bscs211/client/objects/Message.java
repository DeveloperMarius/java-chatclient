package net.atos.bscs211.client.objects;

import net.atos.bscs211.client.utils.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Message {

    protected int id;
    protected int user;
    protected User user_object = null;
    protected String content;
    protected int sent;

    public Message(int id, int user, String content, int sent) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public User getUserObject() {
        if(this.user_object == null){
            try {
                this.user_object = User.getById(this.getUser());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user_object;
    }

    public String getContent() {
        return content;
    }

    public int getSent() {
        return sent;
    }

    public static Message getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `messages` WHERE `id` = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new Message(result.getInt("id"), result.getInt("user"), result.getString("content"), result.getInt("sent"));
        }
        throw new SQLException("User not found");
    }
/*
    public static User create(User user, String content) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("INSERT INTO `users` VALUES (NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, username);
        statement.setString(2, password);

        if(statement.executeUpdate() > 0){
            int insert_id = -1;
            ResultSet result = statement.getGeneratedKeys();
            if(result.next())
                insert_id = result.getInt(1);

            return new User(insert_id, username, password);
        }else
            throw new SQLException("Failed to insert user");
    }*/
}
