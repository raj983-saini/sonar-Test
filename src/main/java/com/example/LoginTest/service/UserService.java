package com.example.LoginTest.service;

import com.example.LoginTest.Model.Login;
import com.example.LoginTest.Model.Resgister;
import com.example.LoginTest.entity.Users;

public interface UserService {
    Users createUser(Resgister resgister);

}
