package com.example.LoginTest.repo;

import com.example.LoginTest.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<Users , Long> {
 Users findByEmail(String email);
 boolean existsByEmail (String email);
 @Modifying
 @Transactional
 @Query("UPDATE Users u SET u.password = :newPassword WHERE u.email = :email")
 void updatePasswordByEmail(@Param("newPassword") String newPassword, @Param("email") String email);
}
