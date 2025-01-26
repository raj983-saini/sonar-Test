package com.example.LoginTest.controller;

import com.example.LoginTest.Model.Login;
import com.example.LoginTest.Model.Resgister;
import com.example.LoginTest.entity.Users;
import com.example.LoginTest.entity.WeatherPOJO;
import com.example.LoginTest.repo.UserRepo;
import com.example.LoginTest.service.UserService;
import com.example.LoginTest.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserRepo userRepoitory;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemporaryUserStorage temporaryUserStorage;
    @Autowired
    private JwtService jwtService;  // Inject JWT Service
    @Autowired
    WeatherApi weatherApi;

    @GetMapping("/")
    public String gethealth(){
        WeatherPOJO  weather = weatherApi.getWeather("mumbai");
 if (weather!= null){
     String r= "good health "+ weather.getCurrent().getFeelslike();
     return r;
 }
        return "good health";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Resgister resgister) {
        // This check ensures we don't send duplicate confirmation emails.
        if (temporaryUserStorage.getUser(resgister.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use.");
        }

        // Generate confirmation token
        String token = emailService.genterateOtp();

        // Temporarily store user information with the token
        temporaryUserStorage.saveUser(token, resgister);

        // Send confirmation email
        emailService.sendConfirmationEmail(resgister.getEmail(), token);

        return ResponseEntity.ok("Registration successful. Please check your email to confirm your account.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );

        // Validate user and generate JWT token if authenticated
        UserDetails userDetails = loginService.loadUserByUsername(login.getEmail());

        if (userDetails != null) {
            // Generate JWT token and send it to the client
            String token = jwtService.generateToken(login.getEmail());
            return new ResponseEntity<>("Login Success, Token: "+ token, HttpStatus.OK);
        } else {
            // Return fail response with meaningful message
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
    }
}
