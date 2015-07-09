package com.tw.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SanCoder on 7/9/15.
 */
public class MySQLConnectionMaker implements IConnectionMaker {

    String username = "root";
    String password = "";
    String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8";
    String driver = "com.mysql.jdbc.Driver";

    public Connection makeConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        return  DriverManager.getConnection(url, username, password);
    }
}
