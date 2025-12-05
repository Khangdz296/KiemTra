package com.example.kiemtra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra.adapter.CategoryAdapter;
import com.example.kiemtra.api.ApiClient;
import com.example.kiemtra.api.ApiService;
import com.example.kiemtra.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvGreeting, tvSubtitle, tvCatTitle;
    private ImageView imgAvatar;
    private RecyclerView rcvCategory;
    private FloatingActionButton fabCart;
    private LinearLayout homeBtn, profileBtn, supportBtn, settingBtn;
    private ProgressBar progressBar;

    private CategoryAdapter adapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        showUserInfo();
        initRecyclerView();
        setupListeners();
    }

    private void initUI() {
        tvGreeting = findViewById(R.id.tvGreeting);
        tvSubtitle = findViewById(R.id.tvSubtitle);
        imgAvatar = findViewById(R.id.imgAvatar);
        rcvCategory = findViewById(R.id.rcvCategory);
        fabCart = findViewById(R.id.fabCart);

        homeBtn = findViewById(R.id.homeBtn);
        profileBtn = findViewById(R.id.profileBtn);
        supportBtn = findViewById(R.id.supportBtn);
        settingBtn = findViewById(R.id.settingBtn);

        progressBar = findViewById(R.id.progressBar);
        tvCatTitle = findViewById(R.id.tvCatTitle);
    }

    // HIỂN THỊ THÔNG TIN USER
    private void showUserInfo() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        if (userName != null && !userName.isEmpty()) {
            tvGreeting.setText("Hi! " + userName);
        } else {
            tvGreeting.setText("Hi! TechZone Member");
        }

        // tvSubtitle.setText("Chọn hãng để xem sản phẩm");
    }
    //23162024
    private void initRecyclerView() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCategory.setLayoutManager(layoutManager);

        adapterCategory = new CategoryAdapter(
                this,
                category -> {
                    // DEBUG: xem id truyền sang là bao nhiêu
                    Toast.makeText(
                            MainActivity.this,
                            "Chọn category: " + category.getName() + " (id = " + category.getId() + ")",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
                    intent.putExtra("category_id", category.getId());   // dùng getId() trong Category
                    startActivity(intent);
                }
        );

        rcvCategory.setAdapter(adapterCategory);


        tvCatTitle.setText("Danh mục sản phẩm");
        // Gọi API lấy category
        loadCategoriesFromApi();
    }

    private void loadCategoriesFromApi() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        ApiService apiService = ApiClient.getApiService();
        Call<List<Category>> call = apiService.getAllCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }

                if (response.isSuccessful() && response.body() != null) {
                    List<Category> list = response.body();

                    if (list.isEmpty()) {
                        Toast.makeText(MainActivity.this,
                                "API khong có category", Toast.LENGTH_SHORT).show();
                    }

                    adapterCategory.setData(list);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Lỗi dữ liệu category từ API. Code: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                Toast.makeText(MainActivity.this,
                        "Gọi API Category thất bại: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupListeners() {
        fabCart.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Giỏ hàng trống", Toast.LENGTH_SHORT).show()
        );

        homeBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Đang ở trang chủ", Toast.LENGTH_SHORT).show()
        );

        profileBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Trang cá nhân (chưa làm)", Toast.LENGTH_SHORT).show()
        );

        supportBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Hỗ trợ khách hàng", Toast.LENGTH_SHORT).show()
        );

        settingBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Cài đặt ứng dụng", Toast.LENGTH_SHORT).show()
        );

        imgAvatar.setOnClickListener(v ->
                Toast.makeText(MainActivity.this,
                        "Click Avatar", Toast.LENGTH_SHORT).show()
        );
    }
}
