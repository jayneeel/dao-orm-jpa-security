package com.example.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHome(HttpServletRequest request){
        return "Hello Home " + request.getSession().getId();
    }

    @GetMapping("/about")
    public String getAbout(HttpServletRequest request){
        return "About Us " + request.getSession().getId();
    }

}
