package com.example.bid.service.impl;

import com.example.bid.config.GenericResponse;
import com.example.bid.config.JwtService;
import com.example.bid.dto.ConfirmForgotPasswordDto;
import com.example.bid.dto.ForgotPwdDto;
import com.example.bid.dto.UserDetailsDto;
import com.example.bid.entity.AppUser;
import com.example.bid.entity.Token;
import com.example.bid.repo.AppUserRepo;
import com.example.bid.repo.TokenRepository;
import com.example.bid.service.AppUserService;
import com.example.bid.util.BidUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AppUserServiceImpl implements AppUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Gson gson;

    @Override
    public GenericResponse saveUser(UserDetailsDto dto) {
        AppUser user = new AppUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        if (appUserRepo.existsByEmail(dto.getEmail())) {
            return new GenericResponse(HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value(),
                    "User already exists with this email");
        }
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        LOGGER.error("User Details>>>>>>>>>>>>>" + gson.toJson(dto));

        appUserRepo.save(user);
        return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Signup Success");
    }

    @Override
    public GenericResponse forgotPassword(ForgotPwdDto dto) {
        String otp = BidUtils.generateOTP();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rakeshkumarboya33@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject("Reset Password");
        message.setText("Hi Please find the verification code for testing purpose: " + otp);
        redisTemplate.opsForValue().set(dto.getEmail(), otp, 300, TimeUnit.SECONDS);
        javaMailSender.send(message);
        return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Email Sent Successfully");
    }

    @Override
    public GenericResponse forgotPasswordConfirm(ConfirmForgotPasswordDto request) {
        String value = redisTemplate.opsForValue().get(request.getEmail());
        if (value != null) {
            if (value.equals(request.getInputOtp())) {
                return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Verification Code Matched Successfully");
            } else {
                return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "OTP not matched. Please re verify.");
            }
        } else {
            return new GenericResponse(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR.value(), "OTP expired");
        }
    }

    @Override
    public GenericResponse changePassword(ConfirmForgotPasswordDto request) {
        if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
            var user = appUserRepo.findByEmail(request.getEmail());
            AppUser appUser = user.get();
            appUser.setPassword(request.getConfirmNewPassword());
            appUserRepo.save(appUser);
            return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Password Changed Successfully");
        }
        return new GenericResponse(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Passwords did not match");
    }

    @Override
    public GenericResponse login(UserDetailsDto dto) {
        var user = appUserRepo.findByEmail(dto.getEmail());
        if (user.isPresent() && BCrypt.checkpw(dto.getPassword(), user.get().getPassword())) {
            var jwtToken = jwtService.generateToken(user.get());
            Token token = new Token();
            token.setToken(jwtToken);
            token.setUser(user.get());
            tokenRepository.save(token);
            return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Login successful", jwtToken);
        }
        return new GenericResponse(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "Invalid credentials");
    }

    @Override
    public GenericResponse mail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rakeshkumarboya33@gmail.com");
        message.setTo("saikumar3963@gmail.com");
        message.setSubject("Testing");
        message.setText("Hi THis is for testing purpose");
        //  redisTemplate.opsForValue().set("saikumar3963@gmail.com", "12345", 120, TimeUnit.SECONDS);
        String str = redisTemplate.opsForValue().get("saikumar3963@gmail.com");
        System.out.println("STRRRRRR: " + str);
        //  javaMailSender.send(message);
        return new GenericResponse(HttpStatus.OK, HttpStatus.OK.value(), "Mail Sent Successfully");

    }


}
