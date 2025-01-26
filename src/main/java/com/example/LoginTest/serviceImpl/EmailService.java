package com.example.LoginTest.serviceImpl;

import com.example.LoginTest.entity.OtpEntity;
import com.example.LoginTest.repo.EmailRepository;
import com.example.LoginTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class EmailService {
    Random random = new Random();
    @Autowired
    private UserRepo userRepo;
   @Autowired
   private  JavaMailSender mailSender;
    StringBuffer buffer = new StringBuffer();




    //generate otp
    public String genterateOtp(){
         for (int i = 0; i < 6; i++) {
             buffer.append(random.nextInt(10));
         }

         return String.valueOf(buffer);
     }

     //email patter
    private static final Pattern EMAIL_PATTERN =    Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    // send otp to email
    public void sendOtpEmail(String to, String subject , String otp) {
        // Validate the email address format
        if (to == null || !EMAIL_PATTERN.matcher(to).matches()) {
            throw new IllegalArgumentException("Invalid email address: " + to);
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText("Your OTP code is: " +otp );
            mailSender.send(message);
        } catch (MailParseException e) {
            // Log the error with detailed message
            System.err.println("MailParseException while sending email to " + to + ": " + e.getMessage());
            throw new MailParseException("Could not send email to " + to, e);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            throw new RuntimeException("Could not send email to " + to, e);
        }
    }

    public void sendConfirmationEmail(String email, String token) {
        System.out.println(email);
        // Validate the email address format
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
        String confirmationUrl = "http://localhost:9000/public/confirm?token=" + token;
        try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Confirm Your Registration");
        message.setText("Click the link to confirm your registration: " + confirmationUrl);
        mailSender.send(message);
        } catch (MailParseException e) {
            // Log the error with detailed message
            System.err.println("MailParseException while sending email to " + email + ": " + e.getMessage());
            throw new MailParseException("Could not send email to " + email, e);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            throw new RuntimeException("Could not send email to " + email, e);
    }
}
}
