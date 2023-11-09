package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.example.demo.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        User user = JSON.parseObject(requestBody.toString(), User.class);
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) req.getSession().getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            req.getSession().setAttribute("users", users);
        }
        JSONObject out = new JSONObject();
        String msg = login(users, user);
        out.put("isLogin", msg.equals("登陆成功!"));
        out.put("msg", msg);
        resp.getWriter().write(out.toJSONString());
    }

    String login(@NotNull List<User> users, User user) {
        for(User u: users) {
            if(u.getUsername().equals(user.getUsername())) {
                if(!u.getPassword().equals(user.getPassword())) {
                    return "密码错误!";
                }
                return "登陆成功!";
            }
        }
        return "用户名不存在！";
    }
}
