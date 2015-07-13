package com.tw.core.dao;

import com.tw.core.model.User;

import java.util.List;

/**
 * Created by SanCoder on 7/13/15.
 */
public interface IUserDAO {

    List<User> getAllUser();

    User addUser(User user);

    User editUser(User user);

    boolean deleteUser(User user);
}
