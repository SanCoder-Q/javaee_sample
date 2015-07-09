package com.tw.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SanCoder on 7/9/15.
 */
public class UserDao {
    private IConnectionMaker connMaker;
    private Connection conn;

    public UserDao(IConnectionMaker connMaker) throws ClassNotFoundException, SQLException {
        this.connMaker = connMaker;
        conn = connMaker.makeConnection();
    }

    public void close() throws SQLException{
        conn.close();
    }

    public User addUser(User user) throws ClassNotFoundException, SQLException{
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO users (name, gender, email, age) VALUES ('" + user.getName() + "', '" + user.getGender() + "', '" + user.getEmail() + "', '" + user.getAge() + "')";
        stmt.execute(sql);
        sql = "select * from users where name='" + user.getName() + "'";
        ResultSet rts = stmt.executeQuery(sql);
        rts.last();
        User newUser = new User(rts.getInt("id"), rts.getString("name"), rts.getInt("gender"),rts.getString("email"), rts.getInt("age"));
        rts.close();
        stmt.close();
        return newUser;
    }

    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        Statement stmt = conn.createStatement();
        String sql = "select * from users";
        ResultSet rts = stmt.executeQuery(sql);
        List<User> userList = new ArrayList<User>();
        while (rts.next()) {
            userList.add(new User(rts.getInt("id"), rts.getString("name"), rts.getInt("gender"),rts.getString("email"), rts.getInt("age")));
        }
        return userList;
    }

    public User editUser(User user) throws ClassNotFoundException, SQLException{
        Statement stmt = conn.createStatement();
        String sql = "UPDATE users SET name='" + user.getName() + "', gender='" + user.getGender() + "', email='" + user.getEmail() + "', age='" + user.getAge() + "' WHERE id='" + user.getId() + "'";
        stmt.execute(sql);
        return user;
    }

    public void deleteUser(User user) throws ClassNotFoundException, SQLException {
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM users WHERE id='" + user.getId()
                + "'";
        stmt.execute(sql);
    }
}
