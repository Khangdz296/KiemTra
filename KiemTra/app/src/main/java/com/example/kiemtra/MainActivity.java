// Bui Viet Hoang 23162024
package com.example.kiemtra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kiemtragk.adapter.CategoryAdapter;
import com.example.kiemtragk.api.ApiClient;
import com.example.kiemtragk.api.ApiService;
import com.example.kiemtragk.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvCategory = findViewById(R.id.rcvCategory);
        progressBar = findViewById(R.id.progressBar);

        categoryAdapter = new CategoryAdapter(this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(categoryAdapter);

        //  GỌI API LẤY TẤT CẢ CATEGORIES
        loadCategoriesFromApi();
    }


    // HÀM GỌI API
    private void loadCategoriesFromApi() {
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);

        //Gọi API
        ApiService apiService = ApiClient.getApiService();
        Call<List<Category>> call = apiService.getAllCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (progressBar != null) progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    categoryAdapter.setData(response.body());
                } else {
                    Toast.makeText(MainActivity.this,
                            "Lỗi dữ liệu category từ API",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                if (progressBar != null) progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,
                        "Gọi API thất bại: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
