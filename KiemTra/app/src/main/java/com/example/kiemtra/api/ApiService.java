// Bui Viet Hoang 23162024
package com.example.kiemtra.api;

import com.example.kiemtragk.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("api/categories")
    Call<List<Category>> getAllCategories();
}
