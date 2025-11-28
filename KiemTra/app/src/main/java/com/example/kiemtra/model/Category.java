// Bui Viet Hoang 23162024
package com.example.kiemtra.model;

public class Category {

    private int id;
    private String name;
    private String imageUrl;

    public Category() {
    }

    public Category(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.imageUrl = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return imageUrl;
    }
}
