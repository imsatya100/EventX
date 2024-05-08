package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StatusMain;
import com.example.demo.model.User;
import com.example.demo.repository.StatusMainRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.PrivilageUtils;
import com.example.demo.utils.StatusMap;
import com.example.demo.utils.ValidationUtils;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private StatusMainRepository statusMainRepository;
	LocalDateTime currentDateTime = LocalDateTime.now();
	
	public String validateUser(User user) {
		// Check user email
		if(ValidationUtils.isNotNullOrEmpty(user.getName())) {
			if(!ValidationUtils.validateFullName(user.getName())) {
				return ValidationUtils.getFullNameValidationMessage(user.getName());
			}
		}else {
			return "Full name is required";
		}
		if(ValidationUtils.isNotNullOrEmpty(user.getEmail())) {
			if(!ValidationUtils.validateEmail(user.getEmail())) {
				return ValidationUtils.getEmailValidationMessage(user.getEmail());
			}
		}else {
			return "Email address is required";
		}
		if(ValidationUtils.isNullOrEmpty(user.getPassword())) {
			return "Password is required";
		}
		if(ValidationUtils.isNullOrEmpty(user.getPassword())) {
			return "Password is required";
		}
		if(!ValidationUtils.validatePassword(user.getPassword(), user.getConfirmPassword())) {
			return ValidationUtils.getPasswordValidationMessage(user.getPassword(), user.getConfirmPassword());
		}
        
        return "";
    }
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
	
    //  =========  Create operation  =========  
	@Transactional
    public User createUser(User user) {
    	try{
    		StatusMain draftStatusMain = statusMainRepository.findById(StatusMap.DRAFT).orElseThrow(() -> new ResourceNotFoundException("Status Main not found"));
        	user.setCreatedDate(Timestamp.valueOf(currentDateTime));
        	user.setModifiedDate(Timestamp.valueOf(currentDateTime));
        	user.setStatusMain(draftStatusMain);
        	user.setRole(PrivilageUtils.ROLE_ADMIN);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return userRepository.save(user);
    }
 
    //   =========  Read operation  =========  
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public boolean isActiveAccount(String email) {
    	User user = userRepository.findByEmail(email)
    			.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    	return user.getStatusMain().getStatusId().equals(StatusMap.ACTIVE);  
    }
    
    public User getUserDetails(String email) {
    	User user = userRepository.findByEmail(email)
    			.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    	return user.getRole().equals(PrivilageUtils.ROLE_ADMIN)? user : null;  
    }

    //  =========   Update operation   =========  
    @Transactional
    public User updateUser(Long id, User userDetails) {
    	try {
    		User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    		user.setName(userDetails.getName());
            user.setStatusMain(userDetails.getStatusMain());
            user.setModifiedDate(Timestamp.valueOf(currentDateTime));
            return userRepository.save(user);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        return userDetails;
    }
   
    @Transactional
    public User activateUser(String email) {
    	try {
    		User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            StatusMain activeStatusMain = statusMainRepository.findById(StatusMap.ACTIVE)
            		.orElseThrow(() -> new ResourceNotFoundException("Status Main not found"));
            if(user.getStatusMain().getStatusId().equals(StatusMap.DELETE)) 
            	user.setStatusMain(activeStatusMain);
            user.setModifiedDate(Timestamp.valueOf(currentDateTime));
            return userRepository.save(user);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
        return null;
    }
    //  =========   Delete operation   =========  
    @Transactional
    public void deleteUser(Long id) {
    	try {
    		User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            StatusMain deleteStatusMain = statusMainRepository.findById(StatusMap.DELETE)
            		.orElseThrow(() -> new ResourceNotFoundException("Status Main not found"));
            user.setStatusMain(deleteStatusMain);
            user.setModifiedDate(Timestamp.valueOf(currentDateTime));
            userRepository.save(user);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
   
}
