package com.twitter.demo.service;

import com.twitter.demo.entity.User;
import com.twitter.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        user.getUserDetails().setJoinDate(sdf.parse(newDateFormat));

        user.setRole("ROLE_USER");

        userRepository.save(user);
    }

    public void deleteUser(User user){

        User userToDelete = userRepository.findUserByLogin(user.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(userToDelete);
    }

    public List<User> getAllUsers(){
        List<User> allUsers = userRepository.findAll();

        return allUsers;
    }

    public User findUser(String login){
        return userRepository.findUserByLogin(login).orElseThrow(()->new RuntimeException("User not found"));
    }

    public String getLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
