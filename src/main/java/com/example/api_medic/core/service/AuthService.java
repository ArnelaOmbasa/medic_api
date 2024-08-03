package com.example.api_medic.core.service;


import com.example.api_medic.core.exceptions.repository.ResourceNotFoundException;
import com.example.api_medic.core.model.User;
import com.example.api_medic.core.repository.UserRepository;
import com.example.api_medic.rest.dto.LoginDTO;
import com.example.api_medic.rest.dto.LoginRequestDTO;
import com.example.api_medic.rest.dto.UserDTO;
import com.example.api_medic.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        User user = userRepository.save(userRequestDTO.toEntity());

        return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));

        user.setLastLoginDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);

        return new LoginDTO(jwt);
    }
}
