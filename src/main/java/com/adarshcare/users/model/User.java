package com.adarshcare.users.model;

import org.springframework.data.mongodb.core.annotation.Collation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Collation(value="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private String id;
    private String name;
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String countryCode;
    private String role; // e.g., "USER", "ADMIN"
    private String profilePictureUrl;
    private String dateOfBirth;
}
