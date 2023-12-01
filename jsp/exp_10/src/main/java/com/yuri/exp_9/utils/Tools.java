package com.yuri.exp_9.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;

public class Tools {
    public static String getReqBody(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("UTF-8");
            BufferedReader reader = req.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
