package com.example.api_medic.core.service;


import com.example.api_medic.core.enums.UserRole;
import com.example.api_medic.core.model.User;
import com.example.api_medic.core.repository.UserRepository;
import com.example.api_medic.rest.dto.UserDTO;
import com.example.api_medic.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        return userRepository.findById(id).map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserDTO register(UserRequestDTO userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());  // Assuming password encoding will be handled later
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        return new UserDTO(user);
    }

    public UserDTO updateUser(String id, UserRequestDTO userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public void blockUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBlocked(true);
        userRepository.save(user);
    }
}
