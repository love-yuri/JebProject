package com.yuri.mod_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> users = (Map<String, String>) req.getSession().getAttribute("users");
        if(users == null) {
            users = new HashMap<>();
        }
        if(users.containsKey(username)) {
            resp.setContentType("text/html");
            resp.getWriter().println("该用户已存在");
        } else {
            users.put(username, password);
            req.getSession().setAttribute("currentUser", username);
            req.getSession().setAttribute("users", users);
            resp.setContentType("text/html");
            resp.getWriter().println("注册成功");
        }
    }
}