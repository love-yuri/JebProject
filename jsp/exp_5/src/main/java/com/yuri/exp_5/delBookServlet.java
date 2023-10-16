package com.yuri.exp_5;

import com.yuri.exp_5.entity.Book;
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
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>)req.getSession().getAttribute("allBook");
        if(books == null) {
            System.out.println("is null");
            books = new ArrayList<>();
        }
        int index = -1;
        for(String key : req.getParameterMap().keySet()) {
            index = Integer.parseInt(key);
            break;
        }
        if(index != -1) {
            books.remove(index);
        }
        req.getSession().setAttribute("allBook", books);
        resp.sendRedirect("index.jsp");
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
