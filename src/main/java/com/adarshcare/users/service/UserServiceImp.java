package com.adarshcare.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;
import com.adarshcare.users.exception.ResourceNotFound;
import com.adarshcare.users.model.User;
import com.adarshcare.users.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) throws ResourceNotFound{
        // Implement user registration logic here
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new ResourceNotFound("Email already in use");
        }
        User user = modelMapper.map(userRegistrationDTO, User.class);
        // Hash password before saving (omitted for brevity)
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserbyId(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        // Implement user update logic here
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id: " + userId));
        if (userDTO.getName() != null) existingUser.setName(userDTO.getName());
        if (userDTO.getAddressLine1() != null) existingUser.setAddressLine1(userDTO.getAddressLine1());
        if (userDTO.getAddressLine2() != null) existingUser.setAddressLine2(userDTO.getAddressLine2());
        if (userDTO.getCity() != null) existingUser.setCity(userDTO.getCity());
        if (userDTO.getState() != null) existingUser.setState(userDTO.getState());
        if (userDTO.getZipCode() != null) existingUser.setZipCode(userDTO.getZipCode());
        if (userDTO.getCountry() != null) existingUser.setCountry(userDTO.getCountry());
        if (userDTO.getPhoneNumber() != null) existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getDateOfBirth() != null) existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        System.err.println("DOB: " + userDTO.getDateOfBirth());

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
    
    @Override
    public void deleteUser(String userId) {
        // Implement user deletion logic here
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFound("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override 
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
