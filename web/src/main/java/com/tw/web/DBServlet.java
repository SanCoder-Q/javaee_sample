package com.tw.web;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

import javax.servlet.http.*;

public class DBServlet extends HttpServlet {

    String username, password, url, driver;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery("select * from users");
            out.print("[");
            while (rst.next()) {
                out.print("{");
                out.print("\"id\":\"" + rst.getString("id") + "\",");
                out.print("\"username\":\"" + rst.getString("name") + "\",");
                if (rst.getInt("gender") == 1)
                    out.print("\"gender\":\"男\",");
                else
                    out.print("\"gender\":\"女\",");
                out.print("\"email\":\"" + rst.getString("email") + "\",");
                out.print("\"age\":\"" + rst.getString("age") + "\"");
                out.print("},");
            }
            out.print("{}]");
            rst.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String op = req.getParameter("operation");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String id = req.getParameter("id");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "";
            if (op.equals("add")) {
                System.out.println(username);
                sql = "INSERT INTO users (name, gender, email, age) VALUES ('" + username + "', '" + gender + "', '" + email + "', '" + age + "')";
            } else if (op == "delete") {
            } else if (op == "edit") {
            } else {
            }

            if(sql.length() > 0) {
                stmt.execute(sql);
                
                ResultSet rst = stmt.executeQuery("select * from users where name='" + username + "'");

                rst.last();

                out.print("{");
                out.print("\"id\":\"" + rst.getString("id") + "\",");
                out.print("\"username\":\"" + rst.getString("name") + "\",");
                if (rst.getInt("gender") == 1)
                    out.print("\"gender\":\"男\",");
                else
                    out.print("\"gender\":\"女\",");
                out.print("\"email\":\"" + rst.getString("email") + "\",");
                out.print("\"age\":\"" + rst.getString("age") + "\"");
                out.print("}");
                rst.close();
            }
            stmt.close();
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }


    public void init() throws ServletException {
        username = "root";
        password = "";
        url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8";
        driver = "com.mysql.jdbc.Driver";
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;

    }
}

