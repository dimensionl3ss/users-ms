package com.adarshcare.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;
import com.adarshcare.users.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(userService.registerUser(userRegistrationDTO));
    }
    
}
