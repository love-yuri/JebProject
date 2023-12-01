package com.yuri.exp_8.listener;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class OnlineUserCounter implements HttpSessionListener {
    private static int onlineUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUsers++;
        System.out.println("Session Created. Online users: " + onlineUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (onlineUsers > 0) {
            onlineUsers--;
        }
        System.out.println("Session Destroyed. Online users: " + onlineUsers);
    }

    public static int getOnlineUsers() {
        return onlineUsers;
    }
}

