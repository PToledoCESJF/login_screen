package com.loginscreen.service;

import com.loginscreen.model.entity.User;
import org.springframework.mail.SimpleMailMessage;

public interface iEmailService {

    void sendEmail(SimpleMailMessage msg);
    void sendNewPasswordEmail(User user, String newPass);

}
