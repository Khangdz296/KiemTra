//23162028 - cao dang huy
//package com.example.kiemtra;

//public class Product implements Comparable<Product> {
//    private String id;
//    private String name;
//    private String imageUrl; // Đường dẫn ảnh
//    private double price;    // Dùng để sắp xếp
//
//    // Constructor, Getter, Setter
//    public String getName() { return name; }
//    public String getImageUrl() { return imageUrl; }
//    public double getPrice() { return price; }
//
//    // Implement so sánh để sắp xếp tăng dần theo giá
//    @Override
//    public int compareTo(Product other) {
//        return Double.compare(this.price, other.price);
//    }
//}

package com.example.kiemtra;

public class Product implements Comparable<Product> {
    private String id;
    private String name;
    private String imageUrl;
    private double price;

    // 1. Constructor rỗng (Bắt buộc phải có để dùng new Product())
    public Product() {
    }

    // 2. CÁC HÀM SETTER (Thêm phần này để hết lỗi đỏ)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    // 4. Logic sắp xếp giá tăng dần
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
}