package com.epam.web.enitity;

import java.util.List;

public class Book implements Identifiable {

    private long id;

    private String title;
    private String description;
    private String imagePath;
    private int quantity;

    public Book(long id, String title, String description, String imagePath, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
