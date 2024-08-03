package com.example.api_medic.core.service;


import com.example.api_medic.core.enums.UserRole;
import com.example.api_medic.core.model.User;
import com.example.api_medic.core.repository.UserRepository;
import com.example.api_medic.rest.dto.UserDTO;
import com.example.api_medic.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public UserDTO updateUser(String userId, UserRequestDTO payload) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("The user with the given ID does not exist."));

        existingUser.setUsername(payload.getUsername());
        existingUser.setPassword(payload.getPassword());
        existingUser.setEmail(payload.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return new UserDTO(updatedUser);
    }

    public void blockUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBlocked(true);
        userRepository.save(user);
    }
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
