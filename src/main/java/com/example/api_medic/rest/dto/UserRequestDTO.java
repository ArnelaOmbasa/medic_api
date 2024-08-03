package com.example.api_medic.rest.dto;

import com.example.api_medic.core.model.User;

public class UserRequestDTO {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);  // Assuming password encoding will be handled later
        user.setEmail(this.email);
        return user;
    }
}
