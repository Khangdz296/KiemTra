package com.example.kiemtra.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    private String password;
    @SerializedName("id")
    private Long id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("fullName") // Mapping với JSON key "fullName"
    private String fullName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("session_key") // Mapping với JSON key "session_key" (snake_case)
    private String sessionKey;

    @SerializedName("join_date") // Mapping với JSON key "join_date" (snake_case)
    private String joinDate;

    @SerializedName("isActive")
    private Boolean isActive;

    @SerializedName("otpCode")
    private String otpCode;

    // --- CONSTRUCTOR ---
    public User() {
    }

    public User(String username, String email, String fullName, String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    // --- GETTER & SETTER ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}