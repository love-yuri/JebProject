package com.yuri.exp_8.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class HomeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println(path);
        if(session.getAttribute("loginUser") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(path.contains("index.jsp") || path.contains("login.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
