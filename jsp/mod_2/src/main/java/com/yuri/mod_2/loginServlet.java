package com.yuri.mod_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> users = (Map<String, String>) req.getSession().getAttribute("users");
        if(users == null) {
            users = new HashMap<>();
        }
        if(users.containsKey(username) && users.get(username).equals(password)) {
            resp.sendRedirect("hello.jsp");
        } else if(users.get(username) == null) {
            resp.setContentType("text/html");
            resp.getWriter().println(" <p>用户不存在, 去 <a href=\"register.jsp\">注册</a></p>");
        } else {
            resp.setContentType("text/html");
            resp.getWriter().println("密码错误!");
        }
    }
}