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
        String k = "";
        for(String key : req.getParameterMap().keySet()) {
            k = key;
            break;
        }
        if(!k.contains("edit")) {
            System.out.println(bookService.delete(Integer.parseInt(k)));
            resp.sendRedirect("index.jsp");
        } else {
            String id = k.substring(k.indexOf("?") + 1);
            req.getSession().setAttribute("editBook", bookService.findById(Integer.parseInt(id)));
            resp.sendRedirect("editBook.jsp");
        }
    }
}
