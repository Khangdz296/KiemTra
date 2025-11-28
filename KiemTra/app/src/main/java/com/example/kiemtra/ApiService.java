//23162028 - cao dang huy
package com.example.kiemtra;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // API lấy sản phẩm theo Category
    // Giả sử có tham số page để làm lazy loading
    @GET("products/category")
    Call<List<Product>> getProductsByCategory(
            @Query("categoryId") String categoryId,
            @Query("page") int page,
            @Query("limit") int limit
    );
}