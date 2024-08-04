package com.example.api_medic.rest.controllers;

import com.example.api_medic.core.service.AuthService;
import com.example.api_medic.rest.dto.LoginDTO;
import com.example.api_medic.rest.dto.LoginRequestDTO;
import com.example.api_medic.rest.dto.UserDTO;
import com.example.api_medic.rest.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
   public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(authService.signUp(user));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginRequestDTO loginRequest) throws AccessDeniedException {
        return ResponseEntity.ok(authService.signIn(loginRequest));
    }
}
