package com.example.newsapp;

public class News {
    private String title;
    private int imageResId; // now int instead of String URL
    private String description;

    public News(String title, int imageResId, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
