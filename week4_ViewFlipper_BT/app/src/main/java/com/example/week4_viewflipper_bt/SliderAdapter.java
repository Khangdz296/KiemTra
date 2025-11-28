package com.example.week4_viewflipper_bt;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {

    private Context context;
    private ArrayList<Integer> arrayList; // Danh sách ID ảnh (R.drawable...)

    public SliderAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        // Inflate layout vừa tạo ở trên
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_slider_shoppe, parent, false);
        return new SliderHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        // Dùng Glide để load ảnh từ ID resource vào ImageView
        Glide.with(context)
                .load(arrayList.get(position))
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    // Class con ViewHolder
    public class SliderHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageView;

        public SliderHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            // Ánh xạ đúng ID trong file XML
            imageView = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }
}