package com.example.LoginTest.serviceImpl;

import com.example.LoginTest.Model.Login;
import com.example.LoginTest.Model.Resgister;
import com.example.LoginTest.entity.Users;
import com.example.LoginTest.repo.UserRepo;
import com.example.LoginTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

  // Inject the encoder

    @Override
    public Users createUser(Resgister resgister) {
        Users users = new Users();
        users.setEmail(resgister.getEmail());
        users.setName(resgister.getUsername());
        users.setPassword(new BCryptPasswordEncoder().encode(resgister.getPassword()));  // Use injected encoder
        userRepo.save(users);
        return users;
    }


}
