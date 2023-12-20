package com.yuri.school.controller;

import com.yuri.school.entity.User;
import com.yuri.school.result.R;
import com.yuri.school.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public R<Boolean> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("login")
    public R<Boolean> login(@RequestBody User user) {
        return userService.login(user);
    }
    @GetMapping("hello")
    String hello() {
        return "hello";
    }
}
