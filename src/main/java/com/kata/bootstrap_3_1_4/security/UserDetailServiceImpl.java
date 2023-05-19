package com.kata.bootstrap_3_1_4.security;

import com.kata.bootstrap_3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;


    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserWithRolesByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User ‘" + username + "’ not found"));
    }
}
