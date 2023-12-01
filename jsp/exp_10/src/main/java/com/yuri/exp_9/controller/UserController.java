package com.yuri.exp_9.controller;

import com.alibaba.fastjson2.JSON;
import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.entity.User;
import com.yuri.exp_9.service.BookService;
import com.yuri.exp_9.service.UserService;
import com.yuri.exp_9.utils.Tools;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        UserService userService = new UserService();
        User user = JSON.parseObject(Tools.getReqBody(request), User.class);
        if ("register".equals(action)) {
            if(userService.findByUserName(user.getUsername()) == null) {
                userService.insert(user);
                response.getWriter().write("注册成功");
            } else {
                response.getWriter().write("注册失败");
            }
        } else if ("login".equals(action)) {
            User user1 = userService.findByUserName(user.getUsername());
            if(user1 == null) {
                response.getWriter().write("请先去注册");
                return;
            }
            if (user1.getPassword().equals(user.getPassword())) {
                response.getWriter().write("登录成功");
            } else {
                response.getWriter().write("登录密码错误!!");
            }
        }  else if("check".equals(action)) {
            User user1 = userService.findByUserName(user.getUsername());
            if(user1 == null) {
                response.getWriter().write("账户不存在");
            } else {
                response.getWriter().write("ok");
            }
        }
    }
}
