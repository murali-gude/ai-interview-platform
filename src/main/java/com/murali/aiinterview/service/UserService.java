package com.murali.aiinterview.service;

import com.murali.aiinterview.entity.User;
import com.murali.aiinterview.exception.ResourceNotFoundException;
import com.murali.aiinterview.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));
    }

    public User updateUser(Long id, User userDetails) {
        User existingUser = getUserById(id);

        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());

        return userRepository.save(existingUser);
    }

    public String deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
        return "User deleted successfully";
    }
}