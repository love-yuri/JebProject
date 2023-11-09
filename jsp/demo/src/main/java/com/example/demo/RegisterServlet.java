package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.example.demo.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
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
        String msg = register(users, user, req.getSession().getAttribute("VerificationCode").toString());
        out.put("isRegister", msg.equals("注册成功!"));
        out.put("msg", msg);
        if (msg.equals("注册成功!")) {
            System.out.println("注册成功捏");
            users.add(user);
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(3600); // 设置过期时间（单位：秒）

            // 将Cookie添加到HTTP响应中
            resp.addCookie(cookie);
            req.getSession().setAttribute("users", users);

        }
        resp.getWriter().write(out.toJSONString());
        System.out.println(req.getSession().getAttribute("users"));
    }

    String register(@NotNull List<User> users, User user, String code) {
        if(!user.getCode().equals(code)) {
            return "验证码错误!";
        }
        for(User u: users) {
            if(u.getUsername().equals(user.getUsername())) {
                return "用户名已存在!";
            }
        }
        return "注册成功!";
    }
}
