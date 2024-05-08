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

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendActivationEmail(String toEmail, String fullName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject("EventX - Account Activation");
        String htmlContent = generateActivationEmail(fullName, generateLink(toEmail));
        messageHelper.setText(htmlContent, true);
        emailSender.send(message);
    }
    public void sendResetPasswordEmail(String toEmail, String fullName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(toEmail);
        messageHelper.setSubject("EventX - Reset Password");
        String htmlContent = generateActivationEmail(fullName, generateLink(toEmail));
        messageHelper.setText(htmlContent, true);
        emailSender.send(message);
    }
    private String generateActivationEmail(String fullName, String activationLink) {
        Context context = new Context();
        context.setVariable("fullName", fullName);
        context.setVariable("verifyLink", activationLink);
        return templateEngine.process("account_activation_email", context);
    }
    private String generateLink(String userEmail) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String serverBaseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
            return serverBaseUrl + "/activate/{" + userEmail+"}";
        } else {
            throw new IllegalStateException("Request attributes are not available");
        }
    }
}
