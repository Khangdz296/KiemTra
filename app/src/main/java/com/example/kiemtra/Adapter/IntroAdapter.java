//23162022-TruongNguyenMinhHau

package com.example.kiemtra.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kiemtra.Model.IntroSlide;
import com.example.kiemtra.R;
import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroViewHolder> {

    private final List<IntroSlide> slideList;

    public IntroAdapter(List<IntroSlide> slideList) {
        this.slideList = slideList;
    }

    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_intro_slide, parent, false);
        return new IntroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
        holder.bind(slideList.get(position));
    }

    @Override
    public int getItemCount() {
        return slideList.size();
    }

    static class IntroViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView description;

        public IntroViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageIntro);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
        }

        public void bind(IntroSlide slide) {
            image.setImageResource(slide.getImage());
            title.setText(slide.getTitle());
            description.setText(slide.getDescription());
        }
    }
}
