package com.twitter.demo.service;

import com.twitter.demo.entity.Comment;
import com.twitter.demo.entity.Post;
import com.twitter.demo.entity.User;
import com.twitter.demo.repository.CommentRepository;
import com.twitter.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void addComment(Comment comment) throws ParseException {
        //setting date of creation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        comment.setCreateDate(sdf.parse(newDateFormat));

        //setting user for comment
        User user = userService.findUser(userService.getLoggedUsername());
        comment.setUser(user);

        //setting post for comment
        Post thisPost = postRepository.findOne(comment.getPostId());
        comment.setPost(thisPost);

        commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        Comment commentToDelete = commentRepository.findOne(comment.getId());

        String currentLoggedUser = userService.getLoggedUsername();
        if (!commentToDelete.getUser().getLogin().equals(currentLoggedUser)) {
            throw new RuntimeException("Nie Twoj Post!");
        }
        commentRepository.delete(commentToDelete);
    }
}
