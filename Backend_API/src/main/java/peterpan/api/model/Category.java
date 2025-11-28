package peterpan.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
//Họ tên: Dương Đình Ngọc Khang
//MSSV: 23162036
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String imageUrl; 
    // Nếu muốn load products theo category thì giữ mối quan hệ này, còn không có thể bỏ.
    @OneToMany(mappedBy = "category")
    @JsonIgnore 
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // getter & setter
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) { this.products = products; }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
