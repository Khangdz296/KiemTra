package com.together.group9;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class postadapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    List<Integer> postList; // lưu drawable ID

    public PostAdapter(List<Integer> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.img.setImageResource(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgPost);
        }
    }
}
