package com.tw.core;

import javax.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SanCoder on 7/9/15.
 */
public interface IConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
