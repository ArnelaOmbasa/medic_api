package com.example.api_medic.rest.dto;

import com.example.api_medic.core.model.User;

import com.example.api_medic.core.enums.UserRole;

import java.util.List;

public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private List<Integer> orders;
    private String imageUrl;
    private String dateOfBirth;

    public UserRequestDTO() { }

    public UserRequestDTO(String username, String password, String email, UserRole role, List<Integer> orders, String imageUrl, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.orders = orders;
        this.imageUrl = imageUrl;
        this.dateOfBirth = dateOfBirth;
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setOrders(this.orders);
        user.setImageUrl(this.imageUrl);
        user.setDateOfBirth(this.dateOfBirth);
        user.setBlocked(false);  // Default value
        user.setLastLoginDate(null);  // Default value
        return user;
    }

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Integer> getOrders() {
        return orders;
    }

    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}