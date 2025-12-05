package com.example.kiemtra;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("session_key")
    private String sessionKey;

    @SerializedName("user_id")
    private int userId;

    public String getStatus() { return status; }

    public String getSessionKey() { return sessionKey; }

    public int getUserId() { return userId; }
}