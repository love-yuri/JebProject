package com.yuri.mod_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class addServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        int sum = x + y;
        resp.setContentType("text/html");
        resp.getWriter().println("他们的和是 -> " + sum);
    }
}
