package net.atos.bscs211.objects;

import net.atos.bscs211.utils.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    protected int id;
    protected String username;
    protected String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static boolean login(String username, String password){
        try {
            User user = getByUsername(username);
            return user.getPassword().equals(password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getByUsername(String username) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `users` WHERE `username` = ?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new User(result.getInt("id"), result.getString("username"), result.getString("password"));
        }
        throw new SQLException("User not found");
    }

    public static User getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `users` WHERE `id` = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new User(result.getInt("id"), result.getString("username"), result.getString("password"));
        }
        throw new SQLException("User not found");
    }

    public static User create(String username, String password) throws SQLException {
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
    }
}
