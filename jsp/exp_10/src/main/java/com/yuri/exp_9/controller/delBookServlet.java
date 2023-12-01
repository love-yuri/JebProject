package com.yuri.exp_9.controller;

import com.alibaba.fastjson2.JSON;
import com.yuri.exp_9.service.BookService;
import com.yuri.exp_9.utils.Tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteBook")
public class delBookServlet extends HelloServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        String json = JSON.parseObject(Tools.getReqBody(req)).getString("bookId");
        bookService.delete(Integer.parseInt(json));
        resp.getWriter().write("true");
    }
}
