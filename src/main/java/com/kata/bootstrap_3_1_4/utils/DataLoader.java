package com.kata.bootstrap_3_1_4.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kata.bootstrap_3_1_4.model.Role;
import com.kata.bootstrap_3_1_4.model.User;
import com.kata.bootstrap_3_1_4.service.RoleService;
import com.kata.bootstrap_3_1_4.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void loadDataToDB() {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        User admin = new User(
                "Jerry", "Jerry", 29, "jerry@mail.ru", "jerry", adminRoles);
        userService.saveUser(admin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);
        User user = new User("Tom", "Tom", 31, "tom@mail.ru", "tom", userRoles);
        userService.saveUser(user);
    }
}
