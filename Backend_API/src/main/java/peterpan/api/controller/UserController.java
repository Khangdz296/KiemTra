package peterpan.api.controller;

import peterpan.api.dto.VerifyOtpRequest;
import peterpan.api.model.User;
import peterpan.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//23162021- Huynh Thien Hao code phan nay
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi mã hóa mật khẩu", e);
        }
    }
    
    private String generateRandomOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Username hoặc Email đã tồn tại."),
                HttpStatus.BAD_REQUEST);
        }

        user.setPassword(hashPassword(user.getPassword()));
        user.setSessionKey(UUID.randomUUID().toString());
        
        String newOtp = generateRandomOtp(); 
        user.setOtpCode(newOtp);
        user.setIsActive(false); 
        
        User savedUser = userRepository.save(user);

        // Giả định hàm gửi email tồn tại
        // sendOtpEmail(savedUser.getEmail(), newOtp); // check database de xem otp

        return new ResponseEntity<>(
            Map.of("status", "success", "message", "Đăng ký thành công! OTP đã được tạo.", "user_id", savedUser.getId(), "test_otp", newOtp), 
            HttpStatus.CREATED); // 201
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginDetails) {
        String username = loginDetails.get("username");
        String rawPassword = loginDetails.get("password");
        String hashedPassword = hashPassword(rawPassword);

        Optional<User> userOpt = userRepository.findByUsernameAndPassword(username, hashedPassword);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            
            if (user.getIsActive() == false) {
                 return new ResponseEntity<>(
                    Map.of("status", "error", "message", "Tài khoản chưa được kích hoạt OTP."),
                    HttpStatus.FORBIDDEN);
            }

            String newSessionKey = UUID.randomUUID().toString();
            user.setSessionKey(newSessionKey);
            userRepository.save(user); 

            return new ResponseEntity<>(
                Map.of("status", "success", "message", "Đăng nhập thành công!", "user_id", user.getId(), "session_key", newSessionKey), 
                HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Sai tên đăng nhập hoặc mật khẩu."),
                HttpStatus.UNAUTHORIZED); 
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader(name = "X-Session-Key") String sessionKey) {
        if (sessionKey == null || sessionKey.isEmpty()) {
             return new ResponseEntity<>(
                Map.of("status", "error", "message", "Thiếu Session Key. Vui lòng đăng nhập trước."),
                HttpStatus.FORBIDDEN); // 403
        }
        
        Optional<User> userOpt = userRepository.findBySessionKey(sessionKey);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Map<String, Object> userData = new HashMap<>();
            userData.put("user_id", user.getId());
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());
            userData.put("full_name", user.getFullName());
            userData.put("join_date", user.getJoinDate());
            userData.put("is_active", user.getIsActive());

            return new ResponseEntity<>(
                Map.of("status", "success", "user", userData), 
                HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Session Key không hợp lệ hoặc đã hết hạn."),
                HttpStatus.FORBIDDEN); 
        }
    }
    
    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOtp(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        
        if (username == null || username.isEmpty()) {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Vui lòng cung cấp Username."),
                HttpStatus.BAD_REQUEST);
        }
        
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Người dùng không tồn tại."),
                HttpStatus.NOT_FOUND);
        }
        
        User user = userOpt.get();
        
        if (user.getIsActive()) {
            return new ResponseEntity<>(
                Map.of("status", "success", "message", "Tài khoản đã được kích hoạt."),
                HttpStatus.OK);
        }
        
        String newOtp = generateRandomOtp(); 
        user.setOtpCode(newOtp); 
        userRepository.save(user);
        
        // sendOtpEmail(user.getEmail(), newOtp); // GIẢ ĐỊNH
        
        return new ResponseEntity<>(
            Map.of("status", "success", "message", "Mã OTP mới đã được gửi đến email.", "test_otp", newOtp),
            HttpStatus.OK);
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
        String username = request.getUsername();
        String otpCode = request.getOtpCode();

        if (username == null || otpCode == null) {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Thiếu username/email hoặc mã OTP."),
                HttpStatus.BAD_REQUEST); // 400
        }

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Người dùng không tồn tại."),
                HttpStatus.NOT_FOUND); 
        }

        User user = userOpt.get();

        if (otpCode.equals(user.getOtpCode())) {
            
            user.setIsActive(true); 
            user.setOtpCode(null); 
            userRepository.save(user);

            return new ResponseEntity<>(
                Map.of("status", "success", "message", "Xác thực OTP thành công! Tài khoản đã được kích hoạt."),
                HttpStatus.OK); 
                
        } else {
            return new ResponseEntity<>(
                Map.of("status", "error", "message", "Mã OTP không đúng. Vui lòng thử lại."),
                HttpStatus.UNAUTHORIZED);
        }
    }
}