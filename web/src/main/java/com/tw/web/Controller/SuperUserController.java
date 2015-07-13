package com.tw.web.controller;

import com.tw.core.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class SuperUserController {

    @Autowired
    // SessionFactory injection
    private SessionFactory sessionFactory;

    // Gets current Session object
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadHomePage(Model m) {
        return "index";
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<User> loadUsersPage() {

        List<User> userList = (List<User>)getSession().createQuery("FROM com.tw.core.User").list();
        return userList;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public User addUsers(@RequestBody User user) {

        getSession().save(user);
        return user;
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseBody
    public User editUsers(@RequestBody User user) {
        getSession().update(user);
        return user;
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    @ResponseBody
    public String deleteUsers(@RequestBody User user) {
        getSession().delete(getSession().get(User.class, user.getId()));
        return "Delete success.";
    }
}
