package com.example.LoginTest.serviceImpl;

import com.example.LoginTest.entity.OtpEntity;
import com.example.LoginTest.execption.InvalidOtpException;
import com.example.LoginTest.execption.OtpExpiredException;
import com.example.LoginTest.execption.UserNotFoundException;
import com.example.LoginTest.repo.EmailRepository;
import com.example.LoginTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailRepository otpTokenRepository;

    @Autowired
    private EmailService emailService;

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public void forgotPassword(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        String otp = generateOtp();
        OtpEntity otpToken = otpTokenRepository.findByEmail(email);

        if (otpToken == null) {
            otpToken = new OtpEntity();
            otpToken.setEmail(email);
        }

        otpToken.setOtp(otp);
        otpToken.setCreationDate(System.currentTimeMillis());
        otpTokenRepository.save(otpToken);

        emailService.sendOtpEmail(email, "Password Reset OTP", otp);
    }

    public void verifyOtpResetPassword(String otp, String newPassword) {
        OtpEntity otpToken = otpTokenRepository.findByOtp(otp);

        if (otpToken == null) {
            throw new InvalidOtpException("Invalid OTP");
        }

        if (isOtpExpired(otpToken)) {
            throw new OtpExpiredException("OTP has expired");
        }

        userRepository.updatePasswordByEmail(passwordEncoder.encode(newPassword), otpToken.getEmail());
        otpTokenRepository.delete(otpToken);
    }

    private boolean isOtpExpired(OtpEntity otpToken) {
        long expirationTimeInMillis = 5 * 60 * 1000; // 5 minutes
        return System.currentTimeMillis() - otpToken.getCreationDate() > expirationTimeInMillis;
    }
}
