package com.twitter.demo.service;

import com.twitter.demo.entity.Post;
import com.twitter.demo.entity.User;
import com.twitter.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAllByCreateDateOrderByDesc();
        //return postRepository.findAll();
    }

    public void addPost(Post post) throws ParseException {
        //setting date of creation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        post.setCreateDate(sdf.parse(newDateFormat));

        //setting user for post
        User user = userService.findUser(userService.getLoggedUsername());
        post.setUser(user);


        postRepository.save(post);
    }

    public void deletePost(Post post){
        Post postToDelete = postRepository.findOne(post.getId());

        String currentLoggedUser = userService.getLoggedUsername();
        if (!postToDelete.getUser().getLogin().equals(currentLoggedUser)) {
            throw new RuntimeException("Nie Twoj Post!");
        }
        postRepository.delete(postToDelete);
    }


}
