package com.tw.core.dao;

import com.tw.core.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SanCoder on 7/13/15.
 */
@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    // SessionFactory injection
    private SessionFactory sessionFactory;

    // Gets current Session object
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>)getSession().createQuery("FROM com.tw.core.model.User").list();
    }

    @Override
    public User addUser(User user) {
        getSession().save(user);
        return user;
    }

    @Override
    public User editUser(User user) {
        getSession().update(user);
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        getSession().delete(getSession().get(User.class, user.getId()));
        return true;
    }
}
