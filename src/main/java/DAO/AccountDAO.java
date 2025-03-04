package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account login(Account user) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?;");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                System.out.println(account);
                return account;   
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account register(Account user) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO account (username, password) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int newId = (int) rs.getLong("account_id");
                return new Account(newId, user.getUsername(), user.getPassword());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userExists(int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM account WHERE account_id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("!!!");
                return true;
            }
            System.out.println("!!!!");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExists() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usernameExists'");
    }    
}
