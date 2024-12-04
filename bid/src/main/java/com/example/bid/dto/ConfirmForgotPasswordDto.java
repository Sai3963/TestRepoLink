package com.example.bid.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmForgotPasswordDto {
    private String email;
    private String userName;
    private String inputOtp;
    private String newPassword;
    private String confirmNewPassword;
}
