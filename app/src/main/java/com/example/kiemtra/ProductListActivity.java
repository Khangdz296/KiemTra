package com.example.kiemtra;
//23162028 - Cao Đăng Huy

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra.adapter.ProductAdapter;
import com.example.kiemtra.api.ApiClient;
import com.example.kiemtra.api.ApiService;
import com.example.kiemtra.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<Product> mListProduct;
    private ProgressBar progressBar;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;

    private final int PAGE_SIZE = 2;

    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // LẤY category_id TỪ INTENT – DÙNG -1 LÀM GIÁ TRỊ LỖI
        categoryId = getIntent().getIntExtra("category_id", -1);

        if (categoryId == -1) {
            Toast.makeText(this,
                    "Không nhận được category_id từ Intent",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Toast.makeText(this,
                "Đang load product cho category id = " + categoryId,
                Toast.LENGTH_SHORT).show();

        rcvProduct = findViewById(R.id.rcvProduct);
        progressBar = findViewById(R.id.progressBarLoadMore);

        mListProduct = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);

        productAdapter = new ProductAdapter(this, mListProduct);
        rcvProduct.setAdapter(productAdapter);

        rcvProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        currentPage++;
                        callApiGetProducts(currentPage);
                    }
                }
            }
        });

        // Gọi API lần đầu
        callApiGetProducts(currentPage);
    }

    private void callApiGetProducts(int page) {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getApiService();
        apiService.getProductsByCategory(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Product> fullListFromServer = response.body();

                    // Debug số sản phẩm server trả về
                    Toast.makeText(ProductListActivity.this,
                            "Server trả về " + fullListFromServer.size() + " sản phẩm",
                            Toast.LENGTH_SHORT).show();

                    int start = (page - 1) * PAGE_SIZE;
                    int end = Math.min(start + PAGE_SIZE, fullListFromServer.size());

                    if (start >= fullListFromServer.size()) {
                        isLastPage = true;
                        return;
                    }

                    List<Product> productsForThisPage = fullListFromServer.subList(start, end);
                    productAdapter.addData(productsForThisPage);

                    if (end >= fullListFromServer.size()) {
                        isLastPage = true;
                    }
                } else {
                    Toast.makeText(ProductListActivity.this,
                            "API lỗi, code: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProductListActivity.this,
                        "Lỗi: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
