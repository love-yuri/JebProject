package com.example.feign.pojo;

import lombok.Data;

@Data
public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private int id;
    private String username;
    private String address;
}
