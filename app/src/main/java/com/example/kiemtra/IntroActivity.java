//23162022-TruongNguyenMinhHau
package com.example.kiemtra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kiemtra.Adapter.IntroAdapter;
import com.example.kiemtra.Model.IntroSlide;

import me.relex.circleindicator.CircleIndicator3;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnStart;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable autoSlideRunnable;
    private int slideCount;

    private static final int AUTO_SLIDE_DELAY = 4000;
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPagerIntro);
        btnStart = findViewById(R.id.btnStart);
        CircleIndicator3 indicator = findViewById(R.id.circle_indicator);

        List<IntroSlide> slides = prepareIntroSlides();
        slideCount = slides.size();

        IntroAdapter adapter = new IntroAdapter(slides);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        startAutoSliding();
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                btnStart.setVisibility(position == slideCount - 1 ? View.VISIBLE : View.GONE);
                if (position == slideCount - 1) {
                    handler.removeCallbacks(autoSlideRunnable);
                }
            }
        });

        btnStart.setOnClickListener(v -> navigateNext());
    }
    private List<IntroSlide> prepareIntroSlides() {
        List<IntroSlide> slides = new ArrayList<>();
        slides.add(new IntroSlide(R.drawable.intro1, "Chào Mừng Đến Với Ứng Dụng", "Khám phá đầy đủ các tính năng độc đáo."));
        slides.add(new IntroSlide(R.drawable.intro2, "Trải Nghiệm Dễ Dàng", "Thiết kế trực quan, thao tác nhanh chóng."));
        slides.add(new IntroSlide(R.drawable.intro3, "Sẵn Sàng Khởi Đầu", "Đăng nhập hoặc đăng ký để bắt đầu!"));
        return slides;
    }
    private void startAutoSliding() {
        autoSlideRunnable = new Runnable() {
            @Override
            public void run() {
                int nextItem = viewPager.getCurrentItem() + 1;
                if (nextItem < slideCount) {
                    viewPager.setCurrentItem(nextItem, true);
                    handler.postDelayed(this, AUTO_SLIDE_DELAY);
                }
            }
        };
        handler.postDelayed(autoSlideRunnable, AUTO_SLIDE_DELAY);
    }
    private void navigateNext() {
        if (isLoggedIn()) {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
        }
        finish();
    }
    private boolean isLoggedIn() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(autoSlideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager.getCurrentItem() < slideCount - 1) {
            handler.postDelayed(autoSlideRunnable, AUTO_SLIDE_DELAY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(autoSlideRunnable);
    }
}
