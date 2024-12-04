package com.example.bid.service;

import com.example.bid.config.GenericResponse;
import com.example.bid.dto.ConfirmForgotPasswordDto;
import com.example.bid.dto.ForgotPwdDto;
import com.example.bid.dto.UserDetailsDto;

public interface AppUserService {
    GenericResponse saveUser(UserDetailsDto dto);

    GenericResponse forgotPassword(ForgotPwdDto dto);

    GenericResponse login(UserDetailsDto dto);

    GenericResponse mail();

    GenericResponse forgotPasswordConfirm(ConfirmForgotPasswordDto request);

    GenericResponse changePassword(ConfirmForgotPasswordDto request);
}
