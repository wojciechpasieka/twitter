package com.twitter.demo.controller;

import com.twitter.demo.entity.Comment;
import com.twitter.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/deleteComment")
    public String deleteComment(@ModelAttribute("commentToDelete") Comment comment, RedirectAttributes redirectAttributes){
        try {
            commentService.deleteComment(comment);
            redirectAttributes.addAttribute("message_comment", "Success");
        } catch(RuntimeException e) {
            System.out.println("Rzucony wyjatek");
            redirectAttributes.addAttribute("message_comment", "Failed");
        }
        return "redirect:index";
    }

}
