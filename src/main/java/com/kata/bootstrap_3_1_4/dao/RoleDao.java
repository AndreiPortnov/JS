package com.kata.bootstrap_3_1_4.dao;


import com.kata.bootstrap_3_1_4.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(String name);

}
