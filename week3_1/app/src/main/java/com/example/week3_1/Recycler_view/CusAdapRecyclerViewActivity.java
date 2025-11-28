package com.example.week3_1.Recycler_view;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.example.week3_1.R;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class CusAdapRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rvSongs;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recyclerview_activity);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recyclerview_activity);
        rvSongs = findViewById(R.id.rv_songs);

        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("001", "Song1", "Lyric1", "Artist1"));
        mSongs.add(new SongModel("002", "Song2", "Lyric2", "Artist2"));
        mSongs.add(new SongModel("003", "Song3", "Lyric3", "Artist3"));
        mSongs.add(new SongModel("004", "Song4", "Lyric4", "Artist4"));
        mSongs.add(new SongModel("005", "Song5", "Lyric5", "Artist5"));
        mSongs.add(new SongModel("006", "Song6", "Lyric6", "Artist6"));
        mSongs.add(new SongModel("007", "Song7", "Lyric7", "Artist7"));
        mSongs.add(new SongModel("008", "Song8", "Lyric8", "Artist8"));
        mSongs.add(new SongModel("009", "Song9", "Lyric9", "Artist9"));
        mSongs.add(new SongModel("010", "Song10", "Lyric10", "Artist10"));
        mSongs.add(new SongModel("011", "Song11", "Lyric11", "Artist11"));
        mSongs.add(new SongModel("012", "Song12", "Lyric12", "Artist12"));


        mSongAdapter = new SongAdapter(this, mSongs);
        rvSongs.setAdapter(mSongAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);
    }
}