//Họ tên: Dương Đình Ngọc Khang
//MSSV: 23162036
package peterpan.api.repository;

import peterpan.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Spring Data JPA tự sinh query theo tên hàm
    List<Product> findByCategoryIdOrderByPriceAsc(Long categoryId);
}
