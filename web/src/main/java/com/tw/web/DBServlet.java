package com.tw.web;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import com.tw.core.*;

public class DBServlet extends HttpServlet {

    String username, password, url, driver;

    DaoFactory daoFactory;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        try {
            UserDao userDao = daoFactory.userDao();
            List<User> userList = userDao.getAllUsers();
            out.print(user2JSON(userList));
            out.close();
            userDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        int userId;
        try{
            userId = Integer.parseInt(req.getParameter("id"));
        }
        catch(Exception e){
            userId = -1;
        }

        User user = new User(userId,
                req.getParameter("username"),
                req.getParameter("gender"),
                req.getParameter("email"),
                Integer.parseInt(req.getParameter("age")));
        String op = req.getParameter("operation");

        try {
            UserDao userDao = daoFactory.userDao();

            if (op.equals("add")) {
                System.out.println("Add " + user.getName());

                User newUser = userDao.addUser(user);
                out.print(user2JSON(newUser));

                out.close();
                userDao.close();

            } else if (op.equals("delete")) {
                System.out.println("Delete " + user.getName());
                userDao.deleteUser(user);
                out.print("true");
                out.close();
                userDao.close();
            } else if (op.equals("edit")) {
                System.out.println("Edit " + user.getName());

                User newUser = userDao.editUser(user);
                out.print(user2JSON(newUser));

                out.close();
                userDao.close();

            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void init(){
        daoFactory = DaoFactory.getDaoFactory();
    }

    private String user2JSON(List<User> userList) {
        String json = "";
        json += ("[");
        for (User user : userList) {
            json += "{";
            json += "\"id\":\"" + user.getId() + "\",";
            json += "\"username\":\"" + user.getName() + "\",";
            json += "\"gender\":\"" + user.getGenderStr() + "\",";
            json += "\"email\":\"" + user.getEmail() + "\",";
            json += "\"age\":\"" + user.getAge() + "\"";
            json += "},";
        }
        if (json.length() > 2) {
            json = json.substring(0, json.length() - 1);
        }
        json += "]";
        return json;
    }

    private String user2JSON(User user) {
        String json = "";
        json += "{";
        json += "\"id\":\"" + user.getId() + "\",";
        json += "\"username\":\"" + user.getName() + "\",";
        json += "\"gender\":\"" + user.getGenderStr() + "\",";
        json += "\"email\":\"" + user.getEmail() + "\",";
        json += "\"age\":\"" + user.getAge() + "\"";
        json += "}";
        return json;
    }
}

