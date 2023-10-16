package com.yuri.exp_5.entity;
public class Book {
    private String ISBN;
    private String name;
    private String price;

    @Override
    public String toString() {
        return "User{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
