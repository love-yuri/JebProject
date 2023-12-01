package com.yuri.exp_9;

import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteBook")
public class delBookServlet extends HelloServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookService();
        int index = -1;
        for(String key : req.getParameterMap().keySet()) {
            index = Integer.parseInt(key);
            break;
        }
        if(index != -1) {
            System.out.println(bookService.delete(index));
        }
        resp.sendRedirect("index.jsp");
    }
}
