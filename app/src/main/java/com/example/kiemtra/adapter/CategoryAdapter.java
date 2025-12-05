// Bui Viet Hoang 23162024
package com.example.kiemtra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kiemtra.R;
import com.example.kiemtra.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    private final Context context;
    private final List<Category> categoryList = new ArrayList<>();
    private final OnCategoryClickListener listener;
    public CategoryAdapter(Context context, OnCategoryClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    // Hàm để set data từ API
    public void setData(List<Category> list) {
        categoryList.clear();
        if (list != null) {
            categoryList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.txtCategoryName.setText(category.getName());

        // Load ảnh (nếu imageUrl trống thì set ảnh mặc định)
        if (category.getImage() != null && !category.getImage().isEmpty()) {
            Glide.with(context)
                    .load(category.getImage())
                    .placeholder(R.drawable.ic_launcher_background) // ảnh tạm
                    .into(holder.imgCategory);
        } else {
            holder.imgCategory.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategory;
        TextView txtCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
