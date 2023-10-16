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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
            session.setAttribute("AllUser", users);
            System.out.println("内容为空!");
        }
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            for(User u : users) {
                if(user.getUname().equals(u.getUname())) {
                    if(user.getUpwd().equals(u.getUpwd())) {
                        response.sendRedirect("FlowerServlet");
                        return;
                    } else {
                        writer.write("<html><body><h1>密码错误</h1></body></html>");
                        return;
                    }
                }
            }
            writer.write("<html><body><h1>没找到用户，请注册!!</h1></body></html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
