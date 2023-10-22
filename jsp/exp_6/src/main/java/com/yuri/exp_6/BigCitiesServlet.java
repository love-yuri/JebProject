package com.yuri.exp_6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bigCities")
public class BigCitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> capitals = new HashMap<String, String>();
        capitals.put("俄罗斯", "莫斯科");
        capitals.put("日本", "东京");
        capitals.put("中国", "北京");
        Map<String, String[]> bigcities = new HashMap<String, String[]>();
        bigcities.put("澳大利亚", new String[]{"悉尼", "墨尔本", "布里斯班"});
        bigcities.put("美国", new String[]{"纽约", "洛杉矶", "加利福尼亚"});
        bigcities.put("中国", new String[]{"北京", "上海", "广州"});
        req.setAttribute("capitals", capitals);
        req.setAttribute("bigcities", bigcities);
        req.getRequestDispatcher("bigCities.jsp").forward(req, resp);
    }
}
