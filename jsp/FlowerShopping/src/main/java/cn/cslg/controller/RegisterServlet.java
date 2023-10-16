package cn.cslg.controller;

import cn.cslg.db.UserDB;
import cn.cslg.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        Writer writer = response.getWriter();
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>)session.getAttribute("AllUser");
        if(users == null) {
            users = UserDB.userList; // 所有用户
        }
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());

            for(User u : users) {
                if(user.getUname().equals(u.getUname())) {
                    writer.write("<html><body><h1>用户名重复</h1></body></html>");
                    return;
                }
            }
            users.add(user);
            System.out.println(user.toString());
            session.setAttribute("AllUser", users);
            writer.write("<html><body><h1>注册成功</h1></body></html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
