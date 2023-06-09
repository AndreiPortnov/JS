package com.kata.bootstrap_3_1_4.controller;


import com.kata.bootstrap_3_1_4.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class UserController {


    @ResponseBody
    @GetMapping("/api/v1/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
