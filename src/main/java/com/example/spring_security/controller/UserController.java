package com.example.spring_security.controller;

import com.example.spring_security.config.JwtFilter;
import com.example.spring_security.model.User;
import com.example.spring_security.service.JwtService;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);


    @GetMapping("/hello")
    public String hello(){
        return "HELLO";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println(authentication.getCredentials());
            System.out.println(authentication.getDetails());
            return JwtService.generateToken(user.getUsername());
        }else{
            return "Failure";
        }
    }

}
