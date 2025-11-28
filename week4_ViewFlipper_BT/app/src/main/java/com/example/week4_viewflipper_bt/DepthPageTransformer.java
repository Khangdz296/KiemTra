package com.example.week4_viewflipper_bt;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class DepthPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(@NonNull View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // Trang nằm tít bên trái màn hình
            view.setAlpha(0f);

        } else if (position <= 0) { // [-1,0]
            // Dùng hiệu ứng mặc định khi trượt sang trái
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setTranslationZ(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);

        } else if (position <= 1) { // (0,1]
            // Hiệu ứng làm mờ trang đi
            view.setAlpha(1 - position);

            // Chống lại hiệu ứng trượt mặc định
            view.setTranslationX(pageWidth * -position);
            // Đẩy trang xuống dưới (về độ sâu Z)
            view.setTranslationZ(-1f);

            // Thu nhỏ trang xuống một chút
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // Trang nằm tít bên phải màn hình
            view.setAlpha(0f);
        }
    }
}