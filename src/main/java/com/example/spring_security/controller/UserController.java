package com.example.spring_security.controller;

import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

}
