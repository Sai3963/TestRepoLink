package com.example.bid.controller;

import com.example.bid.config.GenericResponse;
import com.example.bid.dto.ConfirmForgotPasswordDto;
import com.example.bid.dto.ForgotPwdDto;
import com.example.bid.dto.UserDetailsDto;
import com.example.bid.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AppUserService userService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Environment environment;


    @PostMapping("/signup")
    public GenericResponse saveUser(@RequestBody UserDetailsDto dto) {
        logger.info("save user service started");
        GenericResponse apiResponse = userService.saveUser(dto);
        logger.info("save user service completed");
        return apiResponse;
    }

    @PostMapping("/login")
    public GenericResponse login(@RequestBody UserDetailsDto dto) {
        logger.info("login service started");
        GenericResponse apiResponse = userService.login(dto);
        logger.info("login service completed");
        logger.info("Mail sent");
        return apiResponse;
        //  return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @GetMapping("/mail")
    public GenericResponse mail() {
        logger.info("Mail service started");
        GenericResponse apiResponse = userService.mail();
        logger.info("Mail service completed");
        return apiResponse;
    }

    @PostMapping("/forgot-password")
    public GenericResponse forgotPassword(@RequestBody ForgotPwdDto dto) {
        logger.info("forgot password service started");
        GenericResponse apiResponse = userService.forgotPassword(dto);
        logger.info("forgot password service completed");
        return apiResponse;
    }

    @PostMapping("/forgot-password-confirm")
    public GenericResponse forgotPasswordConfirm(@RequestBody ConfirmForgotPasswordDto request) {
        logger.info( "Forgot Password Confirm Service Started" );
        GenericResponse apiResponse = userService.forgotPasswordConfirm( request );
        logger.info( "Forgot Password Confirm Service Completed" );
        return apiResponse;
    }

    @PostMapping("/change-password")
    public GenericResponse changePassword(@RequestBody ConfirmForgotPasswordDto request) {
        logger.info( "change Password Service Started" );
        GenericResponse apiResponse = userService.changePassword( request );
        logger.info( "change Password Service Completed" );
        return apiResponse;
    }
}
