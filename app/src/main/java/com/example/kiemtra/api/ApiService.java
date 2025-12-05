package com.example.kiemtra.api;

import com.example.kiemtra.LoginRequest;
import com.example.kiemtra.LoginResponse;
import com.example.kiemtra.RegisterResponse;
import com.example.kiemtra.VerifyOtpRequest;
import com.example.kiemtra.VerifyOtpResponse;
import com.example.kiemtra.model.Product;
import com.example.kiemtra.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import com.example.kiemtra.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/register")
    Call<RegisterResponse> register(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("api/verify-otp")
    Call<VerifyOtpResponse> verifyOtp(@Body VerifyOtpRequest req);
    @GET("api/categories/{id}/products")
    Call<List<Product>> getProductsByCategory(@Path("id") int categoryId);

    @GET("api/categories")
    Call<List<Category>> getAllCategories();


//    Gson gson = new GsonBuilder().setLenient().create();
//
//    ApiService api = new Retrofit.Builder()
//            .baseUrl("http://10.0.2.2:8080/api/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);


}
