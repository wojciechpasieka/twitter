package com.twitter.demo.controller;

import com.twitter.demo.entity.Post;
import com.twitter.demo.service.PostService;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        String loggedUser = userService.getLoggedUser();
        model.addAttribute("userLogin", loggedUser);

        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "index";
    }
}
