package com.tw.core.service;

import com.tw.core.dao.IUserDAO;
import com.tw.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SanCoder on 7/13/15.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User editUser(User user) {
        return userDAO.editUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
