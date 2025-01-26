package com.example.LoginTest.controller;

import com.example.LoginTest.Model.Login;
import com.example.LoginTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private")
public class PrivateController {
    @Autowired
    UserService userService;
    @GetMapping("/")
   private String getDetails(){
       return  "Hello ";
   }
}
