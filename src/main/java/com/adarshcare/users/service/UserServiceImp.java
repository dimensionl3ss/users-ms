package com.adarshcare.users.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarshcare.users.dto.UserDTO;
import com.adarshcare.users.dto.UserRegistrationDTO;
import com.adarshcare.users.exception.ResourceNotFound;
import com.adarshcare.users.model.Role;
import com.adarshcare.users.model.User;
import com.adarshcare.users.repository.UserRepository;
import com.adarshcare.users.utility.Validation;

@Service
public class UserServiceImp implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) throws ResourceNotFound{
        // Implement user registration logic here
        // if (Validation.isValidEmail(userRegistrationDTO.getEmail()) == false) {
        //     throw new IllegalArgumentException("Invalid email format");
        // }
        // if (Validation.isPasswordValid(userRegistrationDTO.getPassword()) == false) {
        //     throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, one special character, and have no whitespace.");
        // }
        // if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
        //     throw new ResourceNotFound("Email already in use");
        // }
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setId(generateUniqueId());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
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
        

        if (Validation.isNameValid(userDTO.getName()) == false) {
            throw new IllegalArgumentException("Name must be 2-50 characters long and contain only letters and spaces.");
        }
        if (Validation.isAddressLineValid(userDTO.getAddressLine1()) == false) {
            throw new IllegalArgumentException("Address Line 1 must be 5-100 characters long and can contain letters, digits, spaces, commas, periods, and hyphens.");
        }
        if (userDTO.getAddressLine2() != null && !userDTO.getAddressLine2().isEmpty()) {
            if (Validation.isAddressLineValid(userDTO.getAddressLine2()) == false) {
                throw new IllegalArgumentException("Address Line 2 must be 5-100 characters long and can contain letters, digits, spaces, commas, periods, and hyphens.");
            }
        }
        if (Validation.isCityValid(userDTO.getCity()) == false) {
            throw new IllegalArgumentException("City must be 2-50 characters long and contain only letters and spaces.");
        }
        if (Validation.isStateValid(userDTO.getState()) == false) {
            throw new IllegalArgumentException("State must be 2-50 characters long and contain only letters and spaces.");
        }
        if (Validation.isCountryValid(userDTO.getCountry()) == false) {
            throw new IllegalArgumentException("Country must be 2-50 characters long and contain only letters and spaces.");      
        
        }
        if (Validation.isZipCodeValid(userDTO.getZipCode()) == false) {
            throw new IllegalArgumentException("Zip code must be a 6-digit number not starting with zero.");
        }
        if (Validation.isPhoneNumberValid(userDTO.getPhoneNumber()) == false) {
            throw new IllegalArgumentException("Phone number must be a 10-digit number.");
        }
        if (Validation.isCountryCodeValid(userDTO.getCountryCode()) == false) {
            throw new IllegalArgumentException("Country code must be +91.");
        }
        if (Validation.isDateOfBirthValid(userDTO.getDateOfBirth()) == false) {
            throw  new IllegalArgumentException("Date of birth must be in YYYY-MM-DD format and the user must be at least 18 years old.");
        }

        existingUser.setName(userDTO.getName());
        existingUser.setAddressLine1(userDTO.getAddressLine1());
        existingUser.setAddressLine2(userDTO.getAddressLine2());
        existingUser.setCity(userDTO.getCity());
        existingUser.setState(userDTO.getState()); 
        existingUser.setZipCode(userDTO.getZipCode());
        existingUser.setCountry(userDTO.getCountry());
        existingUser.setCountryCode(userDTO.getCountryCode());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        existingUser.setUpdatedAt(LocalDateTime.now());

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

    private String generateUniqueId() {
        // Implement a method to generate a unique ID for the user
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("User not found with email: " + email));
        return modelMapper.map(user, UserDTO.class);
    }
}
