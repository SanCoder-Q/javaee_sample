package com.tw.core.controller;

import com.tw.core.model.User;
import com.tw.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SanCoder on 7/11/15.
 */


@Transactional(readOnly = true)
@Repository
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadHomePage(Model m) {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<User> loadUsersPage() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public User addUsers(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseBody
    public User editUsers(@RequestBody User user) {
        return userService.editUser(user);
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseBody
    public String deleteUsers(@RequestBody User user) {
        return userService.deleteUser(user) ? "Delete success" : "Delete error";
    }
}
