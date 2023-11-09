package com.example.user.controller;

import com.example.user.config.PatternProperties;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PatternProperties patternProperties;

    @GetMapping("/yuri")
    public String test() {
        return "yuri is yes!";
    }

    @GetMapping("/users")
    List<User> users() {
        return userMapper.users();
    }

    @GetMapping("/now")
    public String now() {
        System.out.println(patternProperties.getDateFormat());
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateFormat()));
    }
}
