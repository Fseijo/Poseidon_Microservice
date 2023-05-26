package com.fseijo.mscrud.controller;


import com.fseijo.mscrud.model.User;
import com.fseijo.mscrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user/validate")
    public User postNewUser(@RequestBody User user){
        User newUser = userRepository.save(user);
        return newUser;
    }
    @GetMapping("/user/update/{id}")
    public User getUserByIDFromTheList(@PathVariable ("id") int id){
       User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user ID:" + id));
        return user;
    }
    @PostMapping("/user/update/{id}")
    public User updateUserFromTheList(@PathVariable("id") int id, @RequestBody User user){
        userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user ID:" + id));
        user.setId(id);
        return userRepository.save(user);
    }
    @DeleteMapping("/user/delete/{id}")
    public void deleteUserFromTheList(@PathVariable("id") int id){
        userRepository.deleteById(id);
    }
}
