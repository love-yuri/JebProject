package com.yuri.exp_9;

import java.io.*;
import java.util.List;

import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.service.BookService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = new BookService().getBooks();
        System.out.println(books);
        request.getSession().setAttribute("allBook", books);
    }

    public void destroy() {
    }
}