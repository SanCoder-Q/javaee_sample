package com.tw.web;

import java.io.*;
import java.sql.*;
import javax.servlet.*;

import javax.servlet.http.*;

public class DBServlet extends HttpServlet{

    String username, password, url, driver;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("gb2312");
        resp.setContentType("text/json;charset=gb2312");
        PrintWriter out = resp.getWriter();


        try
        {
            Connection con = getConnection();
            Statement stmt=con.createStatement();
            ResultSet rst=stmt.executeQuery("select * from users");
            out.print("[");
            while(rst.next())
            {
                out.print("{");
                out.print("\"id\":\"" + rst.getString("id") + "\",");
                out.print("\"username\":\"" + rst.getString("name") + "\",");
                if(rst.getInt("gender") == 1)
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

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void init()throws ServletException
    {
        username="root";
        password="";
        url="jdbc:mysql://127.0.0.1:3306/test";
        driver = "com.mysql.jdbc.Driver";
    }

    private Connection getConnection()
    {
        Connection con=null;
        try
        {
            Class.forName(driver);
            con=DriverManager.getConnection(url,username,password);

        }
        catch(Exception e )
        {
            e.printStackTrace();
        }

        return con;

    }
}

