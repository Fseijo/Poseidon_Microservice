package com.fseijo.mswebapp.controller;


import com.fseijo.mswebapp.gateway.UserGateway;
import com.fseijo.mswebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public String addUser(User bid) {
        return "user/add";
    }

   @RequestMapping("/user/validate")
    public String validated(User user,Model model){
        userGateway.addNewUser(user);
        model.addAttribute("users", userGateway.getUsersList());
        return "/user/list";
   }

//
//    @PostMapping("/user/validate")
//    public String validate(@Valid User user, BindingResult result, Model model) {
//        if (!result.hasErrors()) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            user.setPassword(encoder.encode(user.getPassword()));
//            userRepository.save(user);
//            model.addAttribute("users", userRepository.findAll());
//            return "redirect:/user/list";
//        }
//        return "user/add";
//    }
//
//    @GetMapping("/user/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        user.setPassword("");
//        model.addAttribute("user", user);
//        return "user/update";
//    }
//
//    @PostMapping("/user/update/{id}")
//    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "user/update";
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
//        user.setId(id);
//        userRepository.save(user);
//        model.addAttribute("users", userRepository.findAll());
//        return "redirect:/user/list";
//    }
//
//    @GetMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable("id") Integer id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userRepository.delete(user);
//        model.addAttribute("users", userRepository.findAll());
//        return "redirect:/user/list";
//    }
}
