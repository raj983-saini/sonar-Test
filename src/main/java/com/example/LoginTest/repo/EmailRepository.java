package com.example.LoginTest.repo;

import com.example.LoginTest.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<OtpEntity , Long> {
   OtpEntity findByEmail(String email);
   OtpEntity findByOtp(String otp);
}
