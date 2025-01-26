package com.example.LoginTest.execption;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class OtpExpiredException extends RuntimeException {
    public OtpExpiredException(String message) {
        super(message);
    }
}
