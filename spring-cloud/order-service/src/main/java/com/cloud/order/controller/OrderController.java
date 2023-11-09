package com.cloud.order.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        String msg = restTemplate.getForObject("http://user-service/hello", String.class);
        return "hello world " + msg;
    }
}
