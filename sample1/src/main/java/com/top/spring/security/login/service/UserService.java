package com.top.spring.security.login.service;

import com.top.spring.security.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);
    Object updateUser(Long id , User user);
    void deleteUser(Long id);
}