package cn.cslg.controller;

import cn.cslg.db.FlowerDB;
import cn.cslg.model.Flower;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/FlowerServlet")
public class FlowerServlet extends HttpServlet {
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //读取数据库中的鲜花数据返回给客户端显示
        List<Flower> flowerList = FlowerDB.flowerList;

        //把数据存储到request对象中
        request.setAttribute("flowerList", flowerList);
        System.out.println(request.getAttribute("flowerList"));
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
