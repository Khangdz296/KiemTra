package com.example.kiemtra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvGreeting, tvSubtitle;
    private ImageView imgAvatar;
    private RecyclerView rcvCategory;
    private FloatingActionButton fabCart;
    private LinearLayout homeBtn, profileBtn, supportBtn, settingBtn;


    private RecyclerView.Adapter adapterCategory;

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
    }

    // --- PHẦN 1: HIỂN THỊ THÔNG TIN USER ---
    private void showUserInfo() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");

        if (userName != null && !userName.isEmpty()) {
            tvGreeting.setText("Hi! " + userName);
        } else {
            tvGreeting.setText("Hi! TechZone Member");
        }
    }

    // --- PHẦN 2: CẤU HÌNH RECYCLERVIEW (CATEGORY) ---
    private void initRecyclerView() {
//        ArrayList<CategoryDomain> items = new ArrayList<>();
//        items.add(new CategoryDomain("iPhone", "cat_iphone"));
//        items.add(new CategoryDomain("Samsung", "cat_samsung"));
//        items.add(new CategoryDomain("Xiaomi", "cat_xiaomi"));
//        items.add(new CategoryDomain("Oppo", "cat_oppo"));
//        items.add(new CategoryDomain("Phụ kiện", "cat_phukien"));
//
//        rcvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        adapterCategory = new CategoryAdapter(items);
//        rcvCategory.setAdapter(adapterCategory);
    }

    // --- PHẦN 3: SỰ KIỆN CLICK ---
    private void setupListeners() {
        fabCart.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show()
        );

        homeBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Đang ở trang chủ", Toast.LENGTH_SHORT).show()
        );

        profileBtn.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Vào trang cá nhân", Toast.LENGTH_SHORT).show()
        );

        imgAvatar.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Click Avatar", Toast.LENGTH_SHORT).show()
        );
    }
}