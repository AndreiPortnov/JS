package com.kata.bootstrap_3_1_4.service;

import com.kata.bootstrap_3_1_4.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(String name);

}
