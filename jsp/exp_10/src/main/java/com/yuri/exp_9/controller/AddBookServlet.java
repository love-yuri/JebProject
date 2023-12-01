package com.yuri.exp_9.controller;

import com.alibaba.fastjson2.JSON;
import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.service.BookService;
import com.yuri.exp_9.utils.Tools;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        try {
            Book book = JSON.parseObject(Tools.getReqBody(req), Book.class);
            book.setStatus(0);
            bookService.insert(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
