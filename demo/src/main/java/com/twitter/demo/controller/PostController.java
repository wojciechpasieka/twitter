package com.twitter.demo.controller;

import com.twitter.demo.entity.Post;
import com.twitter.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/addpost")
    public String addPost(@ModelAttribute("postToInsert") Post post) throws ParseException {
        postService.addPost(post);

        return "redirect:index";
    }

}
