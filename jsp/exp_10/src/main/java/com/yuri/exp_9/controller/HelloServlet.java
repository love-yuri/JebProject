package com.yuri.exp_9.controller;

import java.io.*;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.service.BookService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import netscape.javascript.JSObject;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = new BookService().getBooks();
        response.getWriter().write(JSON.toJSONString(books));
    }

    public void destroy() {
    }
}