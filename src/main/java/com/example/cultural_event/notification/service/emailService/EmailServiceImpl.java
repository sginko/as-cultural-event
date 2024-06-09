package com.example.cultural_event.notification.service.emailService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

        public void sendEmail(String mail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@cultural-event.com");
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }
}
