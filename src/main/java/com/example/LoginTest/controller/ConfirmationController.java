package com.example.LoginTest.controller;

import com.example.LoginTest.Model.Resgister;
import com.example.LoginTest.entity.Users;
import com.example.LoginTest.repo.UserRepo;
import com.example.LoginTest.service.UserService;
import com.example.LoginTest.serviceImpl.TemporaryUserStorage;
import com.example.LoginTest.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class ConfirmationController {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    UserService userService;

    @Autowired
    private TemporaryUserStorage temporaryUserStorage;

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam String token) {
        // Fetch user from temporary storage using token
        Resgister user = temporaryUserStorage.getUser(token);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired confirmation token.");
        }

        userService.createUser(user);

        // Remove user from temporary storage
        temporaryUserStorage.removeUser(token);

        return ResponseEntity.ok("Account confirmed successfully. You can now log in.");
    }
}
