package com.murali.aiinterview.dto;

import com.murali.aiinterview.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private User user;
    private String token;
}