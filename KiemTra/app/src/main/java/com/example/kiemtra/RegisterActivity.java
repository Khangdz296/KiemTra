package com.example.kiemtra;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kiemtra.ApiService;
import com.example.kiemtra.RetrofitClient;
import com.example.kiemtra.RegisterResponse;
import com.example.kiemtra.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword;
    ImageButton btnSubmit;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> handleRegister());
    }

    private void handleRegister() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = generateUsername(name);

        User user = new User(username, email, name, password);

        apiService.register(user).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterResponse res = response.body();
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(RegisterActivity.this, VerifyOtpActivity.class);
                i.putExtra("username", username);
                i.putExtra("otp_test", res.getTest_otp());
                startActivity(i);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Không kết nối được server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String generateUsername(String name) {
        return name.toLowerCase().replace(" ", "") + (int)(Math.random() * 9000 + 1000);
    }
}
