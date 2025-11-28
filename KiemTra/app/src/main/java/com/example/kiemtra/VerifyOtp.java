package com.example.kiemtra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp extends Activity {

    private EditText otp1, otp2, otp3, otp4, otp5, otp6;
    private ImageButton btnVerifyOTP;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_verifyotp);

        // Lấy email từ RegisterActivity
        email = getIntent().getStringExtra("email");

        // Ánh xạ EditText
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);

        btnVerifyOTP = findViewById(R.id.btnVerifyOTP);

        setupOTPInputs();

        btnVerifyOTP.setOnClickListener(v -> {
            String otp = otp1.getText().toString() + otp2.getText().toString() +
                    otp3.getText().toString() + otp4.getText().toString() +
                    otp5.getText().toString() + otp6.getText().toString();

            if (otp.length() < 6) {
                Toast.makeText(this, "Nhập đầy đủ 6 số OTP", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gọi API xác thực OTP với model
            OtpRequest request = new OtpRequest(email, otp);
            ApiService.api.verifyOtp(request).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(VerifyOtp.this, "Xác thực thành công!", Toast.LENGTH_SHORT).show();
                        // Chuyển về LoginActivity hoặc màn hình chính
                        startActivity(new Intent(VerifyOtp.this, VerifyOtp.class));
                        finish();
                    } else {
                        Toast.makeText(VerifyOtp.this, "OTP không đúng, thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(VerifyOtp.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // Chuyển focus sang ô tiếp theo khi nhập OTP
    private void setupOTPInputs() {
        EditText[] otps = {otp1, otp2, otp3, otp4, otp5, otp6};
        for (int i = 0; i < otps.length; i++) {
            final int index = i;
            otps[i].addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && index < otps.length - 1) {
                        otps[index + 1].requestFocus();
                    }
                }
            });
        }
    }
}
