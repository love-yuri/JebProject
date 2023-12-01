package com.yuri.exp_9.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yuri.exp_9.entity.Book;
import com.yuri.exp_9.entity.User;
import com.yuri.exp_9.entity.UserBook;
import com.yuri.exp_9.service.BookService;
import com.yuri.exp_9.service.UserBookService;
import com.yuri.exp_9.service.UserService;
import com.yuri.exp_9.utils.Tools;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/book")
public class UserBookController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        UserService userService = new UserService();
        UserBookService userBookService = new UserBookService();
        BookService bookService = new BookService();
        JSONObject obj = JSON.parseObject(Tools.getReqBody(request));
        User user = userService.findByUserName(obj.getString("username"));
        if ("add".equals(action)) {
            UserBook userBook = new UserBook();
            userBook.setUser_id(user.getId());
            userBook.setBook_id(obj.getInteger("bookId"));
            userBookService.insert(userBook);
            Book book = bookService.findById(obj.getInteger("bookId"));
            book.setStatus(1);
            bookService.update(book);
            response.getWriter().write("订阅成功");
        } else if ("list".equals(action)) {
            List<UserBook> userBooks = userBookService.findByUserName(user.getId());
            List<Book> books = new ArrayList<>();
            userBooks.forEach(book -> {
                books.add(bookService.findById(book.getBook_id()));
            });
            response.getWriter().write(JSON.toJSONString(books));
        } else if ("user".equals(action)) {
            Book book = bookService.findById(obj.getInteger("bookId"));
            UserBook userBook = userBookService.findByBookId(book.getId());
            System.out.println(userBook.toString());
            userBookService.deleteById(userBook.getId());
            book.setStatus(0);
            bookService.update(book);
            response.getWriter().write("取消订阅成功");
        }
    }
}
