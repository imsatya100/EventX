package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.utils.StatusMap;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private EmailDetailsService emailDetailsService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendActivationEmail(String toEmail, String fullName) throws MessagingException {
    	String activateAccountSubject = "MeetsnGreets - Activate Your Account";
        String activationTemplate = "account_activation_email";
    	String emailLink = generateEmailLink("activate",toEmail);
        String htmlContent = generateEmailBodyFromTemplate(fullName, emailLink,activationTemplate);
        emailDetailsService.saveEmailDetails(toEmail, activationTemplate, emailLink, activateAccountSubject, htmlContent,StatusMap.UNSEND);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(activateAccountSubject);
        messageHelper.setText(htmlContent, true);
        try {
        	emailSender.send(message);
        	emailDetailsService.saveEmailDetails(toEmail, activationTemplate, emailLink, activateAccountSubject, htmlContent,StatusMap.SENT);
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    public void sendResetPasswordEmail(String toEmail, String fullName) throws MessagingException {
    	String resetPasswordSubject = "MeetsnGreets - Reset Your Account Password";
        String resetPasswordTemplate = "reset_password_email";
        String emailLink = generateEmailLink("resetPassword",toEmail);
        String htmlContent = generateEmailBodyFromTemplate(fullName, emailLink,resetPasswordTemplate);
        emailDetailsService.saveEmailDetails(toEmail, resetPasswordTemplate, emailLink, resetPasswordSubject, htmlContent,StatusMap.UNSEND);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(resetPasswordSubject);
        messageHelper.setText(htmlContent, true);
        try {
        	emailSender.send(message);
        	emailDetailsService.saveEmailDetails(toEmail, resetPasswordTemplate, emailLink, resetPasswordSubject, htmlContent,StatusMap.SENT);
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
    private String generateEmailBodyFromTemplate(String fullName, String verifyLink,String templateName) {
        Context context = new Context();
        context.setVariable("fullName", fullName);
        context.setVariable("verifyLink", verifyLink);
        return templateEngine.process(templateName, context);
    }
    private String generateEmailLink(String path,String userEmail) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String serverBaseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
            return serverBaseUrl + "/"+path+"/{" + userEmail+"}";
        } else {
            throw new IllegalStateException("Request attributes are not available");
        }
    }
}
