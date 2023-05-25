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

    @PutMapping("/update/user")
    public void updateUserFromTheList(@RequestBody User user){
        userRepository.save(user);
    }
    @DeleteMapping("/delete/user")
    public void deleteUser(@RequestBody User user){
        userRepository.deleteById(user.getId());
    }
}
