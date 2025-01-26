package com.example.LoginTest.controller;

import com.example.LoginTest.serviceImpl.EmailService;
import com.example.LoginTest.serviceImpl.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicFogottenPassword {
 @Autowired
 private ForgotPasswordService otpService;
 @Autowired
 private EmailService emailService;
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email ) {
        // Generate OTP and session ID
         otpService.forgotPassword(email);
        return ResponseEntity.ok("OTP sent to email. "); // Return session ID
    }

    @PostMapping("/reser-password")
    public ResponseEntity<String> resetPassword(@RequestParam String otp, @RequestParam String newPassword) {
   otpService.verifyOtpResetPassword(otp , newPassword);
        return ResponseEntity.ok("Password updated successfully.");
    }

}
