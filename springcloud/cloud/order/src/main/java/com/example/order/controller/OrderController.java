package com.example.order.controller;

import com.example.feign.clients.UserClientApi;
import com.example.feign.pojo.User;
import com.example.order.clients.UserClient;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RefreshScope
public class OrderController {
    private OrderMapper orderMapper;
    private final UserClientApi userClientApi;

    @Value("${pattern.dateFormat}")
    private String dateFormat;
    private final RestTemplate restTemplate;
//    private final UserClient userClient;

    public OrderController(OrderMapper orderMapper, UserClientApi userClientApi, RestTemplate restTemplate) {
        this.orderMapper = orderMapper;
        this.userClientApi = userClientApi;
        this.restTemplate = restTemplate;
//        this.userClient = userClient;
    }

    @GetMapping("{id}")
    public String test(@PathVariable("id") int id) {
        return "order test" + id;
    }

    @GetMapping("/orders")
    public List<Order> orders() {
        return orderMapper.orders();
    }

    @GetMapping("/now")
    public String now() {
        System.out.println(dateFormat);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }

    @GetMapping("/users")
    public List<User> users() {
        try {
            ResponseEntity<List<User>> response = restTemplate.exchange("http://userservice/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
            });
            return response.getBody();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    @GetMapping("/users_2")
//    public List<User> users_2() {
//        return userClient.users();
//    }

    @GetMapping("/users_3")
    public List<User> users_3() {
        return userClientApi.users();
    }
}
