package com.yuri.exp_5;

import com.yuri.exp_5.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>)req.getSession().getAttribute("allBook");
        if(books == null) {
            System.out.println("is null");
            books = new ArrayList<>();
        }
        Book book = new Book();
        try {
            BeanUtils.populate(book, req.getParameterMap());
            books.add(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        req.getSession().setAttribute("allBook", books);
        resp.sendRedirect("index.jsp");
    }
}
