package com.example.week4_viewflipper_bt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ImagesViewPager2Adapter extends RecyclerView.Adapter<ImagesViewPager2Adapter.ImagesViewHolder> {

    private List<Images> imagesList;

    public ImagesViewPager2Adapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_images.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        // Lấy dữ liệu ảnh tại vị trí position
        Images images = imagesList.get(position);

        if (images == null) {
            return;
        }

        // Gán ảnh vào ImageView
        // Lưu ý: Dùng hàm getImagesId() cho khớp với Model bạn tạo ở Bước 3
        holder.imageView.setImageResource(images.getImagesId());
    }

    @Override
    public int getItemCount() {
        if (imagesList != null) {
            return imagesList.size();
        }
        return 0;
    }

    // --- Phần này trong slide bị che khuất, nhưng bắt buộc phải có ---
    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ ID từ file item_images.xml
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}