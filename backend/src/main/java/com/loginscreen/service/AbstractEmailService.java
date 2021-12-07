package com.loginscreen.service;

import com.loginscreen.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements iEmailService {

    @Value("${default.sender}")
    private String sender;


    @Override
    public void sendNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage mailMessage = prepareNewPasswordEmail(user, newPass);
        sendEmail(mailMessage);

    }

    protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSubject("Solicitação de nova senha");
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText("Nova senha: " + newPass);
        return mailMessage;
    }
}
