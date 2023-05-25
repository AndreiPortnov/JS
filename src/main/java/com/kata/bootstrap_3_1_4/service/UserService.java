package com.kata.bootstrap_3_1_4.service;



import com.kata.bootstrap_3_1_4.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService  extends UserDetailsService {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    Optional<User> getUserWithRolesByEmail(String email);
}
