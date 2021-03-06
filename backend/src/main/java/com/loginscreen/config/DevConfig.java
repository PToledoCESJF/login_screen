package com.loginscreen.config;

import com.loginscreen.service.SmtpEmailService;
import com.loginscreen.service.iEmailService;
import com.loginscreen.service.test.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public iEmailService emailService(){
        return new SmtpEmailService();
    }

}
