package com.murali.aiinterview.controller;

import com.murali.aiinterview.dto.ApiResponse;
import com.murali.aiinterview.dto.LoginRequest;
import com.murali.aiinterview.dto.UserRequest;
import com.murali.aiinterview.entity.User;
import com.murali.aiinterview.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> createUser(@Valid @RequestBody UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userService.saveUser(user);

        return new ApiResponse<>(
                true,
                "User created successfully",
                savedUser
        );
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody LoginRequest request) {

        User user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new ApiResponse<>(
                true,
                "Login successful",
                user
        );
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return new ApiResponse<>(
                true,
                "Users fetched successfully",
                users
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);

        return new ApiResponse<>(
                true,
                "User fetched successfully",
                user
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails) {

        User updatedUser = userService.updateUser(id, userDetails);

        return new ApiResponse<>(
                true,
                "User updated successfully",
                updatedUser
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {

        String message = userService.deleteUser(id);

        return new ApiResponse<>(
                true,
                "User deleted successfully",
                message
        );
    }
}