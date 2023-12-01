package com.yuri.exp_8;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("login".equals(action)){
            Map<String, String[]> maps = request.getParameterMap();
            User user=new User();
            try {
                BeanUtils.populate(user,maps);
                //进行数据库校验，用户名和密码是否正确
                request.getSession().setAttribute("loginUser",user);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }else if("logout".equals(action)){//退出登录
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }

    }
}
