package com.example.firstapp;

public class Book {
    private int id;
    private String title;
    private double price;
    private int imageResource;
    private String description;

    public Book(int id, String title, double price, int imageResource, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageResource = imageResource;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}


