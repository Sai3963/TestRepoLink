/**
 * @author Anil
 * @version 1.0
 * @since 06/12/23
 */
package com.example.bid.util;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private HttpStatus status;
    private String message;
    private Object data;
    private long timestamp = new Date().getTime();
    private int statusCode;

    public ApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.statusCode = status.value();
    }

    public ApiResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.statusCode = status.value();
        this.data = data;
    }
}