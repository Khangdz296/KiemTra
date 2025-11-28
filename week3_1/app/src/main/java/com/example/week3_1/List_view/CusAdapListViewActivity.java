package com.example.week3_1.List_view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.week3_1.R;


public class CusAdapListViewActivity extends AppCompatActivity {
    // Khai báo
    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonhocAdapter adapter;
    EditText editText1;
    Button btnNhap;
    Button btnCapNhap;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.listview_activity);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);

        AnhXa();

        adapter = new MonhocAdapter(
                CusAdapListViewActivity.this,
                R.layout.row_monhoc,
                arrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CusAdapListViewActivity.this, "Click vào vị trí: " + i, Toast.LENGTH_SHORT).show();
                editText1.setText(arrayList.get(i).getName());
                vitri = i;
            }
        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();

                if (!name.isEmpty()) {
                    MonHoc monHocMoi = new MonHoc(name, "Chưa có mô tả", R.drawable.project_add);
                    arrayList.add(monHocMoi);
                    adapter.notifyDataSetChanged();
                    editText1.setText("");
                }
            }
        });
        btnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vitri != -1) {
                    String name = editText1.getText().toString();
                    if (name.isEmpty()) {
                        arrayList.remove(vitri);
                        vitri = -1;
                    } else {
                        MonHoc monHocCapNhat = new MonHoc(name, "Chưa có mô tả", R.drawable.project_add);
                        arrayList.set(vitri, monHocCapNhat);
                    }
                    adapter.notifyDataSetChanged();
                    editText1.setText("");
                }
            }
        });
    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listview1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhap = (Button) findViewById(R.id.btnCapNhap);

        // Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java", "Lập trình Java", R.drawable.java));
        arrayList.add(new MonHoc("C#", "Lập trình C#", R.drawable.c));
        arrayList.add(new MonHoc("PHP", "Lập trình PHP", R.drawable.php));
        arrayList.add(new MonHoc("Kotlin", "Lập trình Kotlin", R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart", "Lập trình Dart", R.drawable.dart));
    }

}