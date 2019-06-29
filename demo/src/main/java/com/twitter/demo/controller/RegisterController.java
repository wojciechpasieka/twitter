package com.twitter.demo.controller;

import com.twitter.demo.entity.User;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public ModelAndView registerUser() {
        return new ModelAndView("registerForm", "userToRegister",
                new User());
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User user) throws ParseException {
        System.out.println(user.getLogin());
        userService.saveUser(user);

        return "userRegistered";
    }

}