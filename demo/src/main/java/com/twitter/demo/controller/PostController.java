package com.twitter.demo.controller;

import com.twitter.demo.entity.Post;
import com.twitter.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/deletePost")
    public String deletePost(@ModelAttribute("postToDelete") Post post, RedirectAttributes redirectAttributes){
        try {
            postService.deletePost(post);
            redirectAttributes.addAttribute("message", "Success");
        } catch(RuntimeException e) {
            System.out.println("Rzucony wyjatek");
            redirectAttributes.addAttribute("message", "Failed");
        }
        return "redirect:index";
    }

}
