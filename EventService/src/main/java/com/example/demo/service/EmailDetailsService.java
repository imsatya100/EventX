package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailDetails;
import com.example.demo.model.User;
import com.example.demo.repository.EmailDetailsRepository;
@Service
public class EmailDetailsService {
	@Autowired
    private EmailDetailsRepository emailDetailsRepository;

	@Autowired
	UserService userService;
	
	@Value("${spring.mail.username}")
    private String fromEmail;
	
    // Create operation
	public EmailDetails saveEmailDetails(String toEmail, String templateName,String emailLink, String emailSubject,Integer status) {
    	User user = userService.getUserDetails(toEmail);
    	EmailDetails emailDetails = new EmailDetails();
        emailDetails.setFromEmail(fromEmail);
        emailDetails.setToEmail(toEmail);
        emailDetails.setCreatedBy(user);
        emailDetails.setEmailLink(emailLink);
        emailDetails.setEmailSubject(emailSubject);
        emailDetails.setEmailTemplateName(templateName);
        emailDetails.setStatus(status);
        emailDetails.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        return emailDetailsRepository.save(emailDetails);
    }
}
