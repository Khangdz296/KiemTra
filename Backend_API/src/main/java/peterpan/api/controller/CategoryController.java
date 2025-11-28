//Họ tên: Dương Đình Ngọc Khang
//MSSV: 23162036
package peterpan.api.controller;

import peterpan.api.model.Category;
import peterpan.api.model.Product;
import peterpan.api.repository.CategoryRepository;
import peterpan.api.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")  // nếu bạn gọi từ FE khác domain
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryController(CategoryRepository categoryRepository,
                              ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // 1) API lấy tất cả category
    // GET /api/categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    // 2) API lấy product theo category
    // GET /api/categories/{id}/products
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable Long categoryId) {

        // Optional: check category có tồn tại không
        if (!categoryRepository.existsById(categoryId)) {
            return ResponseEntity.notFound().build();
        }

        List<Product> products = productRepository.findByCategoryIdOrderByPriceAsc(categoryId);
        return ResponseEntity.ok(products);
    }
}
