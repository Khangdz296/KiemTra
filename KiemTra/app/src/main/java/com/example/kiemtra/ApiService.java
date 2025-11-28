package com.example.kiemtra;

import com.example.kiemtra.RegisterRequest;
import com.example.kiemtra.OtpRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    ApiService api = RetrofitClient.getInstance().create(ApiService.class);

    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest request);

    @POST("verify-otp")
    Call<ResponseBody> verifyOtp(@Body OtpRequest request);
}
