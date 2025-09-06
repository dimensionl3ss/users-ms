package com.adarshcare.users.controller;

import java.util.List;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;
import com.adarshcare.users.service.UserService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserService userService;
    @PostMapping(path="/register", consumes="application/json", produces="application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(userService.registerUser(userRegistrationDTO));
    }
    
    @GetMapping(params="userId", produces="application/json")
    public ResponseEntity<UserDTO> getUserById(@RequestParam @NotBlank String userId) {
        return ResponseEntity.ok(userService.getUserbyId(userId));
    }
    @GetMapping(params="email", produces = "application/json")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam @NotBlank String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    
    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping(params="userId", consumes="application/json", produces="application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestParam @NotBlank String userId, @RequestBody @NotBlank UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam @NotBlank String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).build();
    }
    
}
