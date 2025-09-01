package com.adarshcare.users.service;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;

public interface UserService {
    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO); 
}
