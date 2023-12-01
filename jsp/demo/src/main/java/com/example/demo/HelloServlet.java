package com.example.demo;

import java.io.*;
import java.util.Arrays;

import com.example.demo.util.CreateCode;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        try (ByteArrayOutputStream base = new ByteArrayOutputStream()) {
            javax.imageio.ImageIO.write(new CreateCode().createCode(request), "png", base);
            byte[] imageData = base.toByteArray();
            resp.setContentType("application/octet-stream");
            resp.getOutputStream().write(imageData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void destroy() {
    }
}