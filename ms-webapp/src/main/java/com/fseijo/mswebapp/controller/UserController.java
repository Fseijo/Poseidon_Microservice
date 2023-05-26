package com.fseijo.mswebapp.controller;


import com.fseijo.mswebapp.gateway.UserGateway;
import com.fseijo.mswebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserGateway userGateway;

    public UserController(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userGateway.getUsersList());
        return "user/list";
    }

    @RequestMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

   @RequestMapping("/user/validate")
    public String validated(User user,Model model){
        userGateway.addNewUser(user);
        model.addAttribute("users", userGateway.getUsersList());
        return "/user/list";
   }

   @GetMapping("/user/update/{id}")
   public String showUpdateForm(@PathVariable("id") Integer id,Model model){
        User user = userGateway.getUserById(id);
        model.addAttribute("user", user);
        return "user/update";
   }

   @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model, User user){
        userGateway.updateUserById(user);
        model.addAttribute("users", userGateway.getUsersList());
        return "/user/list";
   }

   @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model){
        userGateway.deleteUserById(id);
        model.addAttribute("users", userGateway.getUsersList());
        return "/user/list";
   }
}
