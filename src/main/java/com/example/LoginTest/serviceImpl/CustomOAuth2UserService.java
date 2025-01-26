package com.example.LoginTest.serviceImpl;

import com.example.LoginTest.entity.Users;
import com.example.LoginTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomOAuth2UserService  {

    @Autowired
    private UserRepo userRepository;

    public String loadUser(OAuth2User userRequest) {

        // Extract details from OAuth2 response
        String email = userRequest.getAttribute("email");
        String name = userRequest.getAttribute("name");
        String picture = userRequest.getAttribute("picture");

        System.out.println( name + email + picture);
        // Save or update user in the database
        Users existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPicture(picture);
            newUser.setPassword(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()));
//            newUser.setProvider("google"); // Add provider details if needed
            userRepository.save(newUser);
        } else {
            // Optionally update existing user details
            Users user = existingUser;
            user.setName(name); // Update name if changed
            user.setPicture(picture); // Update profile picture
            user.setPassword(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()));
            userRepository.save(user);
        }

        return  "Succes save"; // Return the OAuth2User object
    }
}
