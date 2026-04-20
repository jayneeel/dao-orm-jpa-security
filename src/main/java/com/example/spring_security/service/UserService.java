package com.example.spring_security.service;

import com.example.spring_security.model.User;
import com.example.spring_security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }
}
