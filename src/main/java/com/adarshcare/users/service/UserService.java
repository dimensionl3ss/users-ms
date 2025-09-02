package com.adarshcare.users.service;

import java.util.List;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;

public interface UserService {
    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO); 
    UserDTO getUserbyId(String userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);
}
