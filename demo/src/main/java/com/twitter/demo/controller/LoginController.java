package com.twitter.demo.controller;

import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {



    @RequestMapping("/login")
    public String login(){
        return "login";
    }


}
