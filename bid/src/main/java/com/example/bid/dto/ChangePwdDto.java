package com.example.bid.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwdDto {

    private String oldPwd;

    private String newPwd;

    private String confirmPwd;
}
