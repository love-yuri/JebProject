package com.yuri.exp_9.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String ISBN;
    private String name;
    private Double price;
    private Integer status;
}
