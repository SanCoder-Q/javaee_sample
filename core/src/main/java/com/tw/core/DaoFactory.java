package com.tw.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SanCoder on 7/9/15.
 */
public class DaoFactory {

    private static DaoFactory factory;
    private static SessionFactory sf;

    public static DaoFactory getDaoFactory() {
        if (factory == null)
            factory = new DaoFactory();
        return factory;
    }

    private DaoFactory() {
        if (sf == null)
            sf = new Configuration().configure().buildSessionFactory();
    }

    public static UserDao userDao() throws SQLException, ClassNotFoundException {
        return new UserDao(sf);
    }
}
