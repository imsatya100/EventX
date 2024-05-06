package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StatusMain;
import com.example.demo.model.StatusMap;
import com.example.demo.model.User;
import com.example.demo.repository.StatusMainRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private StatusMainRepository statusMainRepository;
	LocalDateTime currentDateTime = LocalDateTime.now();
	
	public boolean authenticate(String email, String password) {
		try{
			User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        if (user != null && password.equals(user.getPassword())) {
	            return true;
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
        return false;
    }
	
    // Create operation
    public User createUser(User user) {
    	StatusMain activeStatusMain = statusMainRepository.findById(StatusMap.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("Status Main not found"));
    	user.setCreatedDate(Timestamp.valueOf(currentDateTime));
    	user.setModifiedDate(Timestamp.valueOf(currentDateTime));
    	user.setStatusMain(activeStatusMain);
        return userRepository.save(user);
    }

    // Read operation
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update operation
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setStatusMain(userDetails.getStatusMain());
        user.setModifiedDate(Timestamp.valueOf(currentDateTime));
        return userRepository.save(user);
    }

    // Delete operation
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
