package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.utils.StatusMap;
import com.example.demo.utils.ValidationUtils;

@RestController
@RequestMapping("/api/v1/users")
public class UserControlle {
	@Autowired
    private UserService userService;
	@Autowired
	private EmailService emailService;
	private String errorMsg="";

	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
	
    // Create
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
    	errorMsg = userService.validateUser(user);
    	if(errorMsg.isEmpty()) {
    		user = userService.createUser(user);
    		try {
    			emailService.sendActivationEmail(user.getEmail(), user.getName());
        		return ResponseEntity.ok("Thanks for signing up. Verify email address to create account.");
    		}catch (Exception e) {
    			e.printStackTrace();
    			errorMsg = "There was a problem while sending mail.";
    		}
    	}
    	return ResponseEntity.badRequest().body(errorMsg);
    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetUserPassword(@RequestParam String email) {
    	errorMsg="";
    	if(ValidationUtils.isNotNullOrEmpty(email)) {
    		errorMsg = ValidationUtils.getEmailValidationMessage(email);
    	}else {
    		errorMsg = "Please enter email address.";
    	}
    	if(errorMsg.isEmpty()) {
    		try {
    			User user = userService.getUserDetails(email);
    			if(user.getStatusMain().getStatusId().equals(StatusMap.ACTIVE)) {
    				emailService.sendResetPasswordEmail(user.getEmail(), user.getName());
            		return ResponseEntity.ok("We have e-mailed your password reset link!");
        		}else if(user.getStatusMain().getStatusId().equals(StatusMap.DRAFT)){
        			emailService.sendActivationEmail(user.getEmail(), user.getName());
            		return ResponseEntity.ok("Verify email address to activate your account.");
        		}else {
        			errorMsg = "No account found please sign up first.";
        		}
    		}catch (Exception e) {
    			e.printStackTrace();
    			errorMsg = "There was a problem while sending mail.";
    		}
    	}
    	return ResponseEntity.badRequest().body(errorMsg);
    }

    // Read
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    	if(ValidationUtils.isNull(id)) {
    		return ResponseEntity.notFound().build();
    	}
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }
    @PostMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam String email) {
    	errorMsg = "";
    	if(ValidationUtils.isNotNullOrEmpty(email)) {
    		userService.activateUser(email);
    		return ResponseEntity.ok("Account activated successful");
    	}else {
    		errorMsg = "Please provide correct details";
    	}
    	if(!ValidationUtils.validateEmail(email)) {
    		errorMsg = ValidationUtils.getEmailValidationMessage(email);
    	}
        return ResponseEntity.badRequest().body(errorMsg);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
