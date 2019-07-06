package com.twitter.demo.service;

import com.twitter.demo.entity.User;
import com.twitter.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateFormat = sdf.format(new Date());
        user.getUserDetails().setJoinDate(sdf.parse(newDateFormat));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }

    public void deleteUser(User user) {

        User userToDelete = userRepository.findUserByLogin(user.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(userToDelete);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers;
    }

    public User findUser(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getLoggedUser() {
        String username = getLoggedUsername();
        if (username.equals("admin")) {
            User u = new User();
            u.setLogin("admin");
            u.setUserDetails(new com.twitter.demo.entity.UserDetails());
            u.getUserDetails().setEmail("admin@admin");
            u.setRole("ADMIN");
            u.getUserDetails().setName("Admin");
            return u;
        } else {
            return userRepository.findUserByLogin(username).get();
        }
    }


    public String getLoggedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
