package com.twitter.demo.controller;

import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        String loggedUser = userService.getLoggedUser();
        model.addAttribute("userLogin", loggedUser);

        return "index";
    }
}
