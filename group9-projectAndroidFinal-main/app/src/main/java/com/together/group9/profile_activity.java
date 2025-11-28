package com.together.group9;


import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

public class profile_activity {
    FrameLayout avatarBorder = findViewById(R.id.avatarBorder);
    TextView txtEnergy = findViewById(R.id.txtEnergy);

    String energy = "vui";

switch(energy.toLowerCase())

    {
        case "vui":
            avatarBorder.getBackground().setTint(Color.parseColor("#00FFAA"));
            txtEnergy.setText("Mức năng lượng: Vui");
            break;

        case "buồn":
            avatarBorder.getBackground().setTint(Color.parseColor("#888888"));
            txtEnergy.setText("Mức năng lượng: Buồn");
            break;

        case "tức giận":
            avatarBorder.getBackground().setTint(Color.RED);
            txtEnergy.setText("Mức năng lượng: Tức giận");
            break;

        default:
            avatarBorder.getBackground().setTint(Color.WHITE);
            break;
    }

    RecyclerView recyclerPosts = findViewById(R.id.recyclerPosts);
recyclerPosts.setLayoutManager(new

    GridLayoutManager(this,3));

    List<Integer> posts = new ArrayList<>();
posts.add(R.drawable.p1);
posts.add(R.drawable.p2);
posts.add(R.drawable.p3);
// ... thêm ảnh bài đăng tùy bạn

    PostAdapter adapter = new PostAdapter(posts);
recyclerPosts.setAdapter(adapter);


}
