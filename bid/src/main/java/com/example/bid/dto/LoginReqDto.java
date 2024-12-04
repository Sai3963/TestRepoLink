/**
 * @author Anil
 * @version 1.0
 * @since 27/12/23
 */
package com.example.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
    private String email;
    private String password;
}