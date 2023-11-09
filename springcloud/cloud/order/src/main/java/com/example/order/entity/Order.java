package com.example.order.entity;

import lombok.Data;

@Data
public class Order {
    private int id;
    private String user_id;
    private String name;
    private float price;
    private int num;
}
