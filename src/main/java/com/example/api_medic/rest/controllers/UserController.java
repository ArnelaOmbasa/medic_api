package com.example.api_medic.rest.controllers;

import com.example.api_medic.core.service.UserService;
import com.example.api_medic.rest.dto.UserDTO;
import com.example.api_medic.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "JWT Security")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @RequestMapping(value = "/block/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> blockUser(@PathVariable String id) {
        userService.blockUser(id);
        return ResponseEntity.noContent().build();
    }
}