package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message createMessage(Message message) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int newId = (int) rs.getLong("message_id");
                return new Message(newId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteMessage(int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM message WHERE message_id = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getAllMessagesFromUser(int id) {
        Connection con = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM message WHERE posted_by = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMessages() {
        Connection con = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM message;");
            ResultSet rs = ps. executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message getMessage(int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM message WHERE message_id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps. executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateMessage(int id, Message message) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE message SET message_text = ? WHERE message_id = ?;");
            ps.setString(1, message.getMessage_text());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
