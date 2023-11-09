package com.example.feign.clients;

import com.example.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("userservice")
public interface UserClientApi {
    @GetMapping("/users")
    public List<User> users();
}
