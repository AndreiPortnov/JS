package com.kata.bootstrap_3_1_4.dao;

import org.springframework.stereotype.Repository;
import com.kata.bootstrap_3_1_4.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class RoleDaoImpl implements RoleDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r FROM Role r",Role.class)
                .getResultList();
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return entityManager
                .createQuery("select r FROM Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findFirst();
    }
}
