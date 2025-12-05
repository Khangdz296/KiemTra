//23162028 - cao dang huy
package com.example.kiemtra.model;

public class Product implements Comparable<Product> {
    private String id;
    private String name;
    private String imageUrl; // Đường dẫn ảnh
    private double price;    // Dùng để sắp xếp

    // Constructor, Getter, Setter
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public double getPrice() { return price; }

    // Implement so sánh để sắp xếp tăng dần theo giá
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
}

