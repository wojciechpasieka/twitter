package com.twitter.demo.controller;

import com.twitter.demo.entity.Comment;
import com.twitter.demo.entity.Post;
import com.twitter.demo.entity.User;
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
        User loggedUser = userService.getLoggedUser();
        model.addAttribute("loggedUser", loggedUser);

        model.addAttribute("postToInsert", new Post());
        model.addAttribute("postToDelete", new Post());
        model.addAttribute("commentToInsert", new Comment());
        model.addAttribute("commentToDelete", new Comment());

        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "index";
    }



}
