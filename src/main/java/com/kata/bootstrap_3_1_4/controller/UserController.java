package com.kata.bootstrap_3_1_4.controller;


import com.kata.bootstrap_3_1_4.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String showMe(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "users/show";
    }

}
