package com.top.spring.security.login.service;

import com.top.spring.security.login.models.User;
import com.top.spring.security.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // Handle this according to your needs
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        // Find the user by id
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
           throw new RuntimeException("Error: User is not found.");
        }
System.out.println(userRepository.existsByEmailAndIdNot(userDetails.getEmail(), id));
        if (userRepository.existsByUsernameAndIdNot(userDetails.getUsername(), id)) {
            throw new RuntimeException("Error: Username is already taken.");
        }

        if (userRepository.existsByEmailAndIdNot(userDetails.getEmail(), id)) {
            throw new RuntimeException("Error: Email is already taken.");
        }
        // Update the user details
        User existingUser = optionalUser.get();
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        // Update other fields as needed...

        // Save the updated user
        return userRepository.save(existingUser);
    }
}
