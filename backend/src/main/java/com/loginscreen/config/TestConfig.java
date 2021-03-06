package com.loginscreen.config;

import com.loginscreen.service.iEmailService;
import com.loginscreen.service.test.DBService;
import com.loginscreen.service.test.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public iEmailService emailService(){
        return new MockEmailService();
    }



}
