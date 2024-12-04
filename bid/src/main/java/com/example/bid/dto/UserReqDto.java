package com.example.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserReqDto {

    private String id;

    private String name;

    private String email;

    private String contactNo;

    private String password;

    private String role;

    private String mtCountryId;

    private LocalDateTime lastUpdateDate;

    private String serviceProviderId;

    private boolean isActive;

}
