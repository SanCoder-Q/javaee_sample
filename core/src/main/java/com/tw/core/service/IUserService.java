package com.tw.core.service;

import com.tw.core.model.User;

import java.util.List;

/**
 * Created by SanCoder on 7/13/15.
 */
public interface IUserService {
    List<User> getAllUser();

    User addUser(User user);

    User editUser(User user);

    boolean deleteUser(User user);
}
