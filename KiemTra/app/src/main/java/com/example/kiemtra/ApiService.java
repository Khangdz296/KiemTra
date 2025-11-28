//23162028 - cao dang huy

package com.example.kiemtra;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface ApiService {
    @GET("/api/categories/{id}/products")
    Call<List<Product>> getProductsByCategory(@Path("id") int id);
}