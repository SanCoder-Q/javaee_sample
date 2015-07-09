package com.tw.core;

import java.sql.SQLException;

/**
 * Created by SanCoder on 7/9/15.
 */
public class DaoFactory {

    public UserDao userDao() throws SQLException, ClassNotFoundException{
        return new UserDao(getConnectionMaker());
    }

    public IConnectionMaker getConnectionMaker(){
        return new MySQLConnectionMaker();
    }
}
