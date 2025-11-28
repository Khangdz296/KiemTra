//23162028 - cao dang huy
package com.example.kiemtra;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// Import thêm thư viện này để tạo độ trễ giả
import android.os.Handler;
import android.os.Looper;
import java.util.Random;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<Product> mListProduct;
    private ProgressBar progressBar;

    // Variables for Lazy Loading
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private int PAGE_SIZE = 10; // Số lượng item mỗi lần tải
    private String categoryId = "beef"; // Lấy từ Intent thực tế

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        rcvProduct = findViewById(R.id.rcvProduct);
        progressBar = findViewById(R.id.progressBarLoadMore);

        mListProduct = new ArrayList<>();
        // Sử dụng GridLayoutManager với 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);

        productAdapter = new ProductAdapter(this, mListProduct);
        rcvProduct.setAdapter(productAdapter);

        // Xử lý sự kiện cuộn để Lazy Loading
        rcvProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                // Kiểm tra nếu cuộn xuống cuối và không đang tải
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadMoreItems();
                    }
                }
            }
        });

        // Load dữ liệu trang đầu tiên
        callApiGetProducts(currentPage);
    }

    private void loadMoreItems() {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        currentPage++;
        callApiGetProducts(currentPage);
    }

//    private void callApiGetProducts(int page) {
//        // Khởi tạo Retrofit (Nên tách ra class riêng Singleton pattern)
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://your-api-domain.com/api/") // Thay URL thật vào đây
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        apiService.getProductsByCategory(categoryId, page, PAGE_SIZE)
//                .enqueue(new Callback<List<Product>>() {
//                    @Override
//                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                        isLoading = false;
//                        progressBar.setVisibility(View.GONE);
//
//                        if (response.isSuccessful() && response.body() != null) {
//                            List<Product> newProducts = response.body();
//
//                            if (newProducts.size() > 0) {
//                                // SẮP XẾP TĂNG DẦN THEO GIÁ (Yêu cầu bài toán)
//                                Collections.sort(newProducts);
//
//                                // Thêm vào adapter
//                                productAdapter.addData(newProducts);
//                            } else {
//                                isLastPage = true; // Hết dữ liệu
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Product>> call, Throwable t) {
//                        isLoading = false;
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(ProductListActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
    private void callApiGetProducts(int page) {
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        // Giả lập độ trễ mạng 2 giây (2000ms) để bạn kịp nhìn thấy ProgressBar quay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // 1. Tạo danh sách giả
                List<Product> fakeList = new ArrayList<>();

                // Giả sử mỗi lần load 10 sản phẩm
                // Nếu trang > 5 thì giả vờ hết dữ liệu (để test logic dừng load)
                if (page <= 5) {
                    for (int i = 0; i < 10; i++) {
                        // Tạo giá ngẫu nhiên để test chức năng sắp xếp
                        double randomPrice = 50000 + (new Random().nextInt(50) * 10000);

                        // Tạo tên giả khác nhau theo trang
                        String name = "Món ăn ngon " + page + "-" + i;

                        // Link ảnh mẫu trên mạng (ảnh đồ ăn)
                        String imageUrl = "https://cdn.pixabay.com/photo/2022/06/07/20/52/curry-7249247_1280.jpg";
                        // Lưu ý: Bạn cần có Constructor trong class Product để dòng dưới chạy
                        // Product(id, name, price, imageUrl)
                        Product p = new Product();
                        p.setName(name);
                        p.setPrice(randomPrice);
                        p.setImageUrl(imageUrl);

                        fakeList.add(p);
                    }
                }

                // 2. Xử lý logic như khi có API về
                isLoading = false;
                progressBar.setVisibility(View.GONE);

                if (fakeList.size() > 0) {
                    // TEST SẮP XẾP: Code này sẽ xếp lại giá từ thấp -> cao
                    Collections.sort(fakeList);

                    // Thêm vào adapter
                    productAdapter.addData(fakeList);
                } else {
                    isLastPage = true; // Hết dữ liệu thật rồi
                    Toast.makeText(ProductListActivity.this, "Đã hết sản phẩm", Toast.LENGTH_SHORT).show();
                }

            }
        }, 2000); // 2000ms = 2 giây
    }
}