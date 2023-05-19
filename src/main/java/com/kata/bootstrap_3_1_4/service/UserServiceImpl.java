package com.kata.bootstrap_3_1_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kata.bootstrap_3_1_4.dao.UserDao;
import com.kata.bootstrap_3_1_4.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(getUserById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> getUserWithRolesByEmail(String email){
        return userDao.getUserWithRolesByEmail(email);
    }

}
