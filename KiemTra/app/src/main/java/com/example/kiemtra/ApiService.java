package com.example.kiemtra;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/register")
    Call<RegisterResponse> register(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("api/verify-otp")
    Call<VerifyOtpResponse> verifyOtp(@Body VerifyOtpRequest req);

}
