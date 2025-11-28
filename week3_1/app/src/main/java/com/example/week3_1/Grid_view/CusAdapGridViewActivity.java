package com.example.week3_1.Grid_view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.week3_1.List_view.MonHoc;
import com.example.week3_1.R;



public class CusAdapGridViewActivity extends AppCompatActivity {

    GridView gridView;
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
        setContentView(R.layout.gridview_activity);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_activity);

        AnhXa();

        adapter = new MonhocAdapter(
                CusAdapGridViewActivity.this,
                R.layout.row_monhoc2,
                arrayList);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                MonHoc selectedMonHoc = arrayList.get(i);
                Toast.makeText(CusAdapGridViewActivity.this,
                        "Bạn chọn: " + selectedMonHoc.getName(),
                        Toast.LENGTH_SHORT).show();
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
        gridView = findViewById(R.id.gridview1);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhap = (Button) findViewById(R.id.btnCapNhap);

        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java", "Lập trình Java cơ bản", R.drawable.java));
        arrayList.add(new MonHoc("C#", "Lập trình C# .NET", R.drawable.c));
        arrayList.add(new MonHoc("PHP", "Lập trình Web PHP", R.drawable.php));
        arrayList.add(new MonHoc("Kotlin", "Lập trình Android Kotlin", R.drawable.kotlin));
        arrayList.add(new MonHoc("Dart", "Lập trình Flutter", R.drawable.dart));
        arrayList.add(new MonHoc("Swift", "Lập trình iOS", R.drawable.swift));
    }
}