package com.example.bid.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPwdDto {

    private String email;

    private String pwd;

    private String confirmPwd;

    private String otp;

}
