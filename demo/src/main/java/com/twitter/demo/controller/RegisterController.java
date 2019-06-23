package com.twitter.demo.controller;

import com.twitter.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public ModelAndView registerUser() {
        return new ModelAndView("registerForm", "userToRegister",
                new User());
    }

    @PostMapping("/adduser")
    public String addUser() {
        return "userRegistered";
    }


}