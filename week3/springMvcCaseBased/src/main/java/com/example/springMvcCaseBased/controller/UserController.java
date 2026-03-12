package com.example.springMvcCaseBased.controller;

import com.example.springMvcCaseBased.model.User;
import com.example.springMvcCaseBased.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show Registration Page
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle Registration
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result){

        if(result.hasErrors())
            return "register";

        userService.registerUser(user);

        return "redirect:/login";
    }

    // Show Login Page
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    // Handle Login
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model){

        if(userService.login(email,password))
            return "dashboard";

        model.addAttribute("error", "Invalid Credentials");

        return "login";
    }
}