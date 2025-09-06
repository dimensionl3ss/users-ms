package com.adarshcare.users.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.adarshcare.users.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String countryCode;
    private Role role; // e.g., "USER", "ADMIN"
    private String profilePictureUrl;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
