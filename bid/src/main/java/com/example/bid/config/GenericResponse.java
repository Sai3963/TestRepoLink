package com.example.bid.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@NoArgsConstructor
public class GenericResponse {
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    private Object data;


    public GenericResponse(HttpStatus httpStatus, int statusCode, String message, Object data) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(HttpStatus httpStatus, int statusCode, String message) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
    }

    public GenericResponse(HttpStatus httpStatus, int statusCode, Object data) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.data = data;
    }


    public GenericResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public GenericResponse(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public GenericResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}