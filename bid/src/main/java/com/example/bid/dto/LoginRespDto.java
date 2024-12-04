/**
 * @author Anil
 * @version 1.0
 * @since 27/12/23
 */
package com.example.bid.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespDto {
    private String userName;
    private String token;
    private String role;
    private String name;
}