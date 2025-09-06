package com.adarshcare.users.utility;

import java.time.LocalDate;

public class Validation {
    /**
     * Validates whether the provided email string matches a standard email format.
     * The method uses the following regular expression to check the email:
     * ^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
     *   ^[a-zA-Z0-9.%+-]+: Ensures the local part (before '@') contains one or more allowed characters (letters, digits, dot, percent, plus, hyphen).
     *   @: Requires an '@' symbol separating local and domain parts.
     *   [a-zA-Z0-9.-]+: Ensures the domain part contains one or more allowed characters (letters, digits, dot, hyphen).
     *   \.[a-zA-Z]{2,}$: Requires a dot followed by at least two letters for the top-level domain.
     * Returns true if the email is not null and matches the pattern; otherwise, returns false
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isNameValid(String name) {
        String nameRegex = "^[a-zA-Z ]{2,30}$";
        return name != null && name.matches(nameRegex);
    }

    public static boolean isCityValid(String city) {
        String cityRegex = "^[a-zA-Z ]{2,50}$";
        return city != null && city.matches(cityRegex);
    }

    public static boolean isStateValid(String state) {
        String stateRegex = "^[a-zA-Z ]{2,50}$";
        return state != null && state.matches(stateRegex);
    }

    public static boolean isCountryValid(String country) {
        String countryRegex = "^[a-zA-Z ]{2,50}$";
        return (country != null && country.matches(countryRegex));
    }

    public static boolean isAddressLineValid(String addressLine) {
        String addressLineRegex = "^[a-zA-Z0-9 ,.-]{5,100}$";
        return addressLine != null && addressLine.matches(addressLineRegex);
    }

    public static boolean isZipCodeValid(String zipCode) {
        String ZipCodeRegex = "^[1-9][0-9]{5}$";
        return zipCode != null && zipCode.matches(ZipCodeRegex);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        String phoneNumberRegex = "^[0-9]{10}$";
        return phoneNumber != null && phoneNumber.matches(phoneNumberRegex);
    }

    public static boolean isCountryCodeValid(String countryCode) {
        return countryCode != null && countryCode.equals("+91");
    }

    public static boolean isPasswordValid(String password) {
        // At least one digit, one lowercase, one uppercase, one special char, no whitespace, min 8 chars
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password != null && password.matches(passwordRegex);
    }
    
    public static boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now()) && LocalDate.now().getYear() - dateOfBirth.getYear() >= 18;
    }

}
