package com.yuri.exp_6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getParam")
public class GetParamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("inputParam", "表单提交的参数为: " + req.getParameter("inputParam"));
        req.setAttribute("userAgent", "浏览器信息: " + req.getHeader("User-Agent"));
        req.getRequestDispatcher("implicit.jsp").forward(req, resp);
    }
}
