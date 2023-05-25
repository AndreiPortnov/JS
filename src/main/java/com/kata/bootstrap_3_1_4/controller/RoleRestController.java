package com.kata.bootstrap_3_1_4.controller;

import com.kata.bootstrap_3_1_4.model.Role;
import com.kata.bootstrap_3_1_4.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;


    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> list() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

}
