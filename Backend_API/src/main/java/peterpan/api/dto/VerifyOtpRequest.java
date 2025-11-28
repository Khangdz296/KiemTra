package peterpan.api.dto;

import lombok.Data; 
//23162021- Huynh Thien Hao code phan nay
@Data
public class VerifyOtpRequest {
    private String username;
    private String otpCode;
}