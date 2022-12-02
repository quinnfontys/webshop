package com.qrdsn.fullstackbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerifyMail(String toEmail, String link) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject(String.format("Welcome %s", toEmail));
        message.setText(link);
        javaMailSender.send(message);
    }
}
