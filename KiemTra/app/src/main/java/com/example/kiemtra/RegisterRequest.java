package com.example.kiemtra;

public class RegisterRequest {
    private Long id;
    private String name;
    private String email;
    private String password;

    public RegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter nếu cần
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
