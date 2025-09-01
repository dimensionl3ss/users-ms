package com.adarshcare.users.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;
import com.adarshcare.users.model.User;
import com.adarshcare.users.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Implement user registration logic here
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User user = modelMapper.map(userRegistrationDTO, User.class);
        // Hash password before saving (omitted for brevity)
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }
}
