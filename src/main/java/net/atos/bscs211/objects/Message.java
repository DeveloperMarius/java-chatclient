package net.atos.bscs211.objects;

import net.atos.bscs211.utils.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Message {

    protected int id;
    protected int user;
    protected User user_object = null;
    protected int group;
    protected Group group_object = null;
    protected String content;
    protected long sent;

    public Message(int id, int user, int group, String content, long sent) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.content = content;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public User getUserObject() throws SQLException {
        if(this.user_object == null){
            this.user_object = User.getById(this.getUser());
        }
        return user_object;
    }

    public int getGroup() {
        return group;
    }

    public Group getGroupObject() throws SQLException {
        if(this.group_object == null){
            this.group_object = Group.getById(this.getGroup());
        }
        return group_object;
    }

    public String getContent() {
        return content;
    }

    public long getSent() {
        return sent;
    }

    public static Message getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("SELECT * FROM `messages` WHERE `id` = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new Message(result.getInt("id"), result.getInt("user"), result.getInt("group"), result.getString("content"), result.getLong("sent"));
        }
        throw new SQLException("User not found");
    }

    public static Message create(User user, Group group, String content) throws SQLException {
        return create(user.getId(), group.getId(), content);
    }

    public static Message create(int user, int group, String content) throws SQLException {
        PreparedStatement statement = DatabaseManager.getConnection().prepareStatement("INSERT INTO `messages` VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, user);
        statement.setInt(2, group);
        statement.setString(3, content);
        long sent = System.currentTimeMillis();
        statement.setLong(4, sent);

        if(statement.executeUpdate() > 0){
            int insert_id = -1;
            ResultSet result = statement.getGeneratedKeys();
            if(result.next())
                insert_id = result.getInt(1);

            return new Message(insert_id, user, group, content, sent);
        }else
            throw new SQLException("Failed to insert message");
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", group=" + group +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                '}';
    }
}
