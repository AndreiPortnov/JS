package com.kata.bootstrap_3_1_4.controller;

import com.kata.bootstrap_3_1_4.model.Role;
import com.kata.bootstrap_3_1_4.model.User;
import com.kata.bootstrap_3_1_4.service.RoleService;
import com.kata.bootstrap_3_1_4.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String admin(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }


    @PostMapping("create_user")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam() Set<Role> roles) {
        user.setRoles(getRolesFromDB(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("user") User user,
                         @RequestParam() Set<Role> roles) {
        user.setRoles(getRolesFromDB(roles));
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

        @GetMapping("/{id}")
        public String user (@PathVariable("id") Long id, Model model){
            model.addAttribute("user", userService.getUserById(id));
            model.addAttribute("roles", roleService.getAllRoles());
            return "admin/user";
        }

        @GetMapping("/users")
        public String list (Model model){
            model.addAttribute("users", userService.getAllUsers());
            return "admin/list";
        }

        private Set<Role> getRolesFromDB (Set<Role> roles) {
            return roles.stream()
                    //.map(role -> roleService.getRoleByName(role.getName()).orElse(null))
                    .map(role -> roleService.getRoleByName(role.getName()).orElseThrow(() -> new RuntimeException(role.getName() + " is absent in DB")))
                    .collect(Collectors.toUnmodifiableSet());
        }


}
