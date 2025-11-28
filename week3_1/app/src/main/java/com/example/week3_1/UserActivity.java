package com.example.week3_1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private RecyclerView rvMultipleViewType;
    private List<Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        rvMultipleViewType = findViewById(R.id.multiply);

        mData = new ArrayList<>();

        mData.add(new UserModel("Vo Gia Huan", "TP. Thủ Đức"));
        mData.add(R.drawable.c);
        mData.add("Text 0");
        mData.add("Text 1");

        mData.add(new UserModel("Huỳnh Thiên Hạo", "Quan 3"));
        mData.add(R.drawable.java);
        mData.add(R.drawable.php);
        mData.add("Text 2");

        mData.add(new UserModel("Cao Đăng Huy", "Quan 10"));
        mData.add("Text 3");
        mData.add("Text 4");

        mData.add(new UserModel("Trương Nguyễn Minh Hậu", "Quan 1"));
        mData.add(R.drawable.dart);

        CustomAdapter adapter = new CustomAdapter(this, mData);
        rvMultipleViewType.setAdapter(adapter);

        rvMultipleViewType.setLayoutManager(new LinearLayoutManager(this));
    }
}