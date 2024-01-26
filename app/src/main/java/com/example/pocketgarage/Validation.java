package com.example.pocketgarage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean validateEmail(String email) {
        // Regular expression pattern for email validation
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        // Password length should be at least 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Password should contain at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Password should contain at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Password should contain at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Password is valid
        return true;
    }
}