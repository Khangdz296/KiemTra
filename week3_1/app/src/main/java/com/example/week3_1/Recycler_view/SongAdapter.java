package com.example.week3_1.Recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.week3_1.R;
import android.widget.Toast;


import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context mContext;
    private List<SongModel> mSongs;

    private LayoutInflater mLayoutInflater;

    public SongAdapter(Context mContext, List<SongModel> mSongs) {
        this.mContext = mContext;
        this.mSongs = mSongs;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }
    class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode;
        private TextView tvTitle;
        private TextView tvArtist;
        private TextView tvLyric;

        public SongViewHolder(View items) {
            super(items);
            tvCode = items.findViewById(R.id.tv_code);
            tvTitle = items.findViewById(R.id.tv_title);
            tvArtist = items.findViewById(R.id.tv_artist);
            tvLyric = items.findViewById(R.id.tv_lyric);

            items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        SongModel song = mSongs.get(position);
                        Toast.makeText(mContext, song.getmTitle(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }


    }
    @Override
    @NonNull
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_item_song, parent, false);
        return new SongViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongModel song = mSongs.get(position);
        holder.tvCode.setText(song.getmCode());
        holder.tvTitle.setText(song.getmTitle());
        holder.tvArtist.setText(song.getmArtist());
        holder.tvLyric.setText(song.getmLyric());
    }
    @Override
    public int getItemCount() {
        return mSongs.size();
    }

}
