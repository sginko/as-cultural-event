package com.example.cultural_event.notification.service.emailService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    //    public void sendEmail(String mail, String subject, String content) {
    public void sendEmail(String mail, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("devbuzzmail@gmail.com");
        message.setTo(mail);
//        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }
}
