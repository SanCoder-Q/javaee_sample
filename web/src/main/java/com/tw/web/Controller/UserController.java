package com.tw.web.controller;

import com.tw.core.DaoFactory;
import com.tw.core.User;
import com.tw.core.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by SanCoder on 7/11/15.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadHomePage(Model m) {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> loadUsersPage() {
        List<User> userList = null;

        try {
            UserDao userDao = DaoFactory.getDaoFactory().userDao();
            userList = userDao.getAllUsers();
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    @ResponseBody
    public User addUsers(@RequestBody User user) {

        try {
            UserDao userDao = DaoFactory.getDaoFactory().userDao();
            user = userDao.addUser(user);
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    @ResponseBody
    public User editUsers(@RequestBody User user) {

        try {
            UserDao userDao = DaoFactory.getDaoFactory().userDao();
            user = userDao.editUser(user);
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUsers(@RequestBody User user) {

        try {
            UserDao userDao = DaoFactory.getDaoFactory().userDao();
            userDao.deleteUser(user);
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Delete success.";
    }
}
