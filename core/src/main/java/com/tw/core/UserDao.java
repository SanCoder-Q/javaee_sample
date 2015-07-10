package com.tw.core;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

    SessionFactory sf;

    public UserDao(SessionFactory sessionFactory) throws ClassNotFoundException, SQLException {
        sf = sessionFactory;
    }

    public void close() throws SQLException {
    }

    public User addUser(User user) throws ClassNotFoundException, SQLException {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User newUser = new User(user.getName(), user.getGender(), user.getEmail(), user.getAge());
        session.save(newUser);

        tx.commit();
        session.close();

        return newUser;
    }

    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        Session session = sf.openSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    public User editUser(User user) throws ClassNotFoundException, SQLException {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User newUser = session.get(User.class, user.getId());
        newUser.setName(user.getName());
        newUser.setGender(user.getGender());
        newUser.setEmail(user.getEmail());
        newUser.setAge(user.getAge());
        session.update(newUser);

        tx.commit();
        session.close();

        return newUser;
    }

    public void deleteUser(User user) throws ClassNotFoundException, SQLException {
        System.out.println("====" + user);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        User newUser = session.get(User.class, user.getId());
        if(newUser != null)
            session.delete(newUser);
        tx.commit();
        session.close();
    }
}
