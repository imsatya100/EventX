package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String NAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
	private static final String PASSWORD_REGEX = "^[a-zA-Z0-9@#$%^&+=]*$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private ValidationUtils() {
    	//Private Constructor to avoid object creation
    }
    public static boolean isNull(Object obj) {
        return obj == null || obj.toString().equals("");
    }
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public static boolean validateFullName(String fullName) {
        Matcher matcher = NAME_PATTERN.matcher(fullName);
        return matcher.matches();
    }
	public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }
    public static boolean validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword.trim())) {
            return false;
        }
        // If password matches the pattern, return true, otherwise false
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    public static String getPasswordValidationMessage(String password, String confirmPassword) {
        // Check if passwords match
        if (!password.equals(confirmPassword.trim())) {
            return "Password and confirm password do not match.";
        }
        // Check password length
        if (password.length() < 8) {
            return "Password should be at least 8 characters long.";
        }
        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return "Password should contain at least one uppercase letter.";
        }
        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return "Password should contain at least one lowercase letter.";
        }
        // Check for at least one digit
        if (!password.matches(".*\\d.*")) {
            return "Password should contain at least one digit.";
        }
        // Check for at least one special character
        if (!password.matches(".*[@#$%^&+=].*")) {
            return "Password should contain at least one special character (@#$%^&+=).";
        }
        // Password meets all criteria
        return "Password is valid.";
    }
    public static String getEmailValidationMessage(String email) {
    	// Check if email matches the pattern
        if (EMAIL_PATTERN.matcher(email).matches()) {
            return "Email is valid.";
        } else {
            return "Invalid email address.";
        }
    }
    public static String getFullNameValidationMessage(String fullName) {
    	// Check if fullName matches the pattern
    	if (NAME_PATTERN.matcher(fullName).matches()) {
    		return "Full name is valid.";
    	} else {
    		return "Invalid full name.";
    	}
    }
}
