package com.tw.web.controller;

import com.tw.core.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SanCoder on 7/11/15.
 */
@Controller
public class TestController {

    @RequestMapping(value = "test", method= RequestMethod.GET)
    @ResponseBody
    public List<User> loadHomePage(Model m) {
//        m.addAttribute("name", "Hello Spring");
//        return "index";
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("name1", 1, "name1@TW.com", 29));
        return users;
    }

}
