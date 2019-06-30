package com.twitter.demo.controller;

import com.twitter.demo.entity.Comment;
import com.twitter.demo.entity.Post;
import com.twitter.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;

@Controller
public class CommentCotroller {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addcomment")
    public String addPost(@ModelAttribute("commentToInsert") Comment comment) throws ParseException {
        commentService.addComment(comment);

        return "redirect:index";
    }

}
